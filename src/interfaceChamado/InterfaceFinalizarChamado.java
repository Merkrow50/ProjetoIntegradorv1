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
    setLabel(nomeLabel,"Kilometragem Total:",130,65,150, 40);

    JTextField nomeTxFld = new JTextField();
    setTxtField(nomeTxFld,110,100,150, 20);

    JButton buttonAddFuncionario = new JButton("Gerar Pegada de CO2");
    buttonAddFuncionario.setBounds(90,200,200, 30);
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

        new GeneratorPDF(pegada, chamado, km);

        if(chamadoDao.deletar(chamado)){
          JOptionPane.showMessageDialog(null, "Chamado finalizado com sucesso!\nAcesse seu disco C para ver o relatório referente a este chamado.");
        }else {
          JOptionPane.showMessageDialog(null, "Algo de errado aconteceu! Tente Novamente!");
        };
        setVisible(false);
        new InterfaceListChamado();

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
