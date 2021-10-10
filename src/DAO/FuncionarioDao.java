package DAO;

import bd.ConnectionFactory;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import org.jetbrains.annotations.NotNull;


public class FuncionarioDao {

  public boolean inserir(@NotNull Funcionario funcionario) {

    String sql = "INSERT INTO public.\"Funcionario\"" + "(nome, matricula)"
        + "VALUES (?, ?);";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, funcionario.getNome());
      stmt.setString(2, funcionario.getMatricula());
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

  public boolean deletar(Funcionario f) {
    String sql = "DELETE FROM public.\"Funcionario\"" +
        "WHERE id = ?" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,f.getId());
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

  public boolean editar(Funcionario f) {
    String sql = "UPDATE public.\"Funcionario\"" +
        "SET nome = ? "  + ", matricula = ?" +
        "WHERE id = ? " + ";";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1,f.getNome());
      stmt.setString(2,f.getMatricula());
      stmt.setInt(3,f.getId());
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


  public List<Funcionario> consultar() {
    String sql = "SELECT nome, matricula, id" +
        " FROM public. \"Funcionario\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    List<Funcionario> funcionarios = new ArrayList<>();
    Funcionario funcionario;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        funcionario = new Funcionario(null, null);

        funcionario.setNome(result.getString("nome"));
        funcionario.setMatricula(result.getString("matricula"));
        funcionario.setId(result.getInt("id"));
        funcionarios.add(funcionario);

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return null;
    }
    return funcionarios;

  }

  ;

}
