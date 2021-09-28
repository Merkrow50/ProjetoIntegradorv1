package DAO;

import bd.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Funcionario;


public class FuncionarioDao {

  public boolean inserir(Funcionario funcionario) throws SQLException {

          String sql = "INSERT INTO  Funcionario (nome, matricula, id) VALUES (?,?,?);";
          Connection conn = ConnectionFactory.getConnection();

          try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,funcionario.getNome());
            stmt.setString(2,funcionario.getMatricula());
            stmt.setInt(3,funcionario.getId());
            stmt.executeQuery();
            stmt.close();
            conn.close();
            System.out.println("Campos adicionados com sucesso!");
            return true;
          }catch (SQLException e){

              e.printStackTrace();
              System.out.println("ERRO!");
              return false;
          }

  };

//  public boolean deletar(){
//
//
//  };
//
//  public boolean editar(){
//
//
//  };
//
//  public boolean consultar(){
//
//
//  };

}
