
//Funciona con la ultima version de jdk

import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws CustomException {
        Domotica system = new Domotica();
        system.iniciar(); //Por defecto se crea un usuario root cuyo dni es "root" con contraseña "1234" capaz de realizar todas las acciones menos recuperar contraseña
        Scanner scan= new Scanner(System.in);
        while(true){
            system.procesarPeticion();
        }
    }
}





