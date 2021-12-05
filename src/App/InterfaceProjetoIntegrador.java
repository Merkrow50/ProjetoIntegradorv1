package App;

import DAO.ArvoreDAO;
import interfaceChamado.InterfaceListChamado;
import interfaceFuncionario.InterfaceListFuncionario;
import interfaceVeiculo.InterfaceListVeiculo;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InterfaceProjetoIntegrador extends JFrame {

  @Serial
  private static final long serialVersionUID = 3510759756418883913L;

  Color background = new Color(126, 234, 15, 255);
  Color buttonColor = new Color(24, 109, 60);
  Color btnFontColor = new Color(255, 255, 255);
  ArvoreDAO arvoreDAO = new ArvoreDAO();
  public InterfaceProjetoIntegrador(){
    super();
    setSize(400,500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    Container contentPane = getContentPane();
    contentPane.setLayout(
       new FlowLayout()
    );

    contentPane.setBackground(background);

    ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("carbon-footprint.png")));
    JLabel label = new JLabel(logo);
    label.setSize(100,100);

    JButton buttonFuncionario = new JButton("Funcionários");
    setButton(buttonFuncionario,130,100,150, 30);

    JButton buttonVeiculo = new JButton("Veiculos");
    setButton(buttonVeiculo,130,150,150, 30);


    JButton buttonChamado = new JButton("Chamados");
    setButton(buttonChamado,130,200,150, 30);

    JLabel somarLabel = new JLabel();
    somarLabel.setBounds(100,100,10,30);
    somarLabel.setText(String.valueOf(arvoreDAO.consultar()));

    JButton buttonSomar = new JButton("+");
    setButton(buttonSomar);

    JButton buttonSubtrair = new JButton("-");
    setButton(buttonSubtrair);

    JLabel txtArvore = new JLabel();
    txtArvore.setText("Nº total de árvores plantadas: ");

    JLabel txtCO2 = new JLabel();
    txtCO2.setText("Nº total de co2 absorvido em um ano: " + arvoreDAO.consultar() * 15.6 + " kg/L");

    JPanel novoPanel = new JPanel();

    JPanel novoPanel2 = new JPanel();
    novoPanel.setBackground(background);
    novoPanel.add(txtArvore);
    novoPanel.add(buttonSubtrair);
    novoPanel.add(somarLabel);
    novoPanel.add(buttonSomar);

    novoPanel2.setBackground(background);
    novoPanel2.add(txtCO2);

    contentPane.add(label);
    contentPane.add(buttonFuncionario,FlowLayout.CENTER);
    contentPane.add(buttonVeiculo,FlowLayout.CENTER);
    contentPane.add(buttonChamado,FlowLayout.CENTER);
    contentPane.add(novoPanel);
    contentPane.add(novoPanel2);

    final int[] contador = {0};
    final int[] contador1 = {-1};

    buttonSomar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        countArvore("+",contador,contador1,somarLabel,txtCO2);
      }
    });

    buttonSubtrair.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        countArvore("-",contador,contador1,somarLabel,txtCO2);
      }
    });

    buttonFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
             new InterfaceListFuncionario();
      }
    });

    buttonVeiculo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new InterfaceListVeiculo();
      }
    });

    buttonChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new InterfaceListChamado();
      }
    });



    setResizable(true);
    setVisible(true);
  }


  public void setButton(JButton button, int x , int y, int width, int height) {
    button.setBackground(buttonColor);
    button.setForeground(btnFontColor);
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setBounds(x, y, width, height);
  }

  public void setButton(JButton button) {
    button.setFocusPainted(false);
    button.setBackground(buttonColor);
    button.setForeground(btnFontColor);
    button.setBorderPainted(false);
  }

  public void countArvore(String somar, int[] contador, int[] contador1, JLabel somarLabel, JLabel txtCO2){
    if(Objects.equals(somar,"-")){
      contador[0] = arvoreDAO.consultar() - 1;
    }else {
      contador[0] = arvoreDAO.consultar() + 1;
    }

    contador1[0] = arvoreDAO.consultar();
    arvoreDAO.atualizar(contador1[0],contador[0]);
    somarLabel.setText(String.valueOf(arvoreDAO.consultar()));
    txtCO2.setText("Nº total de co2 absorvido em um ano: " + arvoreDAO.consultar() * 15.6 + " KG/ANO");

  }
}
