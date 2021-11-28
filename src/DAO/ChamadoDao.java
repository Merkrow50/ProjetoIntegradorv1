package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Chamado;
import model.Funcionario;
import model.Veiculo;
import org.jetbrains.annotations.NotNull;


public class ChamadoDao {

  public boolean inserir(@NotNull Chamado chamado) {

    String sql = "INSERT INTO chamado" + "(trajeto_final, veiculo_id)"
        + "VALUES ( ?, ?);";

    String sql2 = "INSERT INTO chamado_funcionario" + "(chamado_id ,funcionario_id)"
        + "VALUES (?, ?);";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, chamado.getTrajetoFim());
      stmt.setInt(2, chamado.getVeiculo().getId());
      stmt.execute();
      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        int cod = rs.getInt("chamado_id");
        stmt = conn.prepareStatement(sql2);
        stmt.setInt(1, cod);
        stmt.setInt(2, chamado.getFuncionario().getId());
        stmt.execute();
      }
      stmt.close();
      conn.close();

      System.out.println("Campos adicionados com sucesso!");
      return true;
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }

  }

  ;

  public boolean deletar(Chamado c) {
    String sql = "DELETE FROM public.\"chamado_funcionario\"" +
        "WHERE chamado_id = ?" + ";";

    String sql2 = "DELETE FROM public. \"chamado\"" +
        "WHERE chamado_id = ?" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, c.getId());
      stmt.executeUpdate();
      stmt = conn.prepareStatement(sql2);
      stmt.setInt(1, c.getId());
      stmt.executeUpdate();
      stmt.close();
      conn.close();
      System.out.println("Campo deletado!");
      return true;
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }


  }

  ;

  public boolean editar(Chamado c) {
    String sql = "UPDATE public.\"chamado\"" +
        "SET  trajeto_inicial = ?, trajeto_final = ?, veiculo_id = ?" +
        "WHERE chamado_id = ? " + ";";

    String sql2 = "UPDATE public.chamado_funcionario\n"
        + "\tSET funcionario_id = ?\n"
        + "\tWHERE chamado_id = ?;";

    Connection conn = ConnectionFactory.getConnection();
    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, c.getTrajetoInicio());
      stmt.setString(2, c.getTrajetoFim());
      stmt.setInt(3,c.getVeiculo().getId());
      stmt.setInt(4, c.getId());
      stmt.executeUpdate();

      stmt = conn.prepareStatement(sql2);

      stmt.setInt(1,c.getFuncionario().getId());
      stmt.setInt(2,c.getId());
      stmt.executeUpdate();
      stmt.close();
      conn.close();
      System.out.println("Campo editado!");
      return true;
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }

  }

  ;


  public List<Chamado> consultar() {
    String sql = "SELECT  chamado_id, veiculo_id, trajeto_final" +
        " FROM public. \"chamado\"" + ";";

    Connection conn = ConnectionFactory.getConnection();

    List<Chamado> chamados = new ArrayList<>();

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        Veiculo veiculo = new Veiculo();
        Chamado chamado = new Chamado();
        chamado.setVeiculo(veiculo);
        chamado.setTrajetoFim(result.getString("trajeto_final"));
        chamado.getVeiculo().setId(result.getInt("veiculo_id"));
        chamado.setId(result.getInt("chamado_id"));
        String sql2 = "SELECT funcionario_id " +
            " FROM public. chamado_funcionario" + " WHERE chamado_id = ?;";

        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setInt(1, chamado.getId());
        ResultSet result2 = stmt2.executeQuery();

        while (result2.next()) {
          Funcionario funcionario = new Funcionario(null, null, false);
          chamado.setFuncionario(funcionario);

          chamado.getFuncionario().setId(result2.getInt("funcionario_id"));


        }

        String sql3 = "SELECT nome " +
            " FROM public. funcionario" + " WHERE funcionario_id = ?;";

        PreparedStatement stmt3 = conn.prepareStatement(sql3);
        stmt3.setInt(1, chamado.getFuncionario().getId());
        ResultSet result3 = stmt3.executeQuery();

        while (result3.next()) {

          chamado.getFuncionario().setNome(result3.getString("nome"));

        }

        String sql4 = "SELECT modelo " +
            " FROM public. veiculo" + " WHERE veiculo_id = ?;";

        PreparedStatement stmt4 = conn.prepareStatement(sql4);
        stmt4.setInt(1, chamado.getVeiculo().getId());
        ResultSet result4 = stmt4.executeQuery();

        while (result4.next()) {

          chamado.getVeiculo().setModelo(result4.getString("modelo"));

        }

        chamados.add(chamado);

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return null;
    }
    return chamados;

  }

  ;

  public void chamadoEmEdicao(Chamado chamado){
    String sql = "SELECT trajeto_final, veiculo_id, chamado_id" +
        " FROM public. \"chamado\"" + " WHERE chamado_id=?;";
    Connection conn = ConnectionFactory.getConnection();

    Veiculo veiculo = new Veiculo();
    chamado.setVeiculo(veiculo);

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,chamado.getId());
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        chamado.setTrajetoFim(result.getString("trajeto_final"));
        chamado.getVeiculo().setId(result.getInt("veiculo_id"));
        chamado.setId(result.getInt("chamado_id"));

        String sql2 = "SELECT funcionario_id " +
            " FROM public. chamado_funcionario" + " WHERE chamado_id = ?;";

        PreparedStatement stmt2 = conn.prepareStatement(sql2);
        stmt2.setInt(1, chamado.getId());
        ResultSet result2 = stmt2.executeQuery();

        while (result2.next()) {
          Funcionario funcionario = new Funcionario(null, null, false);
          chamado.setFuncionario(funcionario);

          chamado.getFuncionario().setId(result2.getInt("funcionario_id"));


        }

        String sql3 = "SELECT nome " +
            " FROM public. funcionario" + " WHERE funcionario_id = ?;";

        PreparedStatement stmt3 = conn.prepareStatement(sql3);
        stmt3.setInt(1, chamado.getFuncionario().getId());
        ResultSet result3 = stmt3.executeQuery();

        while (result3.next()) {

          chamado.getFuncionario().setNome(result3.getString("nome"));

        }

        String sql4 = "SELECT modelo " +
            " FROM public. veiculo" + " WHERE veiculo_id = ?;";

        PreparedStatement stmt4 = conn.prepareStatement(sql4);
        stmt4.setInt(1, chamado.getVeiculo().getId());
        ResultSet result4 = stmt4.executeQuery();

        while (result4.next()) {

          chamado.getVeiculo().setModelo(result4.getString("modelo"));

        }

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
    }
  }

}
