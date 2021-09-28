package model;

public class Funcionario {

  private  int id;
  private  String matricula;
  private  String nome;

  public Funcionario(String nome, String matricula, Integer id) {

    this.nome = nome;
    this.matricula = matricula;
    this.id = id;

  }

  public int getId() {
    return id;
  }

  public String getMatricula() {
    return matricula;
  }

  public String getNome() {
    return nome;
  }


}
