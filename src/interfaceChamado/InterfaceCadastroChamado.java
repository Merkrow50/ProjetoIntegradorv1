package interfaceChamado;


import DAO.ChamadoDao;
import DAO.VeiculoDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Chamado;
import model.Funcionario;
import model.Veiculo;

public class InterfaceCadastroChamado extends JFrame{

  @Serial
  private static final long serialVersionUID = -4349720804436041125L;

  public InterfaceCadastroChamado(){
    super();
    setSize(400,500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );


    JLabel funcionarioLabel = new JLabel();
    funcionarioLabel.setText("Modelo:");
    funcionarioLabel.setBounds(60,90,100, 40);
    add(funcionarioLabel);

    JTextField funcionarioTxFld = new JTextField();
    funcionarioTxFld.setBounds(130,100,100, 40);
    funcionarioTxFld.setSize(150,20);
    add(funcionarioTxFld);

    JLabel veiculoLabel = new JLabel();
    veiculoLabel.setText("Autonomia:");
    veiculoLabel.setBounds(60,120,100, 40);
    add(veiculoLabel);

    JTextField veiculoTxFld = new JTextField();
    veiculoTxFld.setBounds(130,130,100, 40);
    veiculoTxFld.setSize(150,20);
    add(veiculoTxFld);

    JLabel trajetoLabel = new JLabel();
    trajetoLabel.setText("Ano:");
    trajetoLabel.setBounds(60,150,100, 40);
    add(trajetoLabel);

    JTextField trajetoTxFld = new JTextField();
    trajetoTxFld.setBounds(130,160,100, 40);
    trajetoTxFld.setSize(150,20);
    add(trajetoTxFld);

    JButton buttonAddChamado = new JButton("Cadastrar");
    buttonAddChamado.setBounds(130,220,100, 30);
    add(buttonAddChamado);

    buttonAddChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ChamadoDao dao = new ChamadoDao();
        Chamado chamado = new Chamado();

//        chamado.setFuncionario(funcionarioTxFld.getText());
//        chamado.setVeiculo(veiculoTxFld.getText());
//        chamado.setTrajeto(trajetoTxFld.getText());

        if (dao.inserir(chamado)){
          JOptionPane.showMessageDialog(null,"Chamado salvo com sucesso!");
          setVisible(false);
          new InterfaceListChamado();
        }else {
          JOptionPane.showMessageDialog(null,"Algo de errado aconteceu! Tente Novamente!");
        }
      }
    });
    setVisible(true);
  }

}
