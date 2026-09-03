package primerTP;
import java.util.ArrayList;

public class MedicoPaciente {
    static class Medico {
        private String matricula;
        private String nombre;
        private String especialidad;
        private ArrayList<Paciente> pacientes;

        public Medico(String matricula,String nombre,String especialidad){
            this.matricula=matricula;
            this.nombre=nombre;
            this.especialidad=especialidad;
            this.pacientes=new ArrayList<>();
        }

        public String getMatricula(){return matricula;}
        public void setMatricula(String matricula){this.matricula=matricula;}
        public String getNombre(){return nombre;}
        public void setNombre(String nombre){this.nombre=nombre;}
        public String getEspecialidad(){return especialidad;}
        public void setEspecialidad(String especialidad){this.especialidad=especialidad;}

        public boolean asignarPaciente(Paciente paciente){
            if(paciente==null||buscarPaciente(paciente.getDni())!=null)return false;
            pacientes.add(paciente);
            paciente.agregarMedico(this);
            return true;
        }

        public Paciente buscarPaciente(String dni){
            for(Paciente paciente:pacientes){
                if(paciente.getDni().equals(dni))return paciente;
            }
            return null;
        }

        public boolean eliminarPaciente(String dni){
            Paciente paciente=buscarPaciente(dni);
            if(paciente==null)return false;
            pacientes.remove(paciente);
            paciente.eliminarMedico(this);
            return true;
        }

        public int cantidadPacientes(){return pacientes.size();}

        public void mostrarPacientes(){
            System.out.println("========= Pacientes de "+nombre+" =========");
            for(Paciente paciente:pacientes)System.out.println(paciente);
            System.out.println("===========================================");
        }

        @Override
        public String toString(){return "Médico [matrícula="+matricula+", nombre="+nombre+", especialidad="+especialidad+"]";}
    }

    static class Paciente {
        private String dni;
        private String nombre;
        private String obraSocial;
        private ArrayList<Medico> medicos;

        public Paciente(String dni,String nombre,String obraSocial){
            this.dni=dni;
            this.nombre=nombre;
            this.obraSocial=obraSocial;
            this.medicos=new ArrayList<>();
        }

        public String getDni(){return dni;}
        public void setDni(String dni){this.dni=dni;}
        public String getNombre(){return nombre;}
        public void setNombre(String nombre){this.nombre=nombre;}
        public String getObraSocial(){return obraSocial;}
        public void setObraSocial(String obraSocial){this.obraSocial=obraSocial;}

        private void agregarMedico(Medico medico){
            if(medico!=null&&!medicos.contains(medico))medicos.add(medico);
        }

        private void eliminarMedico(Medico medico){medicos.remove(medico);}

        @Override
        public String toString(){return "Paciente [DNI="+dni+", nombre="+nombre+", obraSocial="+obraSocial+"]";}
    }

    public static void main(String[] args){
        Medico medico1=new Medico("M001","Dr. Peralta","Oftalmólogo");
        Medico medico2=new Medico("M002","Dr. Sambrano","Clínico");
        Paciente lautaro=new Paciente("47338299","PANCHO","OSDE");
        Paciente juan=new Paciente("12345678","Juan","Swiss Medical");

        medico1.asignarPaciente(lautaro);
        medico1.asignarPaciente(juan);
        medico2.asignarPaciente(lautaro);

        medico1.mostrarPacientes();
        medico2.mostrarPacientes();

        System.out.println("\n--- Buscar paciente ---");
        System.out.println(medico1.buscarPaciente("47338299"));

        System.out.println("\n--- Cantidad ---");
        System.out.println(medico1.cantidadPacientes());

        System.out.println("\n--- Eliminar paciente de medico1 ---");
        medico1.eliminarPaciente("47338299");
        medico1.mostrarPacientes();

        System.out.println("\n--- El paciente sigue existiendo ---");
        System.out.println(lautaro);
        medico2.mostrarPacientes();
    }
}