package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Funcionario;
import org.jetbrains.annotations.NotNull;


public class FuncionarioDao {


  public boolean inserir(@NotNull Funcionario funcionario) {

    String sql = "INSERT INTO public.\"funcionario\"" + "(nome, matricula, habilitado)"
        + "VALUES (?, ?, ?);";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, funcionario.getNome());
      stmt.setString(2, funcionario.getMatricula());
      stmt.setBoolean(3, funcionario.isHabilitado());
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
    String sql = "DELETE FROM public.\"funcionario\"" +
        "WHERE funcionario_id = ?" + ";";
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
      JOptionPane.showMessageDialog(null, "Campo não pode ser deletado! Verifique se não há nenhum chamado com este funcionario em aberto!");
      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }


  }

  ;

  public boolean editar(Funcionario f) {
    String sql = "UPDATE public.\"funcionario\"" +
        "SET nome = ? "  + ", matricula = ?" + ", habilitado = ?" +
        "WHERE funcionario_id = ? " + ";";

    Connection conn = ConnectionFactory.getConnection();
    System.out.println(f);

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1,f.getNome());
      stmt.setString(2,f.getMatricula());
      stmt.setBoolean(3,f.isHabilitado());
      stmt.setInt(4,f.getId());
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
    String sql = "SELECT nome, matricula, habilitado, funcionario_id" +
        " FROM public. \"funcionario\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    List<Funcionario> funcionarios = new ArrayList<>();
    Funcionario funcionario;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        funcionario = new Funcionario(null, null,false);

        funcionario.setNome(result.getString("nome"));
        funcionario.setMatricula(result.getString("matricula"));
        funcionario.setHabilitado(result.getBoolean("habilitado"));
        funcionario.setId(result.getInt("funcionario_id"));
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

  public void funcionarioEmEdicao(Funcionario funcionario){
    String sql = "SELECT nome, matricula, habilitado, funcionario_id" +
        " FROM public. \"funcionario\"" + " WHERE funcionario_id=?;";
    Connection conn = ConnectionFactory.getConnection();

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,funcionario.getId());
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        funcionario.setNome(result.getString("nome"));
        funcionario.setMatricula(result.getString("matricula"));
        funcionario.setHabilitado(result.getBoolean("habilitado"));
        funcionario.setId(result.getInt("funcionario_id"));

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
    }
  }
}
