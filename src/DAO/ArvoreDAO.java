package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArvoreDAO {


  public void atualizat(int contador, int soma) {
    String sql = "UPDATE public.\"arvores\"" +
        "SET contador = ?" +
        "WHERE contador = ?;";

    Connection conn = ConnectionFactory.getConnection();

    try {
      PreparedStatement stmt = conn.prepareStatement(sql);
      stmt.setInt(1, soma);
      stmt.setInt(2, contador);
      stmt.execute();
      stmt.close();
      conn.close();
      System.out.println("Campo editado!");
    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
    }
  }

  public int consultar(){

    String sql = "SELECT contador" +
        " FROM public. \"arvores\"" + ";";
    Connection conn = ConnectionFactory.getConnection();

    int numero = 0;

    try {
      assert conn != null;
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet result = stmt.executeQuery();

      while (result.next()) {

        numero = result.getInt("contador");

      }

      System.out.println("tabela consultada!");


    } catch (SQLException e) {

      e.printStackTrace();
      System.out.println("ERRO!");
    }
    return numero;

  }

}