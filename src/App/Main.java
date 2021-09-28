package App;

import DAO.FuncionarioDao;
import java.sql.SQLException;
import model.Funcionario;

public class Main {

  public static void main(String[] args) throws SQLException {

    FuncionarioDao dao = new FuncionarioDao();

    Funcionario funcionario10 = new Funcionario("Marcelo","123456",1);

    if (dao.inserir(funcionario10)){

      System.out.println("Funcionario inserido com sucesso!");

    }else {

      System.out.println("ERRO!");

    }



  }

}
