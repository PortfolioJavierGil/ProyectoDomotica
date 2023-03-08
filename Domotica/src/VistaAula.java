import java.util.ArrayList;
import java.util.Scanner;

public class VistaAula {
    Scanner scan =new Scanner(System.in);
    VistaAula(){}

    public static void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public String solicitarDato(){ //solicita interaccion con el usuario
        String dato=scan.nextLine();
        return dato;
    }

    public void mostrarAula(IAula aula){
        System.out.println("Centro "+
                aula.getCentro()+". ID_ETSISI "+
                aula.getiD_ETSISI()+". Superficie "+
                aula.getSuperficie()+"m2. Aforo "+
                aula.getAforo()+" personas. Tipo aula "+
                aula.getTipoAula()+". Id "+
                aula.getId()
        );

    }

    public void subscribirVista(IAula aula){ //metodo que muestra al usuario las lecturas de los sensores
        System.out.println(aula.iniciarMonitorizacion());
    }
}
