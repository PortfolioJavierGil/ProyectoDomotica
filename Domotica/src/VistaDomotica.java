import java.util.Scanner;

public class VistaDomotica {

    Scanner scan =new Scanner(System.in);
    VistaDomotica(){};

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public String solicitarDato(){ //solicita interaccion con el usuario
        String dato=scan.nextLine();
        return dato;
    }

}
