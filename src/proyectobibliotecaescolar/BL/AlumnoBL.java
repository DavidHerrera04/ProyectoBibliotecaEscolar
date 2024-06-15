package proyectobibliotecaescolar.BL;
import proyectobibliotecaescolar.DAL.AlumnoDAL;
import proyectobibliotecaescolar.EN.Alumno;
import java.util.ArrayList;
import java.util.ArrayList;

public class AlumnoBL {
    public ArrayList<Alumno> obtenerTodos() throws Exception
    {
        return AlumnoDAL.obtenerTodos();
    }
    public int crear(Alumno pAlumno) throws Exception
    {
        return AlumnoDAL.crear(pAlumno);
    }
    public int modificar(Alumno pAlumno) throws Exception
    {
        return AlumnoDAL.modificar(pAlumno);
    }
    public int eliminar(Alumno pAlumno) throws Exception
    {
        return AlumnoDAL.eliminar(pAlumno);
    }
    public ArrayList<Alumno> obtenerPorId(Alumno pAlumno) throws Exception
    {
        return AlumnoDAL.obtenerPorId(pAlumno);
    }
    public ArrayList<Alumno> buscar(Alumno pAlumno) throws Exception
    {
        return AlumnoDAL.buscar(pAlumno);
    }
}
