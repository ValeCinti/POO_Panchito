package primerTP;
import java.util.ArrayList;

public class Clinica {
    static class Paciente {
        private String numeroIdentificacion;
        private String nombreApellido;
        private String fechaNacimiento;
        private String obraSocial;
        private final HistoriaClinica historiaClinica;

        public Paciente(String numeroIdentificacion,String nombreApellido,String fechaNacimiento,String obraSocial,String fechaRegistro){
            this.numeroIdentificacion=numeroIdentificacion;
            this.nombreApellido=nombreApellido;
            this.fechaNacimiento=fechaNacimiento;
            this.obraSocial=obraSocial;
            this.historiaClinica=new HistoriaClinica(fechaRegistro);
        }

        public String getNumeroIdentificacion(){return numeroIdentificacion;}
        public void setNumeroIdentificacion(String numeroIdentificacion){this.numeroIdentificacion=numeroIdentificacion;}
        public String getNombreApellido(){return nombreApellido;}
        public void setNombreApellido(String nombreApellido){this.nombreApellido=nombreApellido;}
        public String getFechaNacimiento(){return fechaNacimiento;}
        public void setFechaNacimiento(String fechaNacimiento){this.fechaNacimiento=fechaNacimiento;}
        public String getObraSocial(){return obraSocial;}
        public void setObraSocial(String obraSocial){this.obraSocial=obraSocial;}
        public HistoriaClinica getHistoriaClinica(){return historiaClinica;}

        public int calcularEdad(String fechaReferencia){
            String[] nacimiento=fechaNacimiento.split("/");
            String[] referencia=fechaReferencia.split("/");
            int diaNacimiento=Integer.parseInt(nacimiento[0]);
            int mesNacimiento=Integer.parseInt(nacimiento[1]);
            int anioNacimiento=Integer.parseInt(nacimiento[2]);
            int diaReferencia=Integer.parseInt(referencia[0]);
            int mesReferencia=Integer.parseInt(referencia[1]);
            int anioReferencia=Integer.parseInt(referencia[2]);
            int edad=anioReferencia-anioNacimiento;
            if(mesReferencia<mesNacimiento||(mesReferencia==mesNacimiento&&diaReferencia<diaNacimiento))edad--;
            return edad;
        }

        public boolean registrarConsulta(Consulta consulta){return historiaClinica.agregarConsulta(consulta);}

        public double costoTotalConsultas(){
            double total=0;
            boolean tieneObraSocial=obraSocial!=null&&!obraSocial.isEmpty();
            for(Consulta consulta:historiaClinica.getConsultas())total+=consulta.calcularCostoFinal(tieneObraSocial);
            return total;
        }

        public boolean necesitaSeguimiento(){return historiaClinica.contarConsultasConSeguimiento()>0;}
    }

    static class HistoriaClinica {
        private static final int CAPACIDAD_MAXIMA=10;
        private static int proximoNumeroHistoria=1;
        private int numeroHistoria;
        private ArrayList<Consulta> consultas;
        private String fechaCreacion;

        private HistoriaClinica(String fechaCreacion){
            this.numeroHistoria=proximoNumeroHistoria++;
            this.consultas=new ArrayList<>();
            this.fechaCreacion=fechaCreacion;
        }

        public int getNumeroHistoria(){return numeroHistoria;}
        public ArrayList<Consulta> getConsultas(){return new ArrayList<>(consultas);}
        public int getCantidadConsultas(){return consultas.size();}
        public String getFechaCreacion(){return fechaCreacion;}
        public int getCapacidadMaxima(){return CAPACIDAD_MAXIMA;}

        public boolean agregarConsulta(Consulta consulta){
            if(consulta==null||consultas.size()>=CAPACIDAD_MAXIMA)return false;
            consultas.add(consulta);
            return true;
        }

        public ArrayList<Consulta> buscarPorFecha(String fecha){
            ArrayList<Consulta> resultado=new ArrayList<>();
            for(Consulta consulta:consultas)if(consulta.getFechaAtencion().equals(fecha))resultado.add(consulta);
            return resultado;
        }

        public int contarConsultasConSeguimiento(){
            int contador=0;
            for(Consulta consulta:consultas)if(consulta.requiereSeguimiento())contador++;
            return contador;
        }

        public double calcularCostoPromedio(){
            if(consultas.isEmpty())return 0;
            double suma=0;
            for(Consulta consulta:consultas)suma+=consulta.getCosto();
            return suma/consultas.size();
        }

        public Consulta obtenerConsultaMayorCosto(){
            if(consultas.isEmpty())return null;
            Consulta mayor=consultas.get(0);
            for(Consulta consulta:consultas)if(consulta.getCosto()>mayor.getCosto())mayor=consulta;
            return mayor;
        }

        public Consulta obtenerUltimaConsulta(){
            if(consultas.isEmpty())return null;
            return consultas.get(consultas.size()-1);
        }

        public int contarPorDiagnostico(String diagnostico){
            int contador=0;
            for(Consulta consulta:consultas){
                if(consulta.getDiagnostico()!=null&&consulta.getDiagnostico().equals(diagnostico))contador++;
            }
            return contador;
        }
    }

    static class Consulta {
        private static final double DESCUENTO_OBRA_SOCIAL=0.10;
        private String fechaAtencion;
        private String motivo;
        private String diagnostico;
        private String tratamiento;
        private double costo;
        private boolean requiereSeguimiento;

        public Consulta(String fechaAtencion,String motivo,String diagnostico,String tratamiento,double costo,boolean requiereSeguimiento){
            this.fechaAtencion=fechaAtencion;
            this.motivo=motivo;
            this.diagnostico=diagnostico;
            this.tratamiento=tratamiento;
            this.costo=costo;
            this.requiereSeguimiento=requiereSeguimiento;
        }

        public String getFechaAtencion(){return fechaAtencion;}
        public void setFechaAtencion(String fechaAtencion){this.fechaAtencion=fechaAtencion;}
        public String getMotivo(){return motivo;}
        public void setMotivo(String motivo){this.motivo=motivo;}
        public String getDiagnostico(){return diagnostico;}
        public void setDiagnostico(String diagnostico){this.diagnostico=diagnostico;}
        public String getTratamiento(){return tratamiento;}
        public void setTratamiento(String tratamiento){this.tratamiento=tratamiento;}
        public double getCosto(){return costo;}
        public void setCosto(double costo){this.costo=costo;}
        public boolean requiereSeguimiento(){return requiereSeguimiento;}
        public void setRequiereSeguimiento(boolean requiereSeguimiento){this.requiereSeguimiento=requiereSeguimiento;}

        public double calcularCostoFinal(boolean tieneObraSocial){
            if(tieneObraSocial)return costo*(1-DESCUENTO_OBRA_SOCIAL);
            return costo;
        }

        public String mostrarResumen(){
            return "Fecha: "+fechaAtencion+", Motivo: "+motivo+", Diagnóstico: "+diagnostico+", Tratamiento: "+tratamiento+", Costo: "+costo+", Seguimiento: "+requiereSeguimiento;
        }
    }

    public static void main(String[] args){
        Paciente paciente=new Paciente("001","Juan Pérez","15/03/1990","OSDE","03/09/2026");

        System.out.println("--- Historia vacía ---");
        System.out.println("Número de historia: "+paciente.getHistoriaClinica().getNumeroHistoria());
        System.out.println("Fecha de creación: "+paciente.getHistoriaClinica().getFechaCreacion());
        System.out.println("Cantidad: "+paciente.getHistoriaClinica().getCantidadConsultas());

        Consulta c1=new Consulta("01/09/2026","Dolor de cabeza","Migraña","Reposo",500,true);
        Consulta c2=new Consulta("03/09/2026","Fiebre","Gripe","Reposo",300,false);

        paciente.registrarConsulta(c1);
        paciente.registrarConsulta(c2);

        HistoriaClinica historia=paciente.getHistoriaClinica();

        System.out.println("\n--- Varias consultas ---");
        System.out.println("Cantidad: "+historia.getCantidadConsultas());
        System.out.println("Edad: "+paciente.calcularEdad("03/09/2026"));
        System.out.println("Costo total: $"+paciente.costoTotalConsultas());
        System.out.println("Necesita seguimiento: "+paciente.necesitaSeguimiento());
        System.out.println("Costo promedio: $"+historia.calcularCostoPromedio());
        System.out.println("Consultas con seguimiento: "+historia.contarConsultasConSeguimiento());
        System.out.println("Consultas con diagnóstico Gripe: "+historia.contarPorDiagnostico("Gripe"));
        System.out.println("Consultas del 01/09/2026: "+historia.buscarPorFecha("01/09/2026").size());

        System.out.println("\n--- Historia completa ---");
        while(historia.getCantidadConsultas()<historia.getCapacidadMaxima()){
            historia.agregarConsulta(new Consulta("10/09/2026","Control","Control","Seguimiento",100,false));
        }

        System.out.println("Cantidad: "+historia.getCantidadConsultas());
        boolean agregada=historia.agregarConsulta(new Consulta("20/09/2026","Extra","Extra","Extra",100,false));
        System.out.println("¿Se pudo agregar otra consulta? "+agregada);

        System.out.println("\n--- Consulta de mayor costo ---");
        System.out.println(historia.obtenerConsultaMayorCosto().mostrarResumen());

        System.out.println("\n--- Última consulta ---");
        System.out.println(historia.obtenerUltimaConsulta().mostrarResumen());
    }
}