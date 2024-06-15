package proyectobibliotecaescolar.DAL;

import proyectobibliotecaescolar.EN.Alumno;
import java.util.*;
import java.sql.*;

public class AlumnoDAL {
    public static ArrayList<Alumno> obtenerTodos() throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = " Select * From Alumno " +
                    " Order by Id Desc ";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt(1));
                    alumno.setNombre(rs.getString(2));
                    alumno.setApellido(rs.getString(3));
                    alumno.setDireccion(rs.getString(4));
                    alumno.setTelefono(rs.getString(5));
                    alumnos.add(alumno);
                }
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return alumnos;
    }

    public static int crear(Alumno pAlumno) throws Exception {
        PreparedStatement pstatement;
        int result = 0;
        String sql = "";
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Insert Into Alumno(Nombre, Apellido, Direccion, Telefono)" +
                    " Values(?,?,?,?)";
            try {
                pstatement = conn.prepareStatement(sql);
                pstatement.setString(1, pAlumno.getNombre());
                pstatement.setString(2, pAlumno.getApellido());
                pstatement.setString(3, pAlumno.getDireccion());
                pstatement.setString(4, pAlumno.getTelefono());
                result = pstatement.executeUpdate();
                pstatement.close();
            } catch (Exception ex) {
                throw ex;
            }
            return result;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public static int modificar(Alumno pAlumno) throws Exception {
        PreparedStatement pstatement;
        int result = 0;
        String sql = "";
        try (Connection con = ComunDB.obtenerConexion();) {
            sql = "Update Alumno set Nombre = ?, Apellido = ?, Direccion = ?, Telefono = ? " +
                    "Where Id = ?";
            try {
                pstatement = con.prepareStatement(sql);
                pstatement.setString(1, pAlumno.getNombre());
                pstatement.setString(2, pAlumno.getApellido());
                pstatement.setString(3, pAlumno.getDireccion());
                pstatement.setString(4, pAlumno.getTelefono());
                pstatement.setInt(5, pAlumno.getId());
                result = pstatement.executeUpdate();
                pstatement.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int eliminar(Alumno pAlumno) throws Exception {
        int result = 0;
        String sql = "";
        PreparedStatement pstament;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "Delete From Alumno Where Id = ?";
            try {
                pstament = conn.prepareStatement(sql);
                pstament.setInt(1, pAlumno.getId());
                result = pstament.executeUpdate();
                pstament.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static ArrayList<Alumno> obtenerPorId(Alumno pAlumno) throws Exception {
        PreparedStatement ps;//consultar datos
        ResultSet rs;//Recibir datos de la consulta
        ArrayList<Alumno> alumnos = new ArrayList<>();//lo que vamos a obtener de la consulta
        Alumno alum = new Alumno();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = " Select * From Alumno " +
                    " Where Id = ? " +
                    " Order by Id Desc ";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, pAlumno.getId());
                rs = ps.executeQuery();
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt(1));
                    alumno.setNombre(rs.getString(2));
                    alumno.setApellido(rs.getString(3));
                    alumno.setDireccion(rs.getString(4));
                    alumno.setTelefono(rs.getString(5));
                    alumnos.add(alumno);
                }
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return alumnos;
    }

    public static ArrayList<Alumno> buscar(Alumno pAlumno) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Alumno> alumnos = new ArrayList<>();
        int indice = 0;
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = " Select * From Alumno ";
            if (pAlumno.getNombre() != null && pAlumno.getNombre().trim().isEmpty() == false) {
                sql += "Where Nombre Like ?";
            } else if (pAlumno.getApellido() != null && pAlumno.getApellido().trim().isEmpty() == false) {
                sql += " Where Apellido Like ?";
            }
            sql += " Order by Id Desc ";

            try {
                ps = conn.prepareStatement(sql);
                if (pAlumno.getNombre() != null && pAlumno.getNombre().trim().isEmpty() == false) {
                    ps.setString(1, "%" + pAlumno.getNombre() + "%");
                }

                if (pAlumno.getApellido() != null && pAlumno.getApellido().trim().isEmpty() == false) {
                    ps.setString(1, "%" + pAlumno.getApellido() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setId(rs.getInt(1));
                    alumno.setNombre(rs.getString(2));
                    alumno.setApellido(rs.getString(3));
                    alumno.setDireccion(rs.getString(4));
                    alumno.setTelefono(rs.getString(5));
                    alumnos.add(alumno);
                }
                ps.close();
            } catch (Exception ex) {
                throw ex;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return alumnos;
    }
}
