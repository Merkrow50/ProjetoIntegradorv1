package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Chamado;
import org.jetbrains.annotations.NotNull;

public class Chamado_FuncionarioDAO {

  public boolean inserir(@NotNull Chamado chamado) {

    String sql = "INSERT INTO public.\"chamado_funcionario\"" + "(chamado_id ,funcionario_id)"
        + "VALUES (?, ?);";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, chamado.getTrajetoInicio());
      stmt.setInt(2, chamado.getTrajetoFim());
      stmt.setInt(3, chamado.getVeiculo_id());
      stmt.execute();
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
    String sql = "DELETE FROM public.\"chamado\"" +
        "WHERE chamado_id = ?" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,c.getId());
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
        "SET  trejeto_inicial = ?, trajeto_final = ?" +
        "WHERE chamado_id = ? " + ";";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1,c.getTrajetoInicio());
      stmt.setString(2,c.getTrajetoFim());
      stmt.setInt(3,c.getId());
      stmt.execute();
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
    String sql = "SELECT  trejeto_inicial, trajeto_final, chamado_id " +
        " FROM public. \"chamado\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    List<Chamado> chamados = new ArrayList<>();
    Chamado chamado;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        chamado = new Chamado();

        chamado.setTrajetoInicio(result.getString("trajeto_inicial"));
        chamado.setTrajetoFim(result.getString("trajeto_final"));
        chamado.setId(result.getInt("chamado_id"));
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

}
