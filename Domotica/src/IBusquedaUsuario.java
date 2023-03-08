public interface IBusquedaUsuario {
    IUsuario findUsuario(String dni)throws CustomException;
}
