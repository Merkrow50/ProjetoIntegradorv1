package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
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

  };

  public boolean deletar(int id) {
    String sql = "DELETE FROM public.\"Funcionario\"" +
        "WHERE id = " + id + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.execute();
      stmt.close();
      conn.close();
      System.out.println("Campo deletado!");
      return true;
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }


  };

  public boolean editar(String nome, String matricula, int id){
    String sql = "UPDATE public.\"Funcionario\"" +
	"SET nome=" + "'" + nome + "'" + ", matricula=" + "'" + matricula +"'" +
	"WHERE id =" + id +";";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
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

  };

  public boolean consultar(){
    String sql = "SELECT nome, matricula, id" +
    " FROM public. \"Funcionario\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()){

        Integer id = result.getInt("id");
        String nome = result.getString("nome");
        String matricula = result.getString("matricula");

        System.out.println(String.format("%d\t%s\t%s", id,nome,matricula));

      }
      System.out.println("tabela consultada!");
      return true;
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }


  };

}
