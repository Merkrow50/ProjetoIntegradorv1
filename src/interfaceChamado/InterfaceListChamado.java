package interfaceChamado;

import DAO.ChamadoDao;
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
import model.Chamado;
import model.Funcionario;
import model.Veiculo;


public class InterfaceListChamado extends JFrame {

  @Serial
  private static final long serialVersionUID = 6833478251487492489L;

  ChamadoDao dao = new ChamadoDao();
  FuncionarioDao daoFuncionario = new FuncionarioDao();
  JTable table = new JTable();

  public InterfaceListChamado() {
    super();
    setSize(400, 500);
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
    buttonDelete.setBounds(240, 20, 100, 30);
    add(buttonEdit);


    scrollPane.setViewportView(table);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.addColumn("Trajeto");
    model.addColumn("Id");
    model.setNumRows(0);
    for (Chamado c : dao.consultar()) {

      for (int i = 0; i < 1; i++) {

        model.addRow(new Object[]{

            c.getTrajetoInicio(),
            c.getTrajetoFim(),
            c.getId(),

        });

      }
    };


    add(scrollPane);
    setSize(500, 600);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);

    // ACTION LISTENER DO BOTÃO DE EDITAR
    buttonEdit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1){

          Chamado c1 = new Chamado();
          c1.setTrajetoInicio((String) table.getValueAt(table.getSelectedRow(),1));
          c1.setTrajetoFim((String) table.getValueAt(table.getSelectedRow(),2));

          Funcionario f1 = new Funcionario(null,null);
          f1.setNome((String) table.getValueAt(table.getSelectedRow(),0));
          f1.setMatricula((String) table.getValueAt(table.getSelectedRow(),1));
          f1.setId((int) table.getValueAt(table.getSelectedRow(),2));

          daoFuncionario.editar(f1);
          model.setNumRows(0);
          for (Funcionario f : daoFuncionario.consultar()) {

            for (int i = 0; i < 1; i++) {

              model.addRow(new Object[]{

                  f.getNome(),
                  f.getMatricula(),
                  f.getId(),

              });

            }

          dao.editar(c1);
          model.setNumRows(0);
          for (Chamado c : dao.consultar()) {

            for (int i = 0; i < 1; i++) {

              model.addRow(new Object[]{

                  c.getTrajetoInicio(),
                  c.getTrajetoFim(),
                  c.getId(),

              });

            }
          };

        }else {

          JOptionPane.showMessageDialog(null,"Edite o Chamado e selecione a linha após isso click em editar!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE DELETAR
    buttonDelete.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRow() != -1){

          Chamado c1 = new Chamado();
          c1.setId((int) table.getValueAt(table.getSelectedRow(),4));

          dao.deletar(c1);
          model.setNumRows(0);
          for (Chamado c : dao.consultar()) {

            for (int i = 0; i < 1; i++) {

              model.addRow(new Object[]{

                  c.getFuncionario(),
                  c.getVeiculo(),
                  c.getTrajeto(),
                  c.getId(),

              });

            }
          };

        }else {

          JOptionPane.showMessageDialog(null,"Selecione um Chamado para excluir!");

        }
      }
    });

// ACTION LISTENER DO BOTÃO DE NOVO FUNCIONÁRIO
    buttonNewChamado.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new InterfaceCadastroChamado();
      }
    });

    setResizable(false);
    setVisible(true);
  }




}
