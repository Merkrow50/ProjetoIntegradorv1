package interfaceFuncionario;

import DAO.FuncionarioDao;
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


public class InterfaceListFuncionario extends JFrame {

  @Serial
  private static final long serialVersionUID = 6833478251487492489L;

  FuncionarioDao dao = new FuncionarioDao();
  JTable table = new JTable();

  public InterfaceListFuncionario() {
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
    model.addColumn("Nome");
    model.addColumn("Matricula");
    model.addColumn("Id");
    model.setNumRows(0);
    for (Funcionario f : dao.consultar()) {

      for (int i = 0; i < 1; i++) {

        model.addRow(new Object[]{

            f.getNome(),
            f.getMatricula(),
            f.getId(),

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

          Funcionario f1 = new Funcionario(null, null, false);
          f1.setId((int) table.getValueAt(table.getSelectedRow(), 2));

          setVisible(false);
          new InterfaceEditarFuncionario(f1);


        } else {

          JOptionPane.showMessageDialog(null, "Selecione um funcionário para editar!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE DELETAR
    buttonDelete.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1) {

          Funcionario f1 = new Funcionario(null, null, false);
          f1.setId((int) table.getValueAt(table.getSelectedRow(), 2));

          dao.deletar(f1);
          model.setNumRows(0);
          for (Funcionario f : dao.consultar()) {

            for (int i = 0; i < 1; i++) {

              model.addRow(new Object[]{

                  f.getNome(),
                  f.getMatricula(),
                  f.getId(),

              });

            }
          }
          ;

        } else {

          JOptionPane.showMessageDialog(null, "Selecione um funcionário para excluir!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE NOVO FUNCIONÁRIO
    buttonNewFuncionario.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new InterfaceCadastroFuncionario();
      }
    });

    setResizable(false);
    setVisible(true);
  }


}
