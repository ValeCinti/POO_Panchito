package primerTP;

import java.util.ArrayList;
import java.util.List;

public class Clinica {

    static class Paciente {
        private String numeroIdentificacion;
        private String nombreApellido;
        private String fechaNacimiento;
        private String obraSocial;
        private HistoriaClinica historiaClinica;

        public Paciente(String numeroIdentificacion, String nombreApellido, String fechaNacimiento, String obraSocial) {
            this.numeroIdentificacion = numeroIdentificacion;
            this.nombreApellido = nombreApellido;
            this.fechaNacimiento = fechaNacimiento;
            this.obraSocial = obraSocial;
            // Crear historia clínica automáticamente al registrar paciente
            this.historiaClinica = new HistoriaClinica(1, "01/09/2026"); // número fijo para ejemplo
        }

        public String getNumeroIdentificacion() { return numeroIdentificacion; }
        public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }
        public String getNombreApellido() { return nombreApellido; }
        public void setNombreApellido(String nombreApellido) { this.nombreApellido = nombreApellido; }
        public String getFechaNacimiento() { return fechaNacimiento; }
        public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
        public String getObraSocial() { return obraSocial; }
        public void setObraSocial(String obraSocial) { this.obraSocial = obraSocial; }
        public HistoriaClinica getHistoriaClinica() { return historiaClinica; }
        public void setHistoriaClinica(HistoriaClinica historiaClinica) { this.historiaClinica = historiaClinica; }

        // b. Calcular edad (solo años, sin considerar mes/día)
        public int calcularEdad(String fechaReferencia) {
            String[] nac = fechaNacimiento.split("/");
            String[] ref = fechaReferencia.split("/");
            int anioNac = Integer.parseInt(nac[2]);
            int anioRef = Integer.parseInt(ref[2]);
            return anioRef - anioNac;
        }

        // c. Registrar consulta
        public boolean registrarConsulta(Consulta c) {
            return historiaClinica.agregarConsulta(c);
        }

        // d. Costo total de consultas
        public double costoTotalConsultas() {
            double total = 0;
            for (Consulta c : historiaClinica.getConsultas()) {
                total += c.getCosto();
            }
            return total;
        }

        // e. Necesita seguimiento
        public boolean necesitaSeguimiento() {
            for (Consulta c : historiaClinica.getConsultas()) {
                if (c.isRequiereSeguimiento()) return true;
            }
            return false;
        }
    }

    static class HistoriaClinica {
        private int numeroHistoria;
        private ArrayList<Consulta> consultas;
        private int cantidadConsultas;
        private String fechaCreacion;

        public HistoriaClinica(int numeroHistoria, String fechaCreacion) {
            this.numeroHistoria = numeroHistoria;
            this.fechaCreacion = fechaCreacion;
            this.consultas = new ArrayList<>();
            this.cantidadConsultas = 0;
        }

        public int getNumeroHistoria() { return numeroHistoria; }
        public void setNumeroHistoria(int numeroHistoria) { this.numeroHistoria = numeroHistoria; }
        public ArrayList<Consulta> getConsultas() { return consultas; }
        public void setConsultas(ArrayList<Consulta> consultas) {
            this.consultas = consultas;
            this.cantidadConsultas = consultas.size();
        }
        public int getCantidadConsultas() { return cantidadConsultas; }
        public void setCantidadConsultas(int cantidadConsultas) { this.cantidadConsultas = cantidadConsultas; }
        public String getFechaCreacion() { return fechaCreacion; }
        public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

        // a. Agregar consulta
        public boolean agregarConsulta(Consulta c) {
            consultas.add(c);
            setConsultas(consultas);
            return true;
        }

        // b. Buscar por fecha
        public ArrayList<Consulta> buscarPorFecha(String fecha) {
            ArrayList<Consulta> resultado = new ArrayList<>();
            for (Consulta c : consultas) {
                if (c.getFechaAtencion().equals(fecha)) resultado.add(c);
            }
            return resultado;
        }

        // c. Contar consultas con seguimiento
        public int contarConsultasConSeguimiento() {
            int cont = 0;
            for (Consulta c : consultas) {
                if (c.isRequiereSeguimiento()) cont++;
            }
            return cont;
        }

        // d. Costo promedio
        public double calcularCostoPromedio() {
            if (consultas.isEmpty()) return 0;
            double suma = 0;
            for (Consulta c : consultas) suma += c.getCosto();
            return suma / consultas.size();
        }

        // e. Consulta de mayor costo
        public Consulta obtenerConsultaMayorCosto() {
            if (consultas.isEmpty()) return null;
            Consulta mayor = consultas.get(0);
            for (Consulta c : consultas) {
                if (c.getCosto() > mayor.getCosto()) mayor = c;
            }
            return mayor;
        }

        // f. Última consulta registrada
        public Consulta obtenerUltimaConsulta() {
            if (consultas.isEmpty()) return null;
            return consultas.get(consultas.size() - 1);
        }

        // g. Contar por diagnóstico
        public int contarPorDiagnostico(String diagnostico) {
            int cont = 0;
            for (Consulta c : consultas) {
                if (c.getDiagnostico().equalsIgnoreCase(diagnostico)) cont++;
            }
            return cont;
        }
    }

    static class Consulta {
        private String fechaAtencion;
        private String motivo;
        private String diagnostico;
        private String tratamiento;
        private double costo;
        private boolean requiereSeguimiento;

        public Consulta(String fechaAtencion, String motivo, String diagnostico, String tratamiento, double costo, boolean requiereSeguimiento) {
            this.fechaAtencion = fechaAtencion;
            this.motivo = motivo;
            this.diagnostico = diagnostico;
            this.tratamiento = tratamiento;
            this.costo = costo;
            this.requiereSeguimiento = requiereSeguimiento;
        }

        public String getFechaAtencion() { return fechaAtencion; }
        public void setFechaAtencion(String fechaAtencion) { this.fechaAtencion = fechaAtencion; }
        public String getMotivo() { return motivo; }
        public void setMotivo(String motivo) { this.motivo = motivo; }
        public String getDiagnostico() { return diagnostico; }
        public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
        public String getTratamiento() { return tratamiento; }
        public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
        public double getCosto() { return costo; }
        public void setCosto(double costo) { this.costo = costo; }
        public boolean isRequiereSeguimiento() { return requiereSeguimiento; }
        public void setRequiereSeguimiento(boolean requiereSeguimiento) { this.requiereSeguimiento = requiereSeguimiento; }

        // a. Requiere seguimiento
        public boolean requiereSeguimiento() {
            return requiereSeguimiento;
        }

        // b. Costo final con descuento (10% si tiene obra social)
        public double calcularCostoFinal(boolean tieneObraSocial) {
            return tieneObraSocial ? costo * 0.9 : costo;
        }

        // c. Resumen de la atención
        public String mostrarResumen() {
            return "Fecha: " + fechaAtencion + ", Motivo: " + motivo + ", Diagnóstico: " + diagnostico +
                    ", Tratamiento: " + tratamiento + ", Costo: " + costo + ", Seguimiento: " + requiereSeguimiento;
        }
    }

     static void main(String[] args) {
        final String fechaReferencia = "01/09/2026";

        // Crear pacientes
        Paciente p1 = new Paciente("001", "Juan Pérez", "15/03/1990", "OSDE");
        Paciente p2 = new Paciente("002", "María Gómez", "22/11/1985", "Swiss Medical");
        Paciente p3 = new Paciente("003", "Ana López", "05/07/2000", "Particular");

        // Crear consultas
        Consulta c1 = new Consulta("01/09/2026", "Dolor cabeza", "Migraña", "Reposo", 500, true);
        Consulta c2 = new Consulta("03/09/2026", "Fiebre", "Gripe", "Paracetamol", 300, false);
        Consulta c3 = new Consulta("05/09/2026", "Dolor abdominal", "Gastritis", "Omeprazol", 700, true);
        Consulta c4 = new Consulta("10/09/2026", "Mareos", "Laberintitis", "Betahistina", 450, false);
        Consulta c5 = new Consulta("12/09/2026", "Dolor de espalda", "Lumbalgia", "Ejercicios", 250, false);

        // Registrar consultas en pacientes
        p1.registrarConsulta(c1);
        p1.registrarConsulta(c2);
        p1.registrarConsulta(c3);
        p2.registrarConsulta(c4);
        p2.registrarConsulta(c5);

        // Pruebas de Paciente
        System.out.println("=== Paciente 1 ===");
        System.out.println("Edad: " + p1.calcularEdad(fechaReferencia) + " años");
        System.out.println("Costo total: $" + p1.costoTotalConsultas());
        System.out.println("Requiere seguimiento: " + p1.necesitaSeguimiento());

        System.out.println("\n=== Paciente 2 ===");
        System.out.println("Edad: " + p2.calcularEdad(fechaReferencia) + " años");
        System.out.println("Costo total: $" + p2.costoTotalConsultas());
        System.out.println("Requiere seguimiento: " + p2.necesitaSeguimiento());

        // Pruebas de HistoriaClinica
        HistoriaClinica hc = p1.getHistoriaClinica();
        System.out.println("\n=== Historia Clínica de " + p1.getNombreApellido() + " ===");
        System.out.println("Número: " + hc.getNumeroHistoria());
        System.out.println("Fecha creación: " + hc.getFechaCreacion());
        System.out.println("Cantidad consultas: " + hc.getCantidadConsultas());

        System.out.println("\nBuscar por fecha (05/09/2026):");
        ArrayList<Consulta> encontradas = hc.buscarPorFecha("05/09/2026");
        if (encontradas.isEmpty()) {
            System.out.println("No se encontraron consultas en esa fecha.");
        } else {
            for (Consulta c : encontradas) {
                System.out.println(c.mostrarResumen());
            }
        }

        System.out.println("\nConsultas con seguimiento: " + hc.contarConsultasConSeguimiento());
        System.out.println("Costo promedio: $" + hc.calcularCostoPromedio());

        Consulta mayor = hc.obtenerConsultaMayorCosto();
        if (mayor != null) {
            System.out.println("Consulta de mayor costo: " + mayor.mostrarResumen());
        } else {
            System.out.println("No hay consultas.");
        }

        Consulta ultima = hc.obtenerUltimaConsulta();
        if (ultima != null) {
            System.out.println("Última consulta: " + ultima.mostrarResumen());
        } else {
            System.out.println("No hay consultas.");
        }

        System.out.println("Diagnóstico 'Gripe': " + hc.contarPorDiagnostico("Gripe"));

        System.out.println("\n=== Pruebas sobre Consulta c1 ===");
        System.out.println("Requiere seguimiento: " + c1.requiereSeguimiento());
        System.out.println("Costo final con OS: $" + c1.calcularCostoFinal(true));
        System.out.println("Costo final sin OS: $" + c1.calcularCostoFinal(false));
        System.out.println("Resumen: " + c1.mostrarResumen());

        System.out.println("\n=== Prueba historia vacía (p3) ===");
        HistoriaClinica hcVacia = p3.getHistoriaClinica();
        System.out.println("Cantidad: " + hcVacia.getCantidadConsultas());
        System.out.println("Costo promedio: " + hcVacia.calcularCostoPromedio());

        Consulta mayorVacia = hcVacia.obtenerConsultaMayorCosto();
        if (mayorVacia == null) {
            System.out.println("No hay consulta de mayor costo (lista vacía).");
        }

        Consulta ultimaVacia = hcVacia.obtenerUltimaConsulta();
        if (ultimaVacia == null) {
            System.out.println("No hay última consulta (lista vacía).");
        }

        System.out.println("Buscar fecha (01/09/2026): " + hcVacia.buscarPorFecha("01/09/2026").size() + " encontradas.");

        // Prueba con historia con varias consultas (ya la tiene p1 con 3 consultas)
        System.out.println("\n=== Prueba historia con varias consultas (p1) ===");
        System.out.println("Total consultas: " + hc.getCantidadConsultas());
        System.out.println("Costo promedio: $" + hc.calcularCostoPromedio());
    }
}


