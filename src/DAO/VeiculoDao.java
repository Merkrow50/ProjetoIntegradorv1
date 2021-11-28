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
import model.Veiculo;
import org.jetbrains.annotations.NotNull;


public class VeiculoDao {

  public boolean inserir(@NotNull Veiculo veiculo) {

    String sql = "INSERT INTO public.\"veiculo\"" + "(modelo, autonomia, ano)"
        + "VALUES (?, ?, ?);";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, veiculo.getModelo());
      stmt.setFloat(2, veiculo.getAutonomia());
      stmt.setInt(3, veiculo.getAno());
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

  public boolean deletar(Veiculo v) {
    String sql = "DELETE FROM public.\"veiculo\"" +
        "WHERE veiculo_id = ?" + ";";
    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,v.getId());
      stmt.executeUpdate();
      stmt.close();
      conn.close();
      System.out.println("Campo deletado!");
      return true;
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Campo não pode ser deletado! Verifique se não há nenhum chamado com este veiculo em aberto!");
      e.printStackTrace();
      System.out.println("ERRO!");
      return false;
    }


  }

  ;

  public boolean editar(Veiculo v) {
    String sql = "UPDATE public.\"veiculo\"" +
        "SET modelo = ? "  + ", autonomia = ?" + ", ano = ?" + ", quantidade = ?" +
        "WHERE veiculo_id = ? " + ";";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setString(1, v.getModelo());
      stmt.setFloat(2,v.getAutonomia());
      stmt.setInt(3,v.getAno());
      stmt.setInt(4,v.getQuantidade());
      stmt.setInt(5,v.getId());
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


  public List<Veiculo> consultar() {
    String sql = "SELECT modelo, autonomia, ano, quantidade, veiculo_id " +
        " FROM public. \"veiculo\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    List<Veiculo> veiculos = new ArrayList<>();
    Veiculo veiculo;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        veiculo = new Veiculo();

        veiculo.setModelo(result.getString("modelo"));
        veiculo.setAutonomia(result.getFloat("autonomia"));
        veiculo.setAno(result.getInt("ano"));
        veiculo.setQuantidade(result.getInt("quantidade"));
        veiculo.setId(result.getInt("veiculo_id"));
        veiculos.add(veiculo);

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
      return null;
    }
    return veiculos;

  };

  public void veiculoEmEdicao(Veiculo veiculo){
    String sql = "SELECT modelo, ano, autonomia, quantidade, veiculo_id" +
        " FROM public. \"veiculo\"" + " WHERE veiculo_id=?;";
    Connection conn = ConnectionFactory.getConnection();

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1,veiculo.getId());
      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        veiculo.setModelo(result.getString("modelo"));
        veiculo.setAno(result.getInt("ano"));
        veiculo.setAutonomia(result.getFloat("autonomia"));
        veiculo.setQuantidade(result.getInt("quantidade"));
        veiculo.setId(result.getInt("veiculo_id"));

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
    }
  }

  }
