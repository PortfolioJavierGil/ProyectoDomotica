import java.time.LocalDate;
import java.util.Date;

public interface IPAS extends IUsuario {
    public String getCodigoPersonal();
    public void setCodigoPersonal(String codigoPersonal);
    public LocalDate getAnioIncorporacion();
    public void setAnioIncorporacion(LocalDate anioIncorporacion);
}
