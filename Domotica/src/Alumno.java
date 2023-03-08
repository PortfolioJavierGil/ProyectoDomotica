public class Alumno extends Observador implements IAlumno{
    private String matricula;

    public Alumno(){

    }
    public Alumno(String DNI, String nombre, String apellidos, String emailUPM,String matricula) {
        super(DNI, nombre, apellidos, emailUPM);
        this.matricula=matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
