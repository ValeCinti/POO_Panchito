import java.sql.SQLOutput;

public class Equipo {
    private String NombreDeEquipo;
    private String Categoria;
    private Entrenador Entrenador;

    public void Equipo(String NombreDeEquipo, String Categoria){
       this.NombreDeEquipo = NombreDeEquipo;
       this.Categoria = Categoria;
       this.Entrenador = null;
    }

    public Entrenador ObtenerEntrenado(){return Entrenador;}

    public void AsignarEntrenador(Entrenador Entrenador){
        if (this.Entrenador == null  ){
            if(Entrenador.getEstaOcupado() != true){
                this.Entrenador = Entrenador;
                this.Entrenador.SetterEstaOcupado(true);
            }else{
                System.out.println("El entrenador que quiere seleccionar esta ocupado ");
            }
        }else{
            System.out.println("ya hay un entrenador asignado");
        }
    }

    public void CambiarEntrenador(Entrenador NuevoEntrenador){
        if (this.Entrenador != null){
            if (NuevoEntrenador.getEstaOcupado() != true ){
                this.Entrenador.SetterEstaOcupado(false);
                this.Entrenador=NuevoEntrenador;
                this.Entrenador.SetterEstaOcupado(true);
            }else{
                System.out.println("El entrenador que quiere seleccionar esta ocupado ");
            }
        }else{
            System.out.println("no hay entrenador asignado");
        }
    }
    public void Mostrarinformacio(Entrenador Entrenador) {
        System.out.println("DNI: "+Entrenador.GetDni());
        System.out.println("Nombre: "+Entrenador.GetNombre());
        System.out.println("AniosDeExperiencia: "+Entrenador.GetAniosDeExperiencia());
        System.out.println("EstaOcupado :"+ Entrenador.getEstaOcupado());
    }

}
