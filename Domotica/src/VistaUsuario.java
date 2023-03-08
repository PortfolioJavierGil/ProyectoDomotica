import java.util.Scanner;

public class VistaUsuario {
    Scanner scan =new Scanner(System.in);
    VistaUsuario(){};

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public String solicitarDato(){ //solicita interaccion con el usuario
        String dato=scan.nextLine();
        return dato;
    }

    public void mostrarUsuarioPAS(IPAS pas){
        System.out.println("Anno de Incorporacion: "+
                    pas.getAnioIncorporacion()+ ". Codigo de Personal:"+
                    pas.getCodigoPersonal()+". Apellidos: "+
                    pas.getApellidos()+". Nombre: "+
                    pas.getNombre()+". EmailUPM: "+
                    pas.getEmailUPM()+". DNI: "+
                    pas.getDNI()+"."
        );
    }

    public void mostrarUSuarioPDI(IPDI pdi){
        System.out.println("Codigo de trabajador: "+
                        pdi.getCodigoTrabajador()+". Categoria: "+
                        pdi.getCategoria()+". Apellidos: "+
                        pdi.getApellidos()+". Nombre: "+
                        pdi.getNombre()+". EmailUPM: "+
                        pdi.getEmailUPM()+". DNI: "+
                        pdi.getDNI()+"."
        );
    }

    public void mostrarAlumno(IAlumno alumno){
        System.out.println("Matricula: "+
                        alumno.getMatricula()+". Apellidos: "+
                        alumno.getApellidos()+". Nombre: "+
                        alumno.getNombre()+". EmailUPM: "+
                        alumno.getEmailUPM()+". DNI: "+
                        alumno.getDNI()+"."
        );
    }
}
