package App;

import interfaceFuncionario.InterfaceCadastroFuncionario;
import interfaceFuncionario.InterfaceListFuncionario;
import interfaceVeiculo.InterfaceListVeiculo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;


public class InterfaceProjetoIntegrador extends JFrame {

  @Serial
  private static final long serialVersionUID = 3510759756418883913L;

  Color background = new Color(126, 234, 15, 255);
  Color button = new Color(24, 109, 60);
  Color btnFontColor = new Color(255, 255, 255);

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



    contentPane.add(buttonFuncionario,FlowLayout.CENTER);
    contentPane.add(buttonVeiculo,FlowLayout.CENTER);
    contentPane.add(buttonChamado,FlowLayout.CENTER);

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
        new JFrame();
      }
    });



    setResizable(true);
    setVisible(true);
  }

}
