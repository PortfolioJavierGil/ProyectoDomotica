import junit.framework.TestCase;

public class AlumnoTest extends TestCase {

    public void testConstructorAlumnoVacio(){
        Alumno alumno=new Alumno();
        assertEquals("constructorAlumnoVacio","Alumno",alumno.getClass().getName());
    }

    public void testConstructorAlumno(){
        Alumno alumno = new Alumno("prueba","Prueba","pruebaA","prueba@upm.es","br0001");
        assertEquals("constructorAlumnoVacio","Alumno",alumno.getClass().getName());
    }

    public void testGetMatricula() {
        Alumno alumno = new Alumno("prueba","Prueba","pruebaA","prueba@upm.es","br0001");
        assertEquals("getMatricula","br0001",alumno.getMatricula());
    }

    public void testSetMatricula() {
        Alumno alumno = new Alumno("prueba","Prueba","pruebaA","prueba@upm.es","br0001");
        alumno.setMatricula("nueva");
        assertEquals("getMatricula","nueva",alumno.getMatricula());
    }
}