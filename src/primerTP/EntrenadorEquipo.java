package primerTP;

class Equipo {
    private String NombreDeEquipo;
    private String Categoria;
    private Entrenador Entrenador;

    public Equipo(String NombreDeEquipo, String Categoria) {
        this.NombreDeEquipo = NombreDeEquipo;
        this.Categoria = Categoria;
        this.Entrenador = null;
    }

    public Entrenador obtenerEntrenador() {
        return Entrenador;
    }

    public void asignarEntrenador(Entrenador entrenador) {
        if (this.Entrenador == null) {
            if (!entrenador.getEstaOcupado()) {
                this.Entrenador = entrenador;
                this.Entrenador.setterEstaOcupado(true);
            } else {
                System.out.println("El entrenador que quiere seleccionar esta ocupado ");
            }
        } else {
            System.out.println("ya hay un entrenador asignado");
        }
    }

    public void cambiarEntrenador(Entrenador nuevoEntrenador) {
        if (this.Entrenador != null) {
            if (!nuevoEntrenador.getEstaOcupado()) {
                this.Entrenador.setterEstaOcupado(false);
                this.Entrenador = nuevoEntrenador;
                this.Entrenador.setterEstaOcupado(true);
            } else {
                System.out.println("El entrenador que quiere seleccionar esta ocupado ");
            }
        } else {
            System.out.println("no hay entrenador asignado");
        }
    }

    // Muestra la información del equipo y de su entrenador (si existe)
    public void mostrarInformacion() {
        System.out.println("Equipo: " + NombreDeEquipo + " - Categoría: " + Categoria);
        if (Entrenador != null) {
            System.out.println("DNI: " + Entrenador.getDni());
            System.out.println("Nombre: " + Entrenador.getNombre());
            System.out.println("Años de experiencia: " + Entrenador.getAniosDeExperiencia());
            System.out.println("Ocupado: " + Entrenador.getEstaOcupado());
        } else {
            System.out.println("Este equipo no tiene entrenador asignado.");
        }
        System.out.println("-------------------------");
    }

    static class Entrenador {
        private String Dni;
        private String Nombre;
        private int AniosDeExperiencia;
        private boolean EstaOcupado;

        public Entrenador(String Dni, String Nombre, int AniosDeExperiencia) {
            this.Dni = Dni;
            this.Nombre = Nombre;
            this.AniosDeExperiencia = AniosDeExperiencia;
            this.EstaOcupado = false;
        }

        public String getDni() { return Dni; }
        public String getNombre() { return Nombre; }
        public int getAniosDeExperiencia() { return AniosDeExperiencia; }
        public boolean getEstaOcupado() { return EstaOcupado; }
        public void setterEstaOcupado(boolean estado) { this.EstaOcupado = estado; }
    }
}

    public class EntrenadorEquipo {
     static void main(String[] args) {
        Equipo.Entrenador marcelo = new Equipo.Entrenador("11223344", "Marcelo Gallardo", 15);
        Equipo.Entrenador pep = new Equipo.Entrenador("55667788", "Pep Guardiola", 20);

        Equipo equipoA = new Equipo("Los Halcones", "Primera División");
        Equipo equipoB = new Equipo("Las Águilas", "Segunda División");

        equipoA.asignarEntrenador(marcelo);

        equipoB.asignarEntrenador(marcelo);

        equipoA.mostrarInformacion();

        equipoA.cambiarEntrenador(pep);
        equipoA.mostrarInformacion();

        equipoB.asignarEntrenador(marcelo);
        equipoB.mostrarInformacion();
    }
}