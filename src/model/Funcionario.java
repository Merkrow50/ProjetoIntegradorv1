package model;


public class Funcionario {

  private String matricula;
  private String nome;
  private int id;

  public Funcionario(String nome, String matricula) {

    this.nome = nome;
    this.matricula = matricula;

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
}
