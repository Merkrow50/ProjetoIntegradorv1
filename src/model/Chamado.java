package model;

public class Chamado {

  private Veiculo veiculo;
  private Funcionario funcionario;
  private String trajetoInicio;
  private String trajetoFim;
  private int id;

  public Chamado() {

  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
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

  public void setTrajetoInicio(String trajetoInicio) {
    this.trajetoInicio = trajetoInicio;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
