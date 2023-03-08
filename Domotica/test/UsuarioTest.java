import junit.framework.TestCase;
import org.junit.Test;
import utilidades.Cifrado;
import org.junit.runner.*;

import static org.junit.Assert.*;

public class UsuarioTest extends TestCase {


    @Test
    public void testGetDNI() {
        Usuario tester = new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        assertEquals("getDNI","prueba",tester.getDNI());
    }

    @Test
    public void testSetDNI() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        tester.setDNI("nuevo");
        assertEquals("setDNI","nuevo",tester.getDNI());
    }
    @Test
    public void testGetNombre() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        assertEquals("getNombre","Prueba",tester.getNombre());
    }
    @Test
    public void testSetNombre() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        tester.setNombre("nuevo");
        assertEquals("setNombre","nuevo",tester.getNombre());
    }
    @Test
    public void testGetApellidos() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        assertEquals("getApellidos","pruebaA",tester.getApellidos());
    }
    @Test
    public void testSetApellidos() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        tester.setApellidos("nuevo");
        assertEquals("setApellidos","nuevo",tester.getApellidos());
    }
    @Test
    public void testGetEmailUPM() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        assertEquals("getEmailUPM","prueba@upm.es",tester.getEmailUPM());
    }
    @Test
    public void testSetEmailUPM() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        tester.setEmailUPM("nuevo@ump.es");
        assertEquals("setApellidos","nuevo@ump.es",tester.getEmailUPM());
    }
    @Test
    public void testGetPassword() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        assertEquals("getEmailUPM", Cifrado.cifrar("password", Cifrado.Tipo.MD5),tester.getPassword());
    }
    @Test
    public void testSetPassword() {
        Usuario tester=new Usuario("prueba","Prueba","pruebaA","prueba@upm.es");
        tester.setPassword("nuevo");
        assertEquals("getEmailUPM", Cifrado.cifrar("nuevo", Cifrado.Tipo.MD5),tester.getPassword());
    }
}