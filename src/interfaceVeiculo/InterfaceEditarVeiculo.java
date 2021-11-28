package interfaceVeiculo;

import DAO.VeiculoDao;
import interfaceFuncionario.IsHabilitado;
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

public class InterfaceEditarVeiculo extends JFrame {

  @Serial
  private static final long serialVersionUID = 6274683761440960739L;
  VeiculoDao dao = new VeiculoDao();


  public InterfaceEditarVeiculo(Veiculo veiculo) {
    super();
    setSize(400, 500);
    this.setLocationRelativeTo(null);
    Container contentPane = getContentPane();

    contentPane.setLayout(
        null
    );
    JLabel modeloLabel = new JLabel();
    modeloLabel.setText("Modelo:");
    modeloLabel.setBounds(60, 90, 100, 40);
    add(modeloLabel);

    JTextField modeloTxFld = new JTextField();
    modeloTxFld.setBounds(130, 100, 100, 40);
    modeloTxFld.setSize(150, 20);
    add(modeloTxFld);

    JLabel autonomiaLabel = new JLabel();
    autonomiaLabel.setText("Autonomia:");
    autonomiaLabel.setBounds(60, 120, 100, 40);
    add(autonomiaLabel);

    JTextField autonomiaTxFld = new JTextField();
    autonomiaTxFld.setBounds(130, 130, 100, 40);
    autonomiaTxFld.setSize(150, 20);
    add(autonomiaTxFld);

    JLabel anoLabel = new JLabel();
    anoLabel.setText("Ano:");
    anoLabel.setBounds(60, 150, 100, 40);
    add(anoLabel);

    JTextField anoTxFld = new JTextField();
    anoTxFld.setBounds(130, 160, 100, 40);
    anoTxFld.setSize(150, 20);
    add(anoTxFld);

    JLabel quantidadeLabel = new JLabel();
    quantidadeLabel.setText("Quantidade:");
    quantidadeLabel.setBounds(60, 180, 100, 40);
    add(quantidadeLabel);

    JTextField quantidadeTxFld = new JTextField();
    quantidadeTxFld.setBounds(130, 190, 100, 40);
    quantidadeTxFld.setSize(150, 20);
    add(quantidadeTxFld);

    JButton buttonAddVeiculo = new JButton("Cadastrar");
    buttonAddVeiculo.setBounds(130, 220, 100, 30);
    add(buttonAddVeiculo);

    dao.veiculoEmEdicao(veiculo);

    modeloTxFld.setText(veiculo.getModelo());
    anoTxFld.setText(String.valueOf(veiculo.getAno()));
    autonomiaTxFld.setText(String.valueOf(veiculo.getAutonomia()));
    quantidadeTxFld.setText(String.valueOf(veiculo.getQuantidade()));

    buttonAddVeiculo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Veiculo veiculo1 = new Veiculo();

        int id = veiculo.getId();

        System.out.println(Float.parseFloat((autonomiaTxFld.getText())));

        veiculo1.setModelo(modeloTxFld.getText());
        veiculo1.setAutonomia(Float.parseFloat((autonomiaTxFld.getText())));
        veiculo1.setAno((Integer.parseInt(anoTxFld.getText())));
        veiculo1.setQuantidade(Integer.parseInt(quantidadeTxFld.getText()));
        veiculo1.setId(id);

        if (dao.editar(veiculo1)) {

          JOptionPane.showMessageDialog(null, "Veiculo salvo com sucesso!");
          setVisible(false);
          new InterfaceListVeiculo();
        } else {

          JOptionPane.showMessageDialog(null, "Algo de errado aconteceu! Tente Novamente!");

        }

      }
    });

    setVisible(true);
  }

}


