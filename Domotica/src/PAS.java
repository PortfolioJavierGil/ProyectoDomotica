import java.time.LocalDate;

public class PAS extends Usuario implements IPAS{

    private String codigoPersonal;
    private LocalDate anioIncorporacion;

    public PAS(){

    }

    public PAS(String DNI, String nombre, String apellidos, String emailUPM,String codigoPersonal,LocalDate anioIncorporacion) {
        super(DNI, nombre, apellidos, emailUPM);
        this.codigoPersonal=codigoPersonal;
        this.anioIncorporacion=anioIncorporacion;
    }

    public String getCodigoPersonal() {
        return codigoPersonal;
    }

    public void setCodigoPersonal(String codigoPersonal) {
        this.codigoPersonal = codigoPersonal;
    }

    public LocalDate getAnioIncorporacion() {
        return anioIncorporacion;
    }

    public void setAnioIncorporacion(LocalDate anioIncorporacion) {
        this.anioIncorporacion = anioIncorporacion;
    }
}
