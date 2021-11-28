package interfaceFuncionario;


import DAO.FuncionarioDao;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Funcionario;

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

    JLabel habilitadoLabel = new JLabel();
    habilitadoLabel.setText("Habilitado:");
    habilitadoLabel.setBounds(90,150,100, 40);
    add(habilitadoLabel);

    IsHabilitado habilitado[] = IsHabilitado.values();

    JComboBox habilitadoTxFld = new JComboBox();
    for (IsHabilitado h : habilitado) {
      habilitadoTxFld.addItem(h);
    }
    habilitadoTxFld.setBounds(150,160,100, 40);
    habilitadoTxFld.setSize(150,20);
    add(habilitadoTxFld);

    JButton buttonAddFuncionario = new JButton("Cadastrar");
    buttonAddFuncionario.setBounds(130,200,100, 30);
    add(buttonAddFuncionario);

    buttonAddFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        FuncionarioDao dao = new FuncionarioDao();
        String nome = nomeTxFld.getText();
        String matricula = matriculaTxFld.getText();
        int habilitado = habilitadoTxFld.getSelectedIndex();

        System.out.println(habilitado);

        Funcionario funcionario;
        if(habilitado == 0){

          funcionario = new Funcionario(nome, matricula, IsHabilitado.HABILITADO.ishabilitado);

        }else{

          funcionario = new Funcionario(nome, matricula, IsHabilitado.DESABILITADO.ishabilitado);

        }
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
