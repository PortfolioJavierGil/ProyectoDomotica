import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class ObservadorTest extends TestCase {
    @Test
    public void testAddAula() {
        Observador observador = new Observador("prueba","Prueba","pruebaA","prueba@upm.es");
        Aula aula = new Aula("1","1",200,200,TAula.mixta,1);
        observador.addAula(aula);
        assertEquals("addAula",aula,observador.listaAulas.get(0));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAula() {
        Observador observador = new Observador("prueba","Prueba","pruebaA","prueba@upm.es");
        Aula aula = new Aula("1","1",200,200,TAula.mixta,1);
        observador.removeAula(aula);
        assertEquals("addAula",true,observador.listaAulas.isEmpty());
    }
    @Test
    public void testMostrarAulasSubscripcion() {
        Observador observador = new Observador("prueba","Prueba","pruebaA","prueba@upm.es");
        Aula aula = new Aula("1","1",200,200,TAula.mixta,1);
        assertEquals("mostrarAulasSubscripcion",null,observador.mostrarAulasSubscripcion(0));
    }
    @Test
    public void testGetNumSubscriciones() {
        Observador observador = new Observador("prueba","Prueba","pruebaA","prueba@upm.es");
        Aula aula = new Aula("1","1",200,200,TAula.mixta,1);
        observador.addAula(aula);
        assertEquals("getNumSubscripciones",1,observador.getNumSubscriciones());
    }
}