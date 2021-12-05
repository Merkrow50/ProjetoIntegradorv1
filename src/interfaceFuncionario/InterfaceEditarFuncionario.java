package interfaceFuncionario;

import DAO.FuncionarioDao;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import model.Funcionario;

public class InterfaceEditarFuncionario extends JFrame {

  @Serial
  private static final long serialVersionUID = 6274683761440960739L;
  FuncionarioDao dao = new FuncionarioDao();


  public InterfaceEditarFuncionario(Funcionario funcionario) {
    super();
    setSize(400, 500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );

    JLabel nomeLabel = new JLabel();
    setLabel(nomeLabel,"Nome:",90, 90, 100, 40);

    JTextField nomeTxFld = new JTextField();
    setTxtField(nomeTxFld,130, 100, 150, 20);

    JLabel matriculaLabel = new JLabel();
    setLabel(matriculaLabel,"Matricula:",70, 120, 100, 40);

    JTextField matriculaTxFld = new JTextField();
    setTxtField(matriculaTxFld,130, 130, 150, 20);

    JLabel habilitadoLabel = new JLabel();
    setLabel(habilitadoLabel,"Habilitado:",65, 150, 100, 40);

    IsHabilitado habilitado[] = IsHabilitado.values();

    JComboBox habilitadoTxFld = new JComboBox();
    for (IsHabilitado h : habilitado) {
      habilitadoTxFld.addItem(h);
    }
    habilitadoTxFld.setBounds(130, 160, 150, 20);
    add(habilitadoTxFld);

    JButton buttonAddFuncionario = new JButton("Cadastrar");
    buttonAddFuncionario.setBounds(130, 200, 100, 30);
    add(buttonAddFuncionario);

    dao.funcionarioEmEdicao(funcionario);

    nomeTxFld.setText(funcionario.getNome());
    matriculaTxFld.setText(funcionario.getMatricula());

    if (funcionario.isHabilitado()) {
      habilitadoTxFld.setSelectedIndex(0);
    } else {
      habilitadoTxFld.setSelectedIndex(1);
    }

    buttonAddFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String nome = nomeTxFld.getText();
        String matricula = matriculaTxFld.getText();
        int habilitado = habilitadoTxFld.getSelectedIndex();
        int id = funcionario.getId();

        System.out.println(habilitado);

        Funcionario funcionario;
        if (habilitado == 0) {

          funcionario = new Funcionario(nome, matricula, IsHabilitado.HABILITADO.ishabilitado);

        } else {

          funcionario = new Funcionario(nome, matricula, IsHabilitado.DESABILITADO.ishabilitado);

        }
        funcionario.setId(id);
        System.out.println(funcionario.isHabilitado());
        if (dao.editar(funcionario)) {

          JOptionPane.showMessageDialog(null, "Funcionário editado com sucesso!");
          setVisible(false);
          new InterfaceListFuncionario();
        } else {

          JOptionPane.showMessageDialog(null, "Algo de errado aconteceu! Tente Novamente!");

        }


      }
    });

    setVisible(true);
  }

  public void setLabel(JLabel label, String title, int x, int y, int width, int height){
    label.setText(title);
    label.setBounds(x,y,width,height);
    add(label);
  }

  public void setTxtField(JTextField field,int x, int y, int width, int height){
    field.setBounds(x,y,width,height);
    add(field);
  }

}
