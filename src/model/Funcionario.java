package model;


public class Funcionario {

  private String matricula;
  private String nome;
  private boolean habilitado;
  private int id;

  public Funcionario(String nome, String matricula, Boolean habilitado) {

    this.nome = nome;
    this.matricula = matricula;
    this.habilitado = habilitado;

  }

  public String getMatricula() {
    return matricula;
  }

  public String getNome() {
    return nome;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  @Override
  public String toString() {
    return "Funcionario{" +
        "matricula='" + matricula + '\'' +
        ", nome='" + nome + '\'' +
        ", id=" + id +
        '}';
  }

  public void setHabilitado(boolean habilitado) {
    this.habilitado = habilitado;
  }

  public boolean isHabilitado() {
    return habilitado;
  }
}
