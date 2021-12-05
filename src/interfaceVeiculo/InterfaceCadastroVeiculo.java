package interfaceVeiculo;


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
    setLabel(modeloLabel,"Modelo:",80,90,100, 40);

    JTextField modeloTxFld = new JTextField();
    setTxtField(modeloTxFld,130,100,150, 20);

    JLabel autonomiaLabel = new JLabel();
    setLabel(autonomiaLabel,"Autonomia:",60,120,100, 40);

    JTextField autonomiaTxFld = new JTextField();
    setTxtField(autonomiaTxFld,130,130,150, 20);

    JLabel anoLabel = new JLabel();
    setLabel(anoLabel,"Ano:",100,150,100, 40);

    JTextField anoTxFld = new JTextField();
    setTxtField(anoTxFld,130,160,150, 20);

    JButton buttonAddVeiculo = new JButton("Cadastrar");
    buttonAddVeiculo.setBounds(130,220,100, 30);
    add(buttonAddVeiculo);

    buttonAddVeiculo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        VeiculoDao dao = new VeiculoDao();
        Veiculo veiculo = new Veiculo();

        veiculo.setModelo(modeloTxFld.getText());
        veiculo.setAutonomia(Float.parseFloat((autonomiaTxFld.getText())));
        veiculo.setAno((Integer.parseInt(anoTxFld.getText())));

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
