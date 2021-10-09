package model;


public class Funcionario {

  private String matricula;
  private String nome;

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


}
