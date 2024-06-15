package proyectobibliotecaescolar.BL;
import proyectobibliotecaescolar.DAL.LibroDAL;
import proyectobibliotecaescolar.EN.Libro;
import java.util.ArrayList;

public class LibroBL {
    public ArrayList<Libro> obtenerTodos() throws Exception
    {
        return LibroDAL.obtenerTodos();
    }
    public int crear(Libro pEmpleado) throws Exception
    {
        return LibroDAL.crear(pEmpleado);
    }
    public  int modificar(Libro pLibro)throws Exception
    {
        return  LibroDAL.modificar(pLibro);
    }
    public  int eliminar(Libro pLibro) throws Exception
    {
        return LibroDAL.eliminar(pLibro);
    }
    public  ArrayList<Libro> ObtenerPorId(Libro pLibro) throws Exception
    {
        return  LibroDAL.obtenerPorId(pLibro);
    }
    public ArrayList<Libro> buscar(Libro pLibro) throws Exception
    {
        return LibroDAL.buscar(pLibro);
    }
}
