package model;

public class Chamado {

  private int veiculo_id;
  private Funcionario funcionario;
  private String trajetoInicio;
  private String trajetoFim;
  private int id;

  public Chamado(){


  }

  public int getVeiculo_id() {
    return veiculo_id;
  }

  public void setVeiculo_id(int veiculo_id) {
    this.veiculo_id = veiculo_id;
  }

  public String getTrajetoFim() {
    return trajetoFim;
  }

  public void setTrajetoFim(String trajetoFim) {
    this.trajetoFim = trajetoFim;
  }

  public Funcionario getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(Funcionario funcionario) {
    this.funcionario = funcionario;
  }

  public String getTrajetoInicio() {
    return trajetoInicio;
  }

  public void setTrajetoInicio(String trajetoInicio ) {
    this.trajetoInicio = trajetoInicio;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
  }
}
