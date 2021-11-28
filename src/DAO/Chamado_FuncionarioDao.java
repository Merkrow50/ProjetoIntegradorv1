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

public class Chamado_FuncionarioDao {

  public boolean inserir(@NotNull Chamado chamado) {

    String sql = "INSERT INTO public.\"chamado_funcionario\"" + "(chamado_id ,funcionario_id)"
        + "VALUES (?, ?);";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, chamado.getId());
      stmt.setInt(2, chamado.getFuncionario().getId());
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
    String sql = "DELETE FROM public.\"chamado_funcionario\"" +
        "WHERE (chamado_id = ? , funcionario_id  = ?)" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, c.getId());
      stmt.setInt(2, c.getFuncionario().getId());
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


  public List<Chamado> consultar() {
    String sql = "SELECT  chamado_id, funcionario_id " +
        " FROM public. \"chamado_funcionario\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    List<Chamado> chamados = new ArrayList<>();
    Chamado chamado;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        chamado = new Chamado();

        chamado.setId(result.getInt("chamado_id"));
        chamado.getFuncionario().setId(result.getInt("funcionario_id"));
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
