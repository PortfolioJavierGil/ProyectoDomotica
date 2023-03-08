public class Domotica implements IDomotica {
    ControllerUsuario controllerUsuario;
    ControllerAula controllerAula;
    VistaDomotica vistaDomotica;
    Domotica() {
        vistaDomotica=new VistaDomotica();
    }

    private void requestMostrarMensaje(String mensaje){
        vistaDomotica.mostrarMensaje(mensaje);
    }
    private String requestDato(){
        return vistaDomotica.solicitarDato();
    }

    public void iniciar(){
        controllerUsuario = new ControllerUsuario();
        controllerAula = new ControllerAula(controllerUsuario);
        controllerUsuario.requestNewVistaUsuario();
        controllerAula.requestNewVistaAula();
        controllerUsuario.usuarioRoot();
    }

    public void procesarPeticion() throws CustomException {
        try {
            while (controllerUsuario.getActual() == null) {
                primeraPeticion();
            }
            while (controllerUsuario.getActual() != null) {
                String tipo = controllerUsuario.getTipoUsuario(controllerUsuario.getActual());
                if (controllerUsuario.getActual().getDNI().equals("root"))
                    opcionesRoot();
                else if (tipo.equals("PAS"))
                    opcionesPAS();
                else if (tipo.equals("PDI"))
                    opcionesPDI();
                else if (tipo.equals("Alumno"))
                    opcionesAlumno();
            }
        }catch (RuntimeException e){
            procesarPeticion();
        }catch (CustomException e){
            procesarPeticion();
        }
    }

    private String primeraPeticion() throws CustomException {
        requestMostrarMensaje("Iniciar sesion (1) antes de continuar, darse de alta (2), recuperar password (3)");
        switch (Integer.parseInt(requestDato())) { // switch lambda-style syntax introducido en Java 8
            case 1 -> controllerUsuario.iniciarSesion();
            case 2 -> controllerUsuario.altaUsuario();
            case 3 -> controllerUsuario.recuperarPassword();
        }

        return controllerUsuario.getTipoUsuario(controllerUsuario.getActual());
    }

    private void opcionesRoot() throws CustomException {
        requestMostrarMensaje("Introducir numero asociado a la accion: ");
        requestMostrarMensaje("1-Alta usuario, 2-Editar usuario, 3-Dar de baja usuario, 4-Mostrar usuario, 5-Cerrar sesion,");
        requestMostrarMensaje("6-Alta de aula, 7- Editar aula, 8-Dar de baja aula, 9-Mostrar aula, 0-Mas opciones");
        int opcion = Integer.parseInt(requestDato());
        if(opcion==0){
            requestMostrarMensaje("10-Suscribir usuario, 11-Cancelar Subscripcion, 12- Mostrar suscripciones");
            opcion= Integer.parseInt(requestDato());
        }
        switch (opcion) {
            case 1 -> controllerUsuario.altaUsuario();
            case 2 -> {
                requestMostrarMensaje("Introducir DNI de usuario al que se cambien los datos");
                controllerUsuario.updateUsuario(requestDato());
            }
            case 3 -> {
                requestMostrarMensaje("Introducir DNI de usuario que va a ser dado de baja");
                controllerUsuario.removeUsuario(requestDato());
            }
            case 4 -> {
                requestMostrarMensaje("Introducir DNI de usuario cuyos datos quiere mostrar");
                controllerUsuario.requestMostrarUsuario(requestDato());
            }
            case 5 -> controllerUsuario.cerrarSesion();
            case 6 -> controllerAula.altaAula();
            case 7 -> {
                requestMostrarMensaje("Introducir id (numero) del aula a modificar");
                controllerAula.updateAula(Integer.parseInt(requestDato()));
            }
            case 8 -> {
                requestMostrarMensaje("Introducir id (numero) del aula a dar de baja");
                controllerAula.bajaAula(Integer.parseInt(requestDato()));
            }
            case 9 -> {
                requestMostrarMensaje("Introducir id (numero) del aula a mostrar");
                controllerAula.requestMostrarAula(Integer.parseInt(requestDato()));
            }
            case 10 -> {
                requestMostrarMensaje("Introducir id (numero) del aula al que se suscribe");
                int idAlta = Integer.parseInt(requestDato());
                requestMostrarMensaje("Introducir DNI del usuario que se suscribe");
                controllerAula.subscribirUsuario(requestDato(), idAlta);
                System.out.println("Termino suscribir");
            }
            case 11 -> {
                requestMostrarMensaje("Introducir id (numero) del aula al que ELIMINAR suscripcion");
                int idBaja = Integer.parseInt(requestDato());
                requestMostrarMensaje("Introducir DNI del usuario al que ELIMINAR suscripcion");
                controllerAula.cancelarSubscripcion(requestDato(), idBaja);
            }
            case 12 -> {
                requestMostrarMensaje("Introducir DNI del usuario al que mostrar suscripciones");
                controllerAula.requestMostrarSubscripciones(requestDato());
            }
        }

    }

    private void opcionesDatosUsuario(int opcion) throws CustomException {
        switch (opcion) {
            case 1 -> controllerUsuario.updateUsuario(controllerUsuario.getActual().getDNI());
            case 2 -> controllerUsuario.removeUsuario(controllerUsuario.getActual().getDNI());
            case 3 -> controllerUsuario.requestMostrarUsuario(controllerUsuario.getActual().getDNI());
            case 4 -> controllerUsuario.cerrarSesion();
            default -> throw new RuntimeException();
        }
    }

    private void opcionesPAS() throws CustomException {
        requestMostrarMensaje("Introducir numero asociado a la accion:");
        requestMostrarMensaje("1-Editar el usuario actual, 2-Dar de baja el usuario actual, 3-Mostrar informacion del usuario actual, 4-Cerrar sesion actual");
        requestMostrarMensaje("5-Dar de alta un aula, 6-Editar un aula, 7-Dar de baja un aula, 8-Mostrar informacion de un aula, 9- Mostrar suscripciones");
        int accion= Integer.parseInt(requestDato());
        switch (accion) {
            case 5 -> controllerAula.altaAula();
            case 6 -> {
                requestMostrarMensaje("Introducir id (numero) del aula a modificar");
                controllerAula.updateAula(Integer.parseInt(requestDato()));
            }
            case 7 -> {
                requestMostrarMensaje("Introducir id (numero) del aula a dar de baja");
                controllerAula.bajaAula(Integer.parseInt(requestDato()));
            }
            case 8-> {
                requestMostrarMensaje("Introducir id (numero) del aula a mostrar");
                controllerAula.requestMostrarAula(Integer.parseInt(requestDato()));
            }
            case 9-> {
                controllerAula.requestMostrarSubscripciones(controllerUsuario.getActual().getDNI());
            }
            default -> opcionesDatosUsuario(accion);
        }
    }

    private void opcionesPDI() throws CustomException {
        requestMostrarMensaje("Introducir numero asociado a la accion:");
        requestMostrarMensaje("1-Editar el usuario actual, 2-Dar de baja el usuario actual, 3-Mostrar informacion del usuario actual, 4-Cerrar sesion actual");
        requestMostrarMensaje("5-Suscribirse a un aula, 6-Cancelar suscripcion a un aula, 7-Mostrar informacion de un aula,8-Mostrar suscripciones");
        int accion= Integer.parseInt(requestDato());
        switch (accion) {
            case 5-> {
                requestMostrarMensaje("Introducir id (numero) del aula a suscribirse");
                controllerAula.subscribirUsuario(controllerUsuario.getActual().getDNI(),Integer.parseInt(requestDato()));
            }
            case 6-> {
                requestMostrarMensaje("Introducir id (numero) del aula a cancelar suscribcion");
                controllerAula.cancelarSubscripcion(controllerUsuario.getActual().getDNI(),Integer.parseInt(requestDato()));
            }
            case 7-> {
                requestMostrarMensaje("Introducir id (numero) del aula a mostrar");
                controllerAula.requestMostrarAula(Integer.parseInt(requestDato()));
            }
            case 8-> {
                controllerAula.requestMostrarSubscripciones(controllerUsuario.getActual().getDNI());
            }
            default -> opcionesDatosUsuario(accion);
        }
    }
    private void opcionesAlumno() throws CustomException {
        requestMostrarMensaje("Introducir numero asociado a la accion:");
        requestMostrarMensaje("1-Editar el usuario actual, 2-Dar de baja el usuario actual, 3-Mostrar informacion del usuario actual, 4-Cerrar sesion actual");
        requestMostrarMensaje("5-Suscribirse a un aula, 6-Cancelar suscripcion a un aula, 7-Mostrar informacion de un aula,8-Mostrar suscripciones");
        int accion= Integer.parseInt(requestDato());
        switch (accion) {
            case 5-> {
                requestMostrarMensaje("Introducir id (numero) del aula a suscribirse");
                controllerAula.subscribirUsuario(controllerUsuario.getActual().getDNI(),Integer.parseInt(requestDato()));
            }
            case 6-> {
                requestMostrarMensaje("Introducir id (numero) del aula a cancelar suscribcion");
                controllerAula.cancelarSubscripcion(controllerUsuario.getActual().getDNI(),Integer.parseInt(requestDato()));
            }
            case 7-> {
                requestMostrarMensaje("Introducir id (numero) del aula a mostrar");
                controllerAula.requestMostrarAula(Integer.parseInt(requestDato()));
            }
            case 8-> {
                controllerAula.requestMostrarSubscripciones(controllerUsuario.getActual().getDNI());
            }
            default -> opcionesDatosUsuario(accion);
        }
    }

}
