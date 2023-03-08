public class PDI extends Observador implements IPDI {

    private String codigoTrabajador;
    private Tcategoria categoria;

    public PDI(){

    }

    public PDI(String DNI, String nombre, String apellidos, String emailUPM,String codigoTrabajador,Tcategoria categoria) {
        super(DNI, nombre, apellidos, emailUPM);
        this.codigoTrabajador=codigoTrabajador;
        this.categoria= categoria;
    }

    public String getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(String codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador;
    }

    public Tcategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Tcategoria categoria) {
        this.categoria = categoria;
    }
}
