package primerTP;

public class EntrenadorEquipo {
    static class Equipo {
        private String nombreDeEquipo;
        private String categoria;
        private Entrenador entrenador;

        public Equipo(String nombreDeEquipo,String categoria){
            this.nombreDeEquipo=nombreDeEquipo;
            this.categoria=categoria;
        }

        public Entrenador obtenerEntrenador(){return entrenador;}

        public void asignarEntrenador(Entrenador entrenador){
            if(entrenador==null){
                System.out.println("No se puede asignar un entrenador nulo.");
                return;
            }
            if(this.entrenador!=null){
                System.out.println("Ya hay un entrenador asignado.");
                return;
            }
            if(entrenador.estaOcupado()){
                System.out.println("El entrenador ya está asignado a otro equipo.");
                return;
            }
            this.entrenador=entrenador;
            entrenador.asignarEquipo(this);
        }

        public void cambiarEntrenador(Entrenador nuevoEntrenador){
            if(nuevoEntrenador==null){
                System.out.println("No se puede asignar un entrenador nulo.");
                return;
            }
            if(this.entrenador==null){
                System.out.println("No hay entrenador asignado para cambiar.");
                return;
            }
            if(nuevoEntrenador.estaOcupado()){
                System.out.println("El nuevo entrenador ya está asignado a otro equipo.");
                return;
            }
            entrenador.liberarEquipo();
            entrenador=nuevoEntrenador;
            entrenador.asignarEquipo(this);
        }

        public void mostrarInformacion(){
            System.out.println("Equipo: "+nombreDeEquipo+" - Categoría: "+categoria);
            if(entrenador!=null){
                System.out.println("DNI: "+entrenador.getDni());
                System.out.println("Nombre: "+entrenador.getNombre());
                System.out.println("Años de experiencia: "+entrenador.getAniosDeExperiencia());
            }else{
                System.out.println("Este equipo no tiene entrenador asignado.");
            }
            System.out.println("-------------------------");
        }
    }

    static class Entrenador {
        private String dni;
        private String nombre;
        private int aniosDeExperiencia;
        private Equipo equipo;

        public Entrenador(String dni,String nombre,int aniosDeExperiencia){
            this.dni=dni;
            this.nombre=nombre;
            this.aniosDeExperiencia=aniosDeExperiencia;
        }

        public String getDni(){return dni;}
        public String getNombre(){return nombre;}
        public int getAniosDeExperiencia(){return aniosDeExperiencia;}
        public boolean estaOcupado(){return equipo!=null;}
        private void asignarEquipo(Equipo equipo){this.equipo=equipo;}
        private void liberarEquipo(){this.equipo=null;}
    }

    public static void main(String[] args){
        Entrenador marcelo=new Entrenador("11223344","Marcelo Gallardo",15);
        Entrenador pep=new Entrenador("55667788","Pep Guardiola",20);
        Equipo equipoA=new Equipo("Los Halcones","Primera División");
        Equipo equipoB=new Equipo("Las Águilas","Segunda División");

        equipoA.asignarEntrenador(marcelo);
        equipoB.asignarEntrenador(marcelo);

        equipoA.mostrarInformacion();
        equipoB.mostrarInformacion();

        equipoA.cambiarEntrenador(pep);

        equipoA.mostrarInformacion();

        equipoB.asignarEntrenador(marcelo);
        equipoB.mostrarInformacion();
    }
}