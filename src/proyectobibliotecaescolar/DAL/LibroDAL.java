package proyectobibliotecaescolar.DAL;

import proyectobibliotecaescolar.EN.Libro;
import java.util.*;
import java.sql.*;

public class LibroDAL {
    public static ArrayList<Libro> obtenerTodos() throws Exception
    {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Libro> libros = new ArrayList<>();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = " Select * From Libro " +
                    " Order by Id Desc ";
            try
            {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Libro libro = new Libro();
                    libro.setId(rs.getInt(1));
                    libro.setNombre(rs.getString(2));
                    libro.setAutor(rs.getString(3));
                    libro.setPublicacion(rs.getString(4));
                    libros.add(libro);
                }
                ps.close();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        return libros;
    }

    public static int crear(Libro pLibro) throws Exception
    {

        PreparedStatement ps;
        int result = 0;
        String sql = "";
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Libro(Nombre, Autor, Publicacion)" +
                    " Values(?,?,?)";
            try
            {
                ps = conn.prepareStatement(sql);
                ps.setString(1, pLibro.getNombre());
                ps.setString(2, pLibro.getAutor());
                ps.setString(3, pLibro.getPublicacion());
                result = ps.executeUpdate();
                ps.close();
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return result;
        }
        catch (SQLException ex)
        {
            throw ex;
        }
    }

    public static int modificar(Libro pLibro)throws Exception
    {
        PreparedStatement pstatement;
        int result = 0;
        String sql = "";
        try(Connection con = ComunDB.obtenerConexion();)
        {
            sql = "Update Libro set Nombre = ?, Autor = ?, Publicacion = ?" +
                    "Where Id = ?";

            try{
                pstatement = con.prepareStatement(sql);
                pstatement.setString(1, pLibro.getNombre());
                pstatement.setString(2, pLibro.getAutor());
                pstatement.setString(3, pLibro.getPublicacion());
                pstatement.setInt(4, pLibro.getId());
                result = pstatement.executeUpdate();
                pstatement.close();
            }
            catch(Exception ex)
            {
                throw ex;
            }
            return result;
        }
        catch (SQLException ex)
        {
            throw ex;
        }

    }

    public static int eliminar(Libro pLibro) throws Exception{
        int result = 0;
        String sql = "";
        PreparedStatement pstament;
        try(Connection coon = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Libro Where Id = ?";
            try {
                pstament = coon.prepareStatement(sql);
                pstament.setInt(1, pLibro.getId());
                result = pstament.executeUpdate();
                pstament.close();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        catch (SQLException ex)
        {
            throw  ex;
        }
        return result;
    }

    public static ArrayList<Libro> obtenerPorId(Libro pLibro) throws Exception
    {
        PreparedStatement ps;
        ResultSet rs;//Recibir datos de la consulta
        ArrayList<Libro> libros = new ArrayList<>();
        Libro empl = new Libro();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = " Select * From Libro" +
                    " Where Id = ? " +
                    " Order by Id Desc ";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, pLibro.getId());
                rs = ps.executeQuery();
                while (rs.next())
                {
                    Libro libro = new Libro();
                    libro.setId(rs.getInt(1));
                    libro.setNombre(rs.getString(2));
                    libro.setAutor(rs.getString(3));
                    libro.setPublicacion(rs.getString(4));
                    libros.add(libro);
                }
                ps.close();
            }
            catch (Exception ex)
            {
                throw  ex;
            }
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        /*if (libros.size() > 0)
        {
            empl = libros.get(0);
        }*/
        return libros;
    }

    public static ArrayList<Libro> buscar(Libro pLibro) throws Exception
    {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Libro> libros = new ArrayList<>();
        int indice = 0;
        try(Connection conn = ComunDB.obtenerConexion())
        {
            String sql = " Select * From Libro";
            if(pLibro.getNombre() != null &&
                    pLibro.getNombre().trim().isEmpty() == false)
            {
                sql += " Where Nombre Like ?";
            }else
            if(pLibro.getAutor() != null &&
                    pLibro.getAutor().trim().isEmpty() == false)
            {
                sql += " Where Autor Like ?";
            }
            sql += " Order by Id Desc ";

            try
            {
                ps = conn.prepareStatement(sql);
                if (pLibro.getNombre() != null &&
                        pLibro.getNombre().trim().isEmpty() == false)
                {
                    ps.setString(1, "%" + pLibro.getNombre() + "%");
                }

                if (pLibro.getAutor() != null &&
                        pLibro.getAutor().trim().isEmpty() == false)
                {
                    ps.setString(1, "%" + pLibro.getAutor() + "%");
                }
                rs = ps.executeQuery();
                while(rs.next())
                {
                    Libro libro = new Libro();
                    libro.setId(rs.getInt(1));
                    libro.setNombre(rs.getString(2));
                    libro.setAutor(rs.getString(3));
                    libro.setPublicacion(rs.getString(4));
                    libros.add(libro);
                }
                ps.close();
            }
            catch (Exception ex)
            {
                throw  ex;
            }
        }
        catch (SQLException ex)
        {
            throw ex;
        }
        return libros;
    }
}
