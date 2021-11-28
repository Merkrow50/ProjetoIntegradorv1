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
  Color button = new Color(24, 109, 60);
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
    contentPane.add(label);

    JButton buttonFuncionario = new JButton("Funcionário");
    buttonFuncionario.setBounds(130,100,150, 30);

    buttonFuncionario.setForeground(btnFontColor);
    buttonFuncionario.setBackground(button);

    JButton buttonVeiculo = new JButton("Veiculos");
    buttonVeiculo.setBounds(130,150,150, 30);
    buttonVeiculo.setForeground(btnFontColor);
    buttonVeiculo.setBackground(button);



    JButton buttonChamado = new JButton("Chamado");
    buttonChamado.setBounds(130,200,150, 30);

    buttonChamado.setForeground(btnFontColor);
    buttonChamado.setBackground(button);

    JLabel somarLabel = new JLabel();
    somarLabel.setBounds(100,100,10,30);

    somarLabel.setText(String.valueOf(arvoreDAO.consultar()));

    JButton buttonSomar = new JButton("+");
    buttonSomar.setBackground(button);
    buttonSomar.setBorderPainted(false);
    buttonSomar.setForeground(btnFontColor);

    JButton buttonSubtrair = new JButton("-");
    buttonSubtrair.setBorderPainted(false);
    buttonSubtrair.setBackground(button);
    buttonSubtrair.setForeground(btnFontColor);

    JLabel txtArvore = new JLabel();
    txtArvore.setText("Nº TOTAL DE ARVORES PLANTADAS:");

    JLabel txtCO2 = new JLabel();
    txtCO2.setText("Nº TOTAL DE CO2 ABSORVIDO: " + arvoreDAO.consultar() * 15.6 + " KG/ANO");

    JPanel novoPanel = new JPanel();

    JPanel novoPanel2 = new JPanel();
    novoPanel.setBackground(background);
    novoPanel.add(txtArvore);
    novoPanel.add(buttonSubtrair);
    novoPanel.add(somarLabel);
    novoPanel.add(buttonSomar);
    novoPanel2.add(txtCO2);

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
        contador[0] = arvoreDAO.consultar() + 1;
        contador1[0] = arvoreDAO.consultar();
        arvoreDAO.atualizat(contador1[0],contador[0]);
        somarLabel.setText(String.valueOf(arvoreDAO.consultar()));
        txtCO2.setText("Nº TOTAL DE CO2 ABSORVIDO: " + arvoreDAO.consultar() * 15.6 + " KG/ANO");


      }
    });

    buttonSubtrair.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        contador[0] = arvoreDAO.consultar() - 1;
        contador1[0] = arvoreDAO.consultar();
        arvoreDAO.atualizat(contador1[0],contador[0]);
        somarLabel.setText(String.valueOf(arvoreDAO.consultar()));
        txtCO2.setText("Nº TOTAL DE CO2 ABSORVIDO: " + arvoreDAO.consultar() * 15.6 + " KG/ANO");
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

}
