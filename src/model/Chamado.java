package model;

import model.Funcionario;
import model.Veiculo;

public class Chamado {

  private Veiculo veiculo;
  private Funcionario funcionario;
  private int trajeto;

  public Chamado(){


  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }


}
