package interfaceFuncionario;


import DAO.FuncionarioDao;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Funcionario;
import org.jetbrains.annotations.NotNull;

public class InterfaceCadastroFuncionario extends JFrame{

  @Serial
  private static final long serialVersionUID = -4531896117468329034L;

  public InterfaceCadastroFuncionario(){
    super();
    setSize(400,500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );


    JLabel nomeLabel = new JLabel();
    nomeLabel.setText("Nome:");
    nomeLabel.setBounds(90,90,100, 40);
    add(nomeLabel);

    JTextField nomeTxFld = new JTextField();
    nomeTxFld.setBounds(130,100,100, 40);
    nomeTxFld.setSize(150,20);
    add(nomeTxFld);

    JLabel matriculaLabel = new JLabel();
    matriculaLabel.setText("Matricula:");
    matriculaLabel.setBounds(90,120,100, 40);
    add(matriculaLabel);

    JTextField matriculaTxFld = new JTextField();
    matriculaTxFld.setBounds(150,130,100, 40);
    matriculaTxFld.setSize(150,20);
    add(matriculaTxFld);

    JButton buttonAddFuncionario = new JButton("Cadastrar");
    buttonAddFuncionario.setBounds(130,200,100, 30);
    add(buttonAddFuncionario);

    buttonAddFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FuncionarioDao dao = new FuncionarioDao();
        String nome = nomeTxFld.getText();
        String matricula = matriculaTxFld.getText();
        Funcionario funcionario = new Funcionario(nome, matricula);

        if (dao.inserir(funcionario)){

          JOptionPane.showMessageDialog(null,"Funcionário salvo com sucesso!");
          setVisible(false);
          new InterfaceListFuncionario();
        }else {

          JOptionPane.showMessageDialog(null,"Algo de errado aconteceu! Tente Novamente!");

        }
      }
    });



    setVisible(true);
  }

}
