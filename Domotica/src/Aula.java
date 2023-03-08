import java.util.ArrayList;
import java.util.HashMap;

public class Aula implements IAula {
    private String centro;
    private int id;
    private String iD_ETSISI;
    private float superficie;
    private int aforo;
    private TAula tipoAula;
    ArrayList<IUsuario> subs= new ArrayList<>();
    Aula(String centro,String iD_ETSISI,float superficie,int aforo, TAula tipoAula,int id){
            this.centro = centro;
            this.iD_ETSISI = iD_ETSISI;
            this.superficie = superficie;
            this.aforo = aforo;
            this.tipoAula = tipoAula;
            this.id = id;
    }

    public HashMap<String,String> iniciarMonitorizacion(){
        //metodo que recibe los datos de los sensores y los devuelve
        return null;
    }

    public void pausarMonitorizacion(){
        //metodo que pausa la recepcion de datos de los sensores
    }

    public String getCentro() {
        return centro;
    }

    public void subscribir(IUsuario usuario){
        subs.add(usuario);
    }

    public void cancelarSubs(IUsuario obs){
        subs.remove(obs);
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getiD_ETSISI() {
        return iD_ETSISI;
    }

    public void setiD_ETSISI(String iD_ETSISI) {
        this.iD_ETSISI = iD_ETSISI;
    }

    public float getSuperficie() {
        return superficie;
    }

    public void setSuperficie(float superficie) {
        this.superficie = superficie;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public TAula getTipoAula() {
        return tipoAula;
    }

    public void setTipoAula(TAula tipoAula) {
        this.tipoAula = tipoAula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
