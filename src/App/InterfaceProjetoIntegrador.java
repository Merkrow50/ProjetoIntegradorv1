package App;

import interfaceFuncionario.InterfaceCadastroFuncionario;
import interfaceFuncionario.InterfaceListFuncionario;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;


public class InterfaceProjetoIntegrador extends JFrame {

  @Serial
  private static final long serialVersionUID = 3510759756418883913L;

  public InterfaceProjetoIntegrador(){
    super();
    setSize(400,500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    Container contentPane = getContentPane();

    contentPane.setLayout(
       null
    );

    JButton buttonFuncionario = new JButton("Funcionário");
    buttonFuncionario.setBounds(130,100,150, 30);

    JButton buttonVeiculo = new JButton("Veiculos");
    buttonVeiculo.setBounds(130,150,150, 30);

    JButton buttonChamado = new JButton("Chamado");
    buttonChamado.setBounds(130,200,150, 30);



    contentPane.add(buttonFuncionario,BorderLayout.CENTER);
    contentPane.add(buttonVeiculo,BorderLayout.CENTER);
    contentPane.add(buttonChamado,BorderLayout.CENTER);

    buttonFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
             new InterfaceListFuncionario();
      }
    });

    buttonVeiculo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new JFrame();
      }
    });

    buttonChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new JFrame();
      }
    });




    setVisible(true);
  }

}
