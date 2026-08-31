import java.sql.SQLOutput;

public class Entrenador {
    String Dni;
    String Nombre;
    int AniosDeExperiencia;
    boolean EstaOcupado;

    public Entrenador( int Dni, String Nombre, int AniosDeExperiencia) {
        this.Dni = Dni;
        this.Nombre = Nombre;
        this.AniosDeExperiencia = AniosDeExperiencia;
        this.EstaOcupado=false;
    }
    public String GetDni(){return Dni; }
    public int GetAniosDeExperiencia(){return AniosDeExperiencia;}
    public String GetNombre() {return Nombre;}

    public boolean getEstaOcupado() {
        return EstaOcupado;
    }
    public void SetterEstaOcupado(boolean NuevoEstado){
        this.EstaOcupado = NuevoEstado;
    }


}
