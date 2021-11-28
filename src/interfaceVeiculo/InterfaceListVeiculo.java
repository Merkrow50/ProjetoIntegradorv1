package interfaceVeiculo;

import DAO.FuncionarioDao;
import DAO.VeiculoDao;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import model.Veiculo;


public class InterfaceListVeiculo extends JFrame {

  @Serial
  private static final long serialVersionUID = 6833478251487492489L;

  VeiculoDao dao = new VeiculoDao();
  JTable table = new JTable();

  public InterfaceListVeiculo() {
    super();
    setSize(400, 500);
    this.setLocationRelativeTo(null);
    JScrollPane scrollPane = new JScrollPane();
    setLayout(
        new FlowLayout()
    );

    JButton buttonNewFuncionario = new JButton("Novo");
    buttonNewFuncionario.setBounds(260, 20, 100, 30);
    add(buttonNewFuncionario);

    JButton buttonDelete = new JButton("Deletar");
    buttonDelete.setBounds(240, 20, 100, 30);
    add(buttonDelete);

    JButton buttonEdit = new JButton("Editar");
    buttonDelete.setBounds(240, 20, 100, 30);
    add(buttonEdit);

    scrollPane.setViewportView(table);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.addColumn("Modelo");
    model.addColumn("Autonomia");
    model.addColumn("Ano");
    model.addColumn("Quantidade");
    model.addColumn("Id");
    model.setNumRows(0);
    for (Veiculo v : dao.consultar()) {

      for (int i = 0; i < 1; i++) {

        model.addRow(new Object[]{

            v.getModelo(),
            v.getAutonomia(),
            v.getAno(),
            v.getQuantidade(),
            v.getId(),

        });

      }
    }
    ;

    add(scrollPane);
    setSize(500, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);

    // ACTION LISTENER DO BOTÃO DE EDITAR
    buttonEdit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Veiculo v1 = new Veiculo();
//          v1.setModelo((String) table.getValueAt(table.getSelectedRow(), 0));
//          v1.setAutonomia(Float.parseFloat((table.getValueAt(table.getSelectedRow(), 1).toString())));
//          v1.setAno(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString()));
//          v1.setQuantidade(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
          v1.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString()));

          setVisible(false);
          new InterfaceEditarVeiculo(v1);


        } else {

          JOptionPane.showMessageDialog(null, "Edite o Veiculo e selecione a linha após isso click em editar!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE DELETAR
    buttonDelete.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Veiculo v1 = new Veiculo();
          v1.setId((int) table.getValueAt(table.getSelectedRow(), 4));

          dao.deletar(v1);
          model.setNumRows(0);
          for (Veiculo v : dao.consultar()) {

            for (int i = 0; i < 1; i++) {

              model.addRow(new Object[]{

                  v.getModelo(),
                  v.getAutonomia(),
                  v.getAno(),
                  v.getQuantidade(),
                  v.getId(),

              });

            }
          }
          ;

        } else {

          JOptionPane.showMessageDialog(null, "Selecione um Veiculo para excluir!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE NOVO FUNCIONÁRIO
    buttonNewFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new InterfaceCadastroVeiculo();
      }
    });

    setResizable(false);
    setVisible(true);
  }


}
