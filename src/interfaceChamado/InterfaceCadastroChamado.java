package interfaceChamado;


import DAO.ChamadoDao;
import DAO.FuncionarioDao;
import DAO.VeiculoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Chamado;
import model.Funcionario;
import model.Veiculo;

public class InterfaceCadastroChamado extends JFrame {

  @Serial
  private static final long serialVersionUID = -4349720804436041125L;

  FuncionarioDao funcionarioDao = new FuncionarioDao();
  VeiculoDao veiculoDao = new VeiculoDao();

  public InterfaceCadastroChamado() throws SQLException {
    super();
    setSize(400, 500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );

    JLabel funcionarioLabel = new JLabel();
    setLabel(funcionarioLabel,"Funcionário:",55, 90, 100, 40);

    JComboBox funcionarioComboBox = new JComboBox();
    for (int i = 0; i < funcionarioDao.consultar().size(); i++) {
      funcionarioComboBox.addItem(funcionarioDao.consultar().get(i).getNome());
    }
    funcionarioComboBox.setBounds(130, 100, 100, 40);
    funcionarioComboBox.setSize(150, 20);
    add(funcionarioComboBox);

    JLabel veiculoLabel = new JLabel();
    setLabel(veiculoLabel,"Veiculo:",80, 120, 100, 40);

    JComboBox veiculoComboBox = new JComboBox();
    for (int i = 0; i < veiculoDao.consultar().size(); i++) {
      veiculoComboBox.addItem(veiculoDao.consultar().get(i).getModelo());
    }
    veiculoComboBox.setBounds(130, 130, 100, 40);
    veiculoComboBox.setSize(150, 20);
    add(veiculoComboBox);

    JLabel trajetoFimLabel = new JLabel();
    setLabel(trajetoFimLabel,"Destino:",80, 150, 100, 40);

    JTextField trajetoFimTxFld = new JTextField();
    setTxtField(trajetoFimTxFld,130, 160, 150, 20);

    JButton buttonAddChamado = new JButton("Cadastrar");
    buttonAddChamado.setBounds(130, 220, 100, 30);
    add(buttonAddChamado);

    buttonAddChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ChamadoDao dao = new ChamadoDao();

        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario(null, null, false);

        Chamado chamado = new Chamado();
        chamado.setVeiculo(veiculo);
        chamado.setFuncionario(funcionario);

        chamado.setTrajetoFim(trajetoFimTxFld.getText());
        chamado.getVeiculo().setId(veiculoDao.consultar().get(veiculoComboBox.getSelectedIndex()).getId());
        chamado.getFuncionario().setId(funcionarioDao.consultar().get(funcionarioComboBox.getSelectedIndex()).getId());
        funcionarioDao.funcionarioEmEdicao(chamado.getFuncionario());
        if(chamado.getFuncionario().isHabilitado()){
          if (dao.inserir(chamado)) {
            JOptionPane.showMessageDialog(null, "Chamado salvo com sucesso!");
            setVisible(false);
            new InterfaceListChamado();
          } else {
            JOptionPane.showMessageDialog(null, "Algo de errado aconteceu! Tente Novamente!");
          }
        }else {
          JOptionPane.showMessageDialog(null, "Funcionário não habilitado! Cadastre um funcionário habilitado!");
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
