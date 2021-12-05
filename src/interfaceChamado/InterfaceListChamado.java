package interfaceChamado;

import DAO.ChamadoDao;
import DAO.FuncionarioDao;
import DAO.VeiculoDao;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Chamado;
import model.Funcionario;
import model.Veiculo;


public class InterfaceListChamado extends JFrame {

  @Serial
  private static final long serialVersionUID = 6833478251487492489L;

  ChamadoDao dao = new ChamadoDao();
  FuncionarioDao funcionarioDao = new FuncionarioDao();
  VeiculoDao veiculoDao = new VeiculoDao();

  JTable table = new JTable();

  public InterfaceListChamado() {
    super();
    setSize(800, 1000);
    this.setLocationRelativeTo(null);
    JScrollPane scrollPane = new JScrollPane();
    setLayout(
        new FlowLayout()
    );

    JButton buttonNewChamado = new JButton("Novo");
    buttonNewChamado.setBounds(260, 20, 100, 30);
    add(buttonNewChamado);

    JButton buttonDelete = new JButton("Deletar");
    buttonDelete.setBounds(240, 20, 100, 30);
    add(buttonDelete);

    JButton buttonEdit = new JButton("Editar");
    buttonEdit.setBounds(240, 20, 100, 30);
    add(buttonEdit);

    JButton buttonFinish = new JButton("Finalizar Chamado");
    buttonFinish.setBounds(240, 20, 100, 30);
    add(buttonFinish);

    scrollPane.setViewportView(table);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.addColumn("Destino");
    model.addColumn("Veiculo");
    model.addColumn("Funcionario");
    model.addColumn("id");
    model.setNumRows(0);
    Funcionario funcionario = new Funcionario(null, null, false);
    for (Chamado c : dao.consultar()) {
      for (int i = 0; i < 1; i++) {

        model.addRow(new Object[]{

            c.getTrajetoFim(),
            c.getVeiculo().getModelo(),
            c.getFuncionario().getNome(),
            c.getId()

        });

      }
    }
    ;

    add(scrollPane);
    setSize(600, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);

    // ACTION LISTENER DO BOTÃO DE EDITAR
    buttonEdit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Chamado c1 = new Chamado();
          c1.setId((int) table.getValueAt(table.getSelectedRow(), 3));
          setVisible(false);

          new InterfaceEditarChamado(c1);

        } else {

          JOptionPane.showMessageDialog(null, "Selecione um chamado para editar!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE DELETAR
    buttonDelete.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Chamado c1 = new Chamado();
          c1.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()));

          dao.deletar(c1);
          setVisible(false);
          new InterfaceListChamado();

        } else {

          JOptionPane.showMessageDialog(null, "Selecione um chamado para excluir!");

        }
      }
    });

    buttonFinish.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Chamado c1 = new Chamado();
          c1.setId(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString()));
          new InterfaceFinalizarChamado(c1);
          setVisible(false);


        } else {

          JOptionPane.showMessageDialog(null, "Selecione um chamado para finalizar!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE NOVO FUNCIONÁRIO
    buttonNewChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        try {
          new InterfaceCadastroChamado();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    });

    setResizable(false);
    setVisible(true);
  }


}
