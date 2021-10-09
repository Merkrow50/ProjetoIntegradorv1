package interfaceFuncionario;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class InterfaceListFuncionario extends JFrame {
  @Serial
  private static final long serialVersionUID = 6833478251487492489L;

  public InterfaceListFuncionario(){
    super();
    setSize(400,500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );

    JButton buttonNewFuncionario = new JButton("Novo");
    buttonNewFuncionario.setBounds(260,20,100, 30);
    add(buttonNewFuncionario);

    buttonNewFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new InterfaceCadastroFuncionario();
      }
    });
    setVisible(true);
  }



}
