import java.util.ArrayList;
import java.util.InputMismatchException;

public class ControllerAula {
    private IBusquedaUsuario controladorUsuario;
    private ArrayList<Aula> listaAula;
    private VistaAula vistaAula;


    ControllerAula(){
        listaAula =new ArrayList<>();
    }

    ControllerAula(IBusquedaUsuario controladorUsuario){
        listaAula =new ArrayList<>();
        this.controladorUsuario=controladorUsuario;
    }

    public void setControladorUsuario(IBusquedaUsuario controladorUsuario){
        this.controladorUsuario=controladorUsuario;
    }
    public IBusquedaUsuario getControladorUsuario(){
        return controladorUsuario;
    }

    public void requestNewVistaAula(){
         vistaAula= new VistaAula();
    }

    public void requestMostrarMensaje(String mensaje){
        vistaAula.mostrarMensaje(mensaje);
    }

    public void requestMostrarAula(int id) throws CustomException {
        int i;
        for(i=0;i<listaAula.size();i++){
            if(id == listaAula.get(i).getId())break;
        }
        vistaAula.mostrarAula(listaAula.get(i));
    }

    public String requestDato(){
        return vistaAula.solicitarDato();
    }


    public void altaAula() throws CustomException {
        try {
            TAula a; //guarda el tipo de aula
            requestMostrarMensaje("Introducir Centro (Texto)");
            String centro =  requestDato();
            requestMostrarMensaje("Introducir ID_ETSISI (Texto)");
            String iD_ETSISI =  requestDato();
            requestMostrarMensaje("Introducir superficie (Numero decimal)");
            float superficie = (float)Integer.parseInt(requestDato());
            requestMostrarMensaje("Introducir aforo (Numero natural)");
            int aforo = Integer.parseInt(requestDato());
            requestMostrarMensaje("Introducir tipoAula: 1-teoria,2-laboratorio,3-mixto");
            int tipoAula = Integer.parseInt(requestDato());
            requestMostrarMensaje("Introducir id (Numero natural)");
            int id = Integer.parseInt(requestDato());

            //error aforo negativo o 0
            if(aforo<0)throw new CustomException("Aforo negativo");
            //error tipo aula no existe
            if (tipoAula == 1) a = TAula.teoria;
            else if (tipoAula == 2) a = TAula.laboratorio;
            else if (tipoAula == 3) a = TAula.mixta;
            else throw new CustomException("No existe tipo Aula");
            //error id de aula ya existe
            if(findAula(id)!=null)
                if (id== findAula(id).getId())
                    throw new CustomException("Ya existe aula con id" + id);

            //creacion de aula
            Aula aula = new Aula(centro, iD_ETSISI, superficie, aforo, a, id);
            //inclusion de aula en listaAula
            listaAula.add(aula);
        } catch (InputMismatchException e){
            throw new CustomException("No coincide con el tipo de dato");
        }

    }

    public void updateAula(int id) throws CustomException {
            requestMostrarMensaje("Elegir datos (introducir numero asociado) a editar del aula " + findAula(id).getiD_ETSISI() + ": " + id);
            requestMostrarMensaje("1-centro, 2-ID_ETSISI,3-superficie,4-Tipo de aula");
            int opcion = Integer.parseInt(requestDato());
            requestMostrarMensaje("Introducir nuevo dato");
            Object nuevoDato = requestDato();
            switch (opcion) {
                case 1:
                    findAula(id).setCentro((String)nuevoDato);
                    break;
                case 2:
                    findAula(id).setiD_ETSISI((String)nuevoDato);
                    break;
                case 3:
                    findAula(id).setSuperficie((float)nuevoDato);
                    break;
                case 4:
                    if (nuevoDato.equals("teoria")) findAula(id).setTipoAula(TAula.teoria);
                    else if (nuevoDato.equals("laboratorio")) findAula(id).setTipoAula(TAula.laboratorio);
                    else if (nuevoDato.equals("mixto")) findAula(id).setTipoAula(TAula.mixta);
                    else throw new CustomException("No existe tipo Aula");
                    break;
                default:
                    throw new CustomException("No existe dato asociado al numero: "+opcion);
            }

    }

    public void bajaAula(int id) throws CustomException{
            listaAula.remove(findAula(id));
    }

    public Aula findAula(int id) throws CustomException {
            if(listaAula.isEmpty())return null;
            else for (int i = 0; i < listaAula.size(); i++) {
                if (listaAula.get(i).getId() == id)
                    return listaAula.get(i);
            }
            throw new CustomException("No existe aula con id: "+id);
    }

    public void subscribirUsuario(String dni, int id) throws CustomException {
        String tipo= controladorUsuario.findUsuario(dni).getClass().getName();
        if(tipo=="PAS") requestMostrarMensaje("Usuario PAS no puede suscribirse");
        else {
            findAula(id).subscribir(controladorUsuario.findUsuario(dni));
            switch (tipo){
                case "PDI" -> ((PDI) controladorUsuario.findUsuario(dni)).addAula(findAula(id));
                case "Alumno" ->((Alumno) controladorUsuario.findUsuario(dni)).addAula(findAula(id));
                default -> throw new IllegalStateException("Unexpected value: " + tipo);
            }
        }


    }

    public void cancelarSubscripcion(String dni, int id) throws CustomException {
        String tipo= controladorUsuario.findUsuario(dni).getClass().getName();
        if(tipo=="PAS") requestMostrarMensaje("Usuario PAS no puede estar suscrito");
        else {
            findAula(id).subscribir(controladorUsuario.findUsuario(dni));
            switch (tipo){
                case "PDI" -> ((PDI) controladorUsuario.findUsuario(dni)).removeAula(findAula(id));
                case "Alumno" -> ((Alumno) controladorUsuario.findUsuario(dni)).removeAula(findAula(id));
                default -> throw new IllegalStateException("Unexpected value: " + tipo);
            }
        }
    }

    public int getNumAulas(){return listaAula.size();}

    public void requestMostrarSubscripciones(String dni) throws CustomException {
        String tipo = controladorUsuario.findUsuario(dni).getClass().getName();
        if(tipo=="PAS")requestMostrarMensaje("PAS no puede estar suscrito");
        else{
            switch (tipo){
                case "PDI"-> {
                    PDI pdi=(PDI) controladorUsuario.findUsuario(dni);
                    if(pdi.mostrarAulasSubscripcion(0)==null)requestMostrarMensaje("No esta suscrito a ningun aula");
                    else for(int i=0;i< pdi.getNumSubscriciones();i++) requestMostrarAula(pdi.mostrarAulasSubscripcion(i).getId());
                }
                case "Alumno"-> {
                    Alumno alumno=(Alumno) controladorUsuario.findUsuario(dni);
                    if(alumno.mostrarAulasSubscripcion(0)==null)requestMostrarMensaje("No esta suscrito a ningun aula");
                    else for(int i=0;i< alumno.getNumSubscriciones();i++) requestMostrarAula(alumno.mostrarAulasSubscripcion(i).getId());
                }
            }
        }
    }

}
