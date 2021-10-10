package interfaceVeiculo;


import DAO.FuncionarioDao;
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
import model.Funcionario;
import model.Veiculo;

public class InterfaceCadastroVeiculo extends JFrame{

  @Serial
  private static final long serialVersionUID = -4349720804436041125L;

  public InterfaceCadastroVeiculo(){
    super();
    setSize(400,500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );


    JLabel modeloLabel = new JLabel();
    modeloLabel.setText("Modelo:");
    modeloLabel.setBounds(60,90,100, 40);
    add(modeloLabel);

    JTextField modeloTxFld = new JTextField();
    modeloTxFld.setBounds(130,100,100, 40);
    modeloTxFld.setSize(150,20);
    add(modeloTxFld);

    JLabel autonomiaLabel = new JLabel();
    autonomiaLabel.setText("Autonomia:");
    autonomiaLabel.setBounds(60,120,100, 40);
    add(autonomiaLabel);

    JTextField autonomiaTxFld = new JTextField();
    autonomiaTxFld.setBounds(130,130,100, 40);
    autonomiaTxFld.setSize(150,20);
    add(autonomiaTxFld);

    JLabel anoLabel = new JLabel();
    anoLabel.setText("Ano:");
    anoLabel.setBounds(60,150,100, 40);
    add(anoLabel);

    JTextField anoTxFld = new JTextField();
    anoTxFld.setBounds(130,160,100, 40);
    anoTxFld.setSize(150,20);
    add(anoTxFld);

    JLabel quantidadeLabel = new JLabel();
    quantidadeLabel.setText("Quantidade:");
    quantidadeLabel.setBounds(60,180,100, 40);
    add(quantidadeLabel);

    JTextField quantidadeTxFld = new JTextField();
    quantidadeTxFld.setBounds(130,190,100, 40);
    quantidadeTxFld.setSize(150,20);
    add(quantidadeTxFld);

    JButton buttonAddVeiculo = new JButton("Cadastrar");
    buttonAddVeiculo.setBounds(130,220,100, 30);
    add(buttonAddVeiculo);

    buttonAddVeiculo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        VeiculoDao dao = new VeiculoDao();
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo(modeloTxFld.getText());
        veiculo.setAutonomia(Integer.parseInt(autonomiaTxFld.getText()));
        veiculo.setAno(anoTxFld.getText());
        veiculo.setQuantidade(Integer.parseInt(quantidadeTxFld.getText()));

        if (dao.inserir(veiculo)){

          JOptionPane.showMessageDialog(null,"Veiculo salvo com sucesso!");
          setVisible(false);
          new InterfaceListVeiculo();
        }else {

          JOptionPane.showMessageDialog(null,"Algo de errado aconteceu! Tente Novamente!");

        }
      }
    });



    setVisible(true);
  }

}
