package model;

public class Veiculo {

  private String modelo;
  private float autonomia;
  private String ano;
  private int quantidade;
  private int id;

  public Veiculo(){


  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getAutonomia() {
    return autonomia;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public String getAno() {
    return ano;
  }

  public String getModelo() {
    return modelo;
  }

  public void setAno(String ano) {
    this.ano = ano;
  }

  public void setAutonomia(float autonomia) {
    this.autonomia = autonomia;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

}
