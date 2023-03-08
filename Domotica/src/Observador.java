import java.util.ArrayList;

public class Observador extends Usuario implements IObservador {

    ArrayList<Aula> listaAulas;

    public Observador (){

    }

    public Observador(String DNI, String nombre, String apellidos, String emailUPM) {
        super(DNI, nombre, apellidos, emailUPM);
        listaAulas=new ArrayList<>();
    }
    public void addAula(Aula aula){
        listaAulas.add(aula);
    }
    public void removeAula(Aula aula){
        listaAulas.remove(aula);
    }
    public Aula mostrarAulasSubscripcion(int posicion){
        if(listaAulas.isEmpty())return null;
        else return listaAulas.get(posicion);
    }
    public int getNumSubscriciones(){
        return listaAulas.size();
    }

}
