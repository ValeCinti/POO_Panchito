package primerTP;
import java.util.ArrayList;
public class MedicoPaciente {
    static class Medico {
        private String matricula;
        private String nombre;
        private String especialidad;
        private ArrayList<Paciente> Listapacientes;
        public Medico(String matricula, String nombre, String especialidad) {
            this.matricula = matricula;
            this.nombre = nombre;
            this.especialidad = especialidad;
            this.Listapacientes=new ArrayList<>();
        }

        public String getMatricula() {
            return matricula;
        }
        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getEspecialidad() {
            return especialidad;
        }
        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }

        public boolean asignarPaciente(Paciente paciente){
            if (!Listapacientes.contains(paciente)){
                this.Listapacientes.add(paciente);
                return true;
            }
            return false;
        }
        public Paciente buscarPaciente(String dni){
            for (Paciente paciente : Listapacientes){
                if (paciente.getDni().equals(dni)){
                    return paciente;
                }
            }
            return null;
        }
        boolean eliminarPaciente(String dni){
            if (buscarPaciente(dni) != null){
                Paciente paciente= buscarPaciente(dni);
                Listapacientes.remove(paciente);
                System.out.println("el paciente fue eliminado con exito");
                return true;
            }
            System.out.println("El paciente que quiere eliminar fue eliminado anteriormente o no es encuentra en la lista");
            return false;
        }
        int cantidadPacientes(){
            int cantidadPacientes = Listapacientes.size();
            return cantidadPacientes;
        }
        void mostrarPacientes(){
            System.out.println("=========Pacientes de "+ nombre +" ========");
            for (Paciente paciente:Listapacientes){
                System.out.println(paciente.toString());
            }
            System.out.println("==========================");
        }

        @Override
        public String toString() {
            return "Médico [matrícula=" + matricula + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
        }
    }

    static class Paciente {
        private String dni;
        private String nombre;
        private String obraSocial;

        public Paciente(String dni, String nombre, String obraSocial) {
            this.dni = dni;
            this.nombre = nombre;
            this.obraSocial = obraSocial;

        }

        public String getDni() {
            return dni;
        }

        public void setDni(String dni) {
            this.dni = dni;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getObraSocial() {
            return obraSocial;
        }

        public void setObraSocial(String obraSocial) {
            this.obraSocial = obraSocial;
        }

        @Override
        public String toString() {
            return "Paciente [DNI=" + dni + ", nombre=" + nombre + ", obraSocial=" + obraSocial + "]";
        }
    }

    static void main() {

        Medico medico1 = new Medico("M001", "Dr. Peralta", "Oftalmólogo");
        Medico medico2 = new Medico("M002", "Dr. Sambrano", "Clínico");


        Paciente panchito = new Paciente("47338299", "PANCHO", "OSDE");
        Paciente juan = new Paciente("12345678", "Juan", "Swiss Medical");


        medico1.asignarPaciente(panchito);
        medico1.asignarPaciente(juan);
        medico1.mostrarPacientes();



        System.out.println("\n--- Buscar paciente por DNI ---");
        Paciente encontrado = medico1.buscarPaciente("47338299");
        System.out.println(encontrado != null ? "Encontrado: " + encontrado : "No encontrado.");

        System.out.println("\n--- Cantidad de pacientes ---");
        System.out.println("Cantidad: " + medico1.cantidadPacientes());

        System.out.println("\n--- Eliminar a panchito ---");
        medico1.eliminarPaciente(panchito.getDni());
        medico1.mostrarPacientes();

        medico2.asignarPaciente(panchito);
        medico2.mostrarPacientes();

        // Punto 5
        System.out.println("\n--- Punto 5: panchito sigue existiendo! ---");
        System.out.println("aunque hayamos eliminado el pacienta panchito del la lista del Dr peralta, el objeto panchito sigue existiendo");
        System.out.println(panchito.toString());
        System.out.println("el objeto solo se elimino de la lista del Dr peralta");
    }
}


