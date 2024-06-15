package proyectobibliotecaescolar.IU;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import proyectobibliotecaescolar.EN.Alumno;
import proyectobibliotecaescolar.BL.AlumnoBL;
import javax.swing.table.DefaultTableModel;

public class FrmMantenimientoAlum {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton nuevoButton;
    private JButton editarButton;
    private JRadioButton idRadioButton;
    private JRadioButton nombreRadioButton;
    private JRadioButton apellidoRadioButton;
    private JTextField txtBusqueda;
    private JButton buscarButton;
    private JTable jtAlumno;
    private JPanel JMainMantenimiento;

    AlumnoBL alumnobl = new AlumnoBL();
    private boolean validarCampos() {
        return !txtNombre.getText().trim().isEmpty() &&
                !txtApellido.getText().trim().isEmpty() &&
                !txtDireccion.getText().trim().isEmpty() &&
                !txtTelefono.getText().trim().isEmpty();
    }

    public static void main(String[] args) throws Exception
    {
        JFrame frame = new JFrame("FrmMantenimientoAlum");
        frame.setContentPane(new FrmMantenimientoAlum().JMainMantenimiento);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400));///cambio de tamaño
        frame.pack();
        frame.setLocationRelativeTo(null);////VISIBLE EN EL CENTRO
        frame.setVisible(true);
    }

    public FrmMantenimientoAlum() throws Exception
    {
        ActualizarForm();
        bloqueartext();
        buscarButton.setEnabled(false);
        jtAlumno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = jtAlumno.getSelectedRow();
                txtId.setText(jtAlumno.getValueAt(fila, 0).toString());
                txtNombre.setText(jtAlumno.getValueAt(fila, 1).toString());
                txtApellido.setText(jtAlumno.getValueAt(fila, 2).toString());
                txtDireccion.setText(jtAlumno.getValueAt(fila, 3).toString());
                txtTelefono.setText(jtAlumno.getValueAt(fila, 4).toString());

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
                    alum = new Alumno();
                    alum.setId(0);
                    alum.setNombre(txtNombre.getText());
                    alum.setApellido(txtApellido.getText());
                    alum.setDireccion(txtDireccion.getText());
                    alum.setTelefono(txtTelefono.getText());

                    resultado = alumnobl.crear(alum);
                    if(resultado == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Registro ingresado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        ActualizarForm();
                        limpiartext();
                        bloqueartext();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Registro ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (Exception ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int resultado = 0;
                    alum = new Alumno();
                    alum.setId(Integer.parseInt(txtId.getText()));
                    alum.setNombre(txtNombre.getText());
                    alum.setApellido(txtApellido.getText());
                    alum.setDireccion(txtDireccion.getText());
                    alum.setTelefono(txtTelefono.getText());

                    resultado = alumnobl.modificar(alum);
                    if(resultado == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Registro modificado exitosamente", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                        ActualizarForm();
                        bloqueartext();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error al modificar el registro", "Error", JOptionPane.ERROR_MESSAGE);
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
                limpiartext();
                lista = new ArrayList<>();
            }
        });

        txtBusqueda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                try
                {
                    Alumno alumno = new Alumno();
                    if (nombreRadioButton.isSelected())
                    {
                        alumno.setNombre(txtBusqueda.getText());
                        llenar(alumnobl.buscar(alumno));
                    }
                    if (apellidoRadioButton.isSelected())
                    {
                        alumno.setApellido(txtBusqueda.getText());
                        llenar(alumnobl.buscar(alumno));
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

        apellidoRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarButton.setEnabled(false);
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alumno alumbusqueda = new Alumno();
                alumbusqueda.setId(Integer.parseInt(txtBusqueda.getText()));
                try {
                    llenar(alumnobl.obtenerPorId(alumbusqueda));
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
                        alum = new Alumno();
                        alum.setId(Integer.parseInt(txtId.getText()));

                        resultado = alumnobl.eliminar(alum);
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
        llenar(alumnobl.obtenerTodos());

        guardarButton.setEnabled(false);
        editarButton.setEnabled(false);
        eliminarButton.setEnabled(false);
    }

    public void desbloqueartext()
    {
        txtId.setEnabled(true);
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
    }

    public void bloqueartext()
    {
        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
    }
    public void limpiartext()
    {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
    ArrayList<Alumno> lista;//muestra los datos que se les da click en la tabla
    Alumno alum;
    public void llenar(ArrayList<Alumno> pLista)
    {
        Object[] obj = new Object[10];
        lista = new ArrayList<>();
        String[] title = {"Id","Nombre", "Apellido", "Direccion", "Telefono" };
        DefaultTableModel tm = new DefaultTableModel(null, title);
        lista.addAll(pLista);
        for (Alumno al : lista)
        {
            obj[0] = al.getId();
            obj[1] = al.getNombre();
            obj[2] = al.getApellido();
            obj[3] = al.getDireccion();
            obj[4] = al.getTelefono();
            tm.addRow(obj);
        }
        jtAlumno.setModel(tm);
    }
}
