package model;

public class Veiculo {

  private String modelo;
  private int autonomia;
  private String ano;
  private int quantidade;

  public Veiculo(){



  }

  public int getAutonomia() {
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

  public void setAutonomia(int autonomia) {
    this.autonomia = autonomia;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }


}
