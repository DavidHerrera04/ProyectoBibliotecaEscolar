package proyectobibliotecaescolar.IU;

import proyectobibliotecaescolar.BL.LibroBL;
import proyectobibliotecaescolar.EN.Alumno;
import proyectobibliotecaescolar.EN.Libro;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmMantenimientoLibr {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtAutor;
    private JTextField txtPublicacion;
    private JButton cancelarButton;
    private JButton nuevoButton;
    private JButton guardarButton;
    private JButton editarButton;
    private JButton eliminarButton;
    private JRadioButton idRadioButton;
    private JRadioButton nombreRadioButton;
    private JRadioButton autorRadioButton;
    private JTextField txtBusqueda;
    private JButton buscarButton;
    private JTable jtLibro;
    private JPanel jMainMantenimiento;

    LibroBL librobl = new LibroBL();

    private boolean validarCampos() {
        return !txtNombre.getText().trim().isEmpty() &&
                !txtAutor.getText().trim().isEmpty() &&
                !txtPublicacion.getText().trim().isEmpty();
    }

    public static void main(String[] args) throws Exception
    {
        JFrame frame = new JFrame("FrmMantenimientoLibr");
        frame.setContentPane(new FrmMantenimientoLibr().jMainMantenimiento);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));///cambio de tamaño
        frame.pack();
        frame.setLocationRelativeTo(null);////VISIBLE EN EL CENTRO
        frame.setVisible(true);
    }
    public FrmMantenimientoLibr() throws Exception {
        ActualizarForm();
        bloqueartext();
        buscarButton.setEnabled(false);
        jtLibro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = jtLibro.getSelectedRow();
                txtId.setText(jtLibro.getValueAt(fila, 0).toString());
                txtNombre.setText(jtLibro.getValueAt(fila, 1).toString());
                txtAutor.setText(jtLibro.getValueAt(fila, 2).toString());
                txtPublicacion.setText(jtLibro.getValueAt(fila, 3).toString());

                desbloqueartext();
                txtId.setEnabled(false);
                nuevoButton.setEnabled(false);
                guardarButton.setEnabled(false);
                editarButton.setEnabled(true);
                eliminarButton.setEnabled(true);
            }
        });
        nuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desbloqueartext();
                txtId.setEnabled(false);
                guardarButton.setEnabled(true);
                editarButton.setEnabled(false);
                eliminarButton.setEnabled(false);
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validarCampos()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    int resultado = 0;
                    emp = new Libro();
                    emp.setId(0);
                    emp.setNombre(txtNombre.getText());
                    emp.setAutor(txtAutor.getText());
                    emp.setPublicacion(txtPublicacion.getText());

                    resultado = librobl.crear(emp);
                    if (resultado == 1)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Registro ingresado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        ActualizarForm();
                        limpiartexts();
                        bloqueartext();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,
                                "Registro No ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int resultado = 0;
                    emp = new Libro();
                    emp.setId(Integer.parseInt(txtId.getText()));
                    emp.setNombre(txtNombre.getText());
                    emp.setAutor(txtAutor.getText());
                    emp.setPublicacion(txtPublicacion.getText());
                    resultado = librobl.modificar(emp);
                    if (resultado == 1)
                    {
                        JOptionPane.showMessageDialog(null,
                                "Registro ingresado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        ActualizarForm();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,
                                "Registro No ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bloqueartext();
                guardarButton.setEnabled(false);
                editarButton.setEnabled(false);
                eliminarButton.setEnabled(false);
                nuevoButton.setEnabled(true);
                limpiartexts();
                lista = new ArrayList<>();
            }
        });
        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try {
                    Libro libro = new Libro();
                    if (nombreRadioButton.isSelected())
                    {
                        libro.setNombre(txtBusqueda.getText());
                        llenar(librobl.buscar(libro));
                    }
                    if (autorRadioButton.isSelected())
                    {
                        libro.setAutor(txtBusqueda.getText());
                        llenar(librobl.buscar(libro));
                    }
                }
                catch (Exception ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });
        idRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarButton.setEnabled(true);

            }
        });
        nombreRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarButton.setEnabled(false);
            }
        });
        autorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarButton.setEnabled(false);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Libro empBusqueda = new Libro();
                empBusqueda.setId(Integer.parseInt(txtBusqueda.getText()));
                try {
                    llenar(librobl.ObtenerPorId(empBusqueda));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int resultado = 0;
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este registro?", "Eliminar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        emp = new Libro();
                        emp.setId(Integer.parseInt(txtId.getText()));

                        resultado = librobl.eliminar(emp);
                        if (resultado == 1) {
                            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                            ActualizarForm();
                            bloqueartext();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    public void ActualizarForm() throws Exception {
        llenar(librobl.obtenerTodos());

        guardarButton.setEnabled(false);
        editarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
    }

    public void desbloqueartext(){
        txtId.setEnabled(true);
        txtNombre.setEnabled(true);
        txtAutor.setEnabled(true);
        txtPublicacion.setEnabled(true);

    }
    public void bloqueartext(){
        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtAutor.setEnabled(false);
        txtPublicacion.setEnabled(false);

    }

    public void limpiartexts(){
        txtId.setText("");
        txtNombre.setText("");
        txtAutor.setText("");
        txtPublicacion.setText("");
    }

    ArrayList<Libro> lista;
    Libro emp;
    public void llenar(ArrayList<Libro> pLista)
    {
        Object[] obj = new Object[4];
        lista = new ArrayList<>();
        String[] title = {"Id", "Nombre", "Autor", "Publicacion"};
        DefaultTableModel tm = new DefaultTableModel(null, title);
        lista.addAll(pLista);
        for (Libro ep : lista)
        {
            obj[0] = ep.getId();
            obj[1] = ep.getNombre();
            obj[2] = ep.getAutor();
            obj[3] = ep.getPublicacion();
            tm.addRow(obj);
        }
        jtLibro.setModel(tm);
    }
}
