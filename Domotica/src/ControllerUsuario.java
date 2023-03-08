import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import servidor.*;
import utilidades.Cifrado;

public class ControllerUsuario implements IBusquedaUsuario{

    private VistaUsuario vistaUsuario;
    private ArrayList<IUsuario> listaUsuarios;
    private IUsuario actual;
    private Autenticacion autenticacion;

    ControllerUsuario(){
        listaUsuarios =new ArrayList<>();
        actual=null;
        autenticacion=new Autenticacion();
    }

    public String getTipoUsuario(Object objeto){
        if(objeto==null) return null;
        else return objeto.getClass().getName();
    }

    public void usuarioRoot(){
        PAS root = new PAS("root","Root","","root@upm.es","",LocalDate.now());
        root.setPassword("1234");
        listaUsuarios.add(root);
    }

    public void requestNewVistaUsuario(){
        vistaUsuario=new VistaUsuario();
    }

    public void requestMostrarMensaje(String mensaje){
        vistaUsuario.mostrarMensaje(mensaje);
    }

    public void requestMostrarUsuario(String dni) throws CustomException {
        String tipo=getTipoUsuario(findUsuario(dni));
        switch (tipo) {
            case "PAS" -> vistaUsuario.mostrarUsuarioPAS((PAS) findUsuario(dni));
            case "PDI" -> vistaUsuario.mostrarUSuarioPDI((PDI) findUsuario(dni));
            case "Alumno" -> vistaUsuario.mostrarAlumno((Alumno) findUsuario(dni));
        }
    }
    public String requestDato(){
        return vistaUsuario.solicitarDato();
    }

    public void altaUsuario() throws CustomException {
        try {
            requestMostrarMensaje("Introducir DNI (Texto)");
            String dni = requestDato();
            requestMostrarMensaje("Introducir Nombre (Texto)");
            String nombre =  requestDato();
            requestMostrarMensaje("Introducir Apellidos, ej: 'Perez Perez' (Texto)");
            String apellidos = requestDato();
            requestMostrarMensaje("Introducir email (Texto)");
            String email = requestDato();
            requestMostrarMensaje("******Password generada por defecto, se recomienda cambiar cuanto antes******\n");
            if(!autenticacion.existeCuentaUPM(email)) throw new Error("El correo no pertenece a la UPM");
            UPMUsers eTipoUsuario=ObtencionDeRol.get_UPM_AccountRol(email);
            requestMostrarMensaje("El correo es de la UPM con rol:"+eTipoUsuario);
            int tipoUsuario=0;
            if(eTipoUsuario.equals(UPMUsers.PAS))tipoUsuario=1;
            if(eTipoUsuario.equals(UPMUsers.PDI))tipoUsuario=2;
            if(eTipoUsuario.equals(UPMUsers.ALUMNO))tipoUsuario=3;
            switch (tipoUsuario) {
                case 1 -> {
                    requestMostrarMensaje("Introducir Codigo Personal(Texto)");
                    String codigoPersonal = requestDato();
                    requestMostrarMensaje("Introducir Año de Incorporacion(Fecha) ej:'2017-02-03'");
                    LocalDate annioIncorporacion = LocalDate.parse(requestDato());
                    PAS pas = new PAS(dni, nombre, apellidos, email, codigoPersonal, annioIncorporacion);
                    listaUsuarios.add(pas);
                    actual=findUsuario(dni);
                }
                case 2 -> {
                    requestMostrarMensaje("Introducir Codigo Trabajador(Texto)");
                    String codigoTrabajador = requestDato();
                    requestMostrarMensaje("""
                            Introducir categoria:
                            1- Ayudante
                            2-Profesor Ayudante Doctor
                            3-Profesor Contratado Doctor
                            +4-Titular
                            5-Catedrático""");
                    int categoriaN = Integer.parseInt(requestDato()); // variable numerica que marca la categoria
                    Tcategoria categoria = switch (categoriaN) {
                        case 1 -> Tcategoria.Ayudante;
                        case 2 -> Tcategoria.Profesor_Ayudante_Doctor;
                        case 3 -> Tcategoria.Profesor_Contratado_Doctor;
                        case 4 -> Tcategoria.Titular;
                        case 5 -> Tcategoria.Catedratico;
                        default -> throw new CustomException("Categoria de profesor no encontrada");
                    };
                    PDI pdi = new PDI(dni, nombre, apellidos, email, codigoTrabajador, categoria);
                    listaUsuarios.add(pdi);
                    actual=findUsuario(dni);
                }
                case 3 -> {
                    requestMostrarMensaje("Introducir Matricula del Alumno (Texto)");
                    String matricula = requestDato();
                    Alumno alumno = new Alumno(dni, nombre, apellidos, email, matricula);
                    listaUsuarios.add(alumno);
                    actual=findUsuario(dni);
                }
                default -> throw new CustomException("No existe tipo de usuario introducido");
            }

        } catch (InputMismatchException e){
            throw new CustomException("No coincide con el tipo de dato");
        }
    }

    public void updateUsuario(String dni) throws CustomException {
        IUsuario usuario = findUsuario(dni);
        String tipo = getTipoUsuario(findUsuario(dni));
        int opcion;
        requestMostrarMensaje("Introducir numero asociado al dato a modificar: 0-Salir 1-DNI, 2-Nombre, 3-Apellidos, 4-EmailUPM, 5-Password, ");
        switch (tipo) {
            case "PAS" -> requestMostrarMensaje("6-Codigo de Personal, 7-Año de incorporacion.");
            case "PDI" -> requestMostrarMensaje("8-Codigo de Trabajador, 9-Categoria."); //el codigo real es 8 y 9
            case "Alumno" -> requestMostrarMensaje("10-Matricula.");//el codigo real es 10
            default -> throw new CustomException("El usuario no tiene tipo");
        }
        opcion = Integer.parseInt(requestDato());
        requestMostrarMensaje("Introducir dato:");
        String dato = requestDato();
        switch (opcion) {
            case 1 -> usuario.setDNI(dato);
            case 2 -> usuario.setNombre(dato);
            case 3 -> usuario.setApellidos(dato);
            case 4 -> usuario.setEmailUPM(dato);
            case 5 -> usuario.setPassword(dato);
            case 6 -> {
                PAS pas1 = (PAS) findUsuario(dni);
                pas1.setCodigoPersonal(dato);
            }
            case 7 -> {
                PAS pas2 = (PAS) findUsuario(dni);
                pas2.setAnioIncorporacion(LocalDate.parse(dato));
            }
            case 8 -> {
                PDI pdi = (PDI) findUsuario(dni);
                pdi.setCodigoTrabajador(dato);
            }
            case 9 -> {
                PDI pdi2 = (PDI) findUsuario(dni);
                requestMostrarMensaje("""
                        Introducir numero asociado a la categoria:
                        1- Ayudante
                        2-Profesor Ayudante Doctor
                        3-Profesor Contratado Doctor
                        4-Titular
                        5-Catedrático""");
                Tcategoria aux = switch (Integer.parseInt(requestDato())) {
                    case 1 -> Tcategoria.Ayudante;
                    case 2 -> Tcategoria.Profesor_Ayudante_Doctor;
                    case 3 -> Tcategoria.Profesor_Contratado_Doctor;
                    case 4 -> Tcategoria.Titular;
                    case 5 -> Tcategoria.Catedratico;
                    default -> throw new CustomException("El numero no está asociado con ninguna categoria");
                };
                pdi2.setCategoria(aux);
            }
            case 10 -> {
                Alumno alumno = (Alumno) findUsuario(dni);
                alumno.setMatricula(dato);
            }
        }

    }

    public void removeUsuario(String dni) throws CustomException {
        listaUsuarios.remove(findUsuario(dni));
    }

    public IUsuario findUsuario(String dni) throws CustomException{
        for(int i=0;i<listaUsuarios.size();i++){
            if(dni.equals(listaUsuarios.get(i).getDNI())) {
                return listaUsuarios.get(i);
            }
        }
        requestMostrarMensaje("No existe usuario con dni" + dni);
        return null;
    }


    public void iniciarSesion() {
        try {
            requestMostrarMensaje("Introduzca DNI");
            String dni = requestDato();
            boolean passTrue= false;
            int cont=0;
            while(!passTrue) {
                requestMostrarMensaje("Introduzca password");
                String pass = Cifrado.cifrar(requestDato(), Cifrado.Tipo.MD5);
                if (pass.equals(findUsuario(dni).getPassword())) {
                    actual = findUsuario(dni);
                    passTrue=true;
                }
                cont++;
                if(cont >2){
                    requestMostrarMensaje("Demasiados intentos, recupera password");
                    recuperarPassword();
                }
            }
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    public void recuperarPassword() {
        try {
            requestMostrarMensaje("Introduzca DNI");
            String dni = requestDato();
            if(dni.equals("root")){
                requestMostrarMensaje("No se puede cambiar root");
                throw new RuntimeException("Root user not having permissions") ;
            }else {
                requestMostrarMensaje("Introduzca emailUPM");
                String email = requestDato();
                if (findUsuario(dni).getEmailUPM().equals(email)) {
                    requestMostrarMensaje("Correlacion de datos correcta, introduzca la nueva password: ");
                    String newPass = requestDato();
                    findUsuario(dni).setPassword(newPass);
                } else {
                    throw new CustomException("Sin correlacion de datos");
                }
            }
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }

    public void cerrarSesion(){
        actual=null;
    }

    public VistaUsuario getVistaUsuario() {
        return vistaUsuario;
    }

    public void setVistaUsuario(VistaUsuario vistaUsuario) {
        this.vistaUsuario = vistaUsuario;
    }

    public ArrayList<IUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<IUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public IUsuario getActual() {
        return actual;
    }

    public void setActual(IUsuario actual) {
        this.actual = actual;
    }

}
