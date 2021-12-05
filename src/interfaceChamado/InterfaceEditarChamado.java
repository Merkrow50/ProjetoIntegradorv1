package interfaceChamado;

import DAO.ChamadoDao;
import DAO.FuncionarioDao;
import DAO.VeiculoDao;
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
import model.Chamado;
import model.Funcionario;
import model.Veiculo;

public class InterfaceEditarChamado extends JFrame {

  @Serial
  private static final long serialVersionUID = 8671952615998151385L;
  ChamadoDao dao = new ChamadoDao();
  FuncionarioDao funcionarioDao = new FuncionarioDao();
  VeiculoDao veiculoDao = new VeiculoDao();


  public InterfaceEditarChamado(Chamado chamado) {
    super();
    setSize(400, 500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );

    JLabel funcionarioLabel = new JLabel();
    setLabel(funcionarioLabel,"Funcionário:",60, 90, 100, 40);

    JComboBox funcionarioTxFld = new JComboBox();
    for (int i = 0; i < funcionarioDao.consultar().size(); i++) {
      funcionarioTxFld.addItem(funcionarioDao.consultar().get(i).getNome());
    }
    funcionarioTxFld.setBounds(130, 100, 100, 40);
    funcionarioTxFld.setSize(150, 20);
    add(funcionarioTxFld);

    JLabel veiculoLabel = new JLabel();
    setLabel(veiculoLabel,"Veiculo:",60, 120, 100, 40);

    JComboBox veiculoTxFld = new JComboBox();
    for (int i = 0; i < veiculoDao.consultar().size(); i++) {
      veiculoTxFld.addItem(veiculoDao.consultar().get(i).getModelo());
    }
    veiculoTxFld.setBounds(130, 130, 100, 40);
    veiculoTxFld.setSize(150, 20);
    add(veiculoTxFld);

    JLabel trajetoFimLabel = new JLabel();
    setLabel(trajetoFimLabel,"Destino:",50, 180, 100, 40);

    JTextField trajetoFimTxFld = new JTextField();
    setTxtField(trajetoFimTxFld,130, 190, 150, 20);

    JButton buttonAddChamado = new JButton("Cadastrar");
    buttonAddChamado.setBounds(130, 220, 100, 30);
    add(buttonAddChamado);
    dao.chamadoEmEdicao(chamado);

    trajetoFimTxFld.setText(chamado.getTrajetoFim());
    veiculoTxFld.setSelectedItem(chamado.getVeiculo().getModelo());
    funcionarioTxFld.setSelectedItem(chamado.getFuncionario().getNome());

    buttonAddChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int id = chamado.getId();
        Veiculo veiculo = new Veiculo();
        Funcionario funcionario = new Funcionario(null, null, false);

        Chamado chamado = new Chamado();
        chamado.setVeiculo(veiculo);
        chamado.setFuncionario(funcionario);

        chamado.setTrajetoFim(trajetoFimTxFld.getText());
        chamado.getVeiculo().setId(veiculoDao.consultar().get(veiculoTxFld.getSelectedIndex()).getId());
        chamado.getFuncionario().setId(funcionarioDao.consultar().get(funcionarioTxFld.getSelectedIndex()).getId());
        chamado.setId(id);
        funcionarioDao.funcionarioEmEdicao(chamado.getFuncionario());
        if(chamado.getFuncionario().isHabilitado()){
        if (dao.editar(chamado)) {

          JOptionPane.showMessageDialog(null, "Chamado editado com sucesso!");
          setVisible(false);
          new InterfaceListChamado();
        } else {

          JOptionPane.showMessageDialog(null, "Algo de errado aconteceu! Tente Novamente!");

        }}else {

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
