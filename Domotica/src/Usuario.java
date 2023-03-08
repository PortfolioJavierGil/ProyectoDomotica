
import utilidades.Cifrado;

public class Usuario implements IUsuario{
    private String DNI;
    private String nombre;
    private String apellidos;
    private String emailUPM;
    private String password;

    public Usuario(){

    }

    public Usuario(String DNI, String nombre, String apellidos, String emailUPM) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.emailUPM = emailUPM;
        this.password = Cifrado.cifrar("password", Cifrado.Tipo.MD5);
    }



    public String getDNI() {
        return DNI;
    }

    public void setDNI(String dni) {

        System.out.println(DNI); this.DNI = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmailUPM() {
        return emailUPM;
    }

    public void setEmailUPM(String emailUPM) {
        this.emailUPM = emailUPM;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){ this.password = Cifrado.cifrar(password, Cifrado.Tipo.MD5);}
}
