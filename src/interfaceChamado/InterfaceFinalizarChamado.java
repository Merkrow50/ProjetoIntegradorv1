package interfaceChamado;

import DAO.ChamadoDao;
import DAO.FuncionarioDao;
import DAO.VeiculoDao;
import interfaceFuncionario.InterfaceListFuncionario;
import interfaceFuncionario.IsHabilitado;
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

public class InterfaceFinalizarChamado extends JFrame {

  @Serial
  private static final long serialVersionUID = -2783976265694627018L;

  public InterfaceFinalizarChamado(Chamado chamado){
    super();
    setSize(400,500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );


    JLabel nomeLabel = new JLabel();
    nomeLabel.setText("Kilometragem Total");
    nomeLabel.setBounds(130,65,150, 40);
    add(nomeLabel);

    JTextField nomeTxFld = new JTextField();
    nomeTxFld.setBounds(110,100,100, 40);
    nomeTxFld.setSize(150,20);
    add(nomeTxFld);



    JButton buttonAddFuncionario = new JButton("Gerar Pegada de CO2");
    buttonAddFuncionario.setBounds(130,200,100, 30);
    add(buttonAddFuncionario);

    buttonAddFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        VeiculoDao veiculoDao = new VeiculoDao();
        ChamadoDao chamadoDao = new ChamadoDao();

        chamadoDao.chamadoEmEdicao(chamado);

        veiculoDao.veiculoEmEdicao(chamado.getVeiculo());

       float km = Float.parseFloat(nomeTxFld.getText());
       float CG = km / chamado.getVeiculo().getAutonomia();
       float pegada = (float) (CG * 0.73 * 0.75 * 3.7);

        new GeneratorPDF(pegada, chamado);

        chamadoDao.deletar(chamado);

        new InterfaceListChamado();

      }
    });
    setVisible(true);
  }

}
