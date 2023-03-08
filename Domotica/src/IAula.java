import java.util.ArrayList;
import java.util.HashMap;

public interface IAula {
    public String getCentro();

    public HashMap<String,String> iniciarMonitorizacion();
    public void pausarMonitorizacion();

    public void subscribir(IUsuario obs);

    public void cancelarSubs(IUsuario obs);

    public void setCentro(String centro) ;

    public String getiD_ETSISI() ;

    public void setiD_ETSISI(String iD_ETSISI);

    public float getSuperficie() ;

    public void setSuperficie(float superficie) ;

    public int getAforo() ;

    public void setAforo(int aforo);

    public TAula getTipoAula() ;

    public void setTipoAula(TAula tipoAula);

    public int getId();

    public void setId(int id);
}
