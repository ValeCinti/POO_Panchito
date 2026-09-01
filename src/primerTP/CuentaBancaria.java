package primerTP;
class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    // Getters y Setters
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    // Métodos de operación
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
        } else {
            System.out.println("El monto a depositar debe ser positivo.");
        }
    }

    public void extraer(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a extraer debe ser positivo.");
            return;
        }
        if (saldo >= monto) {
            saldo -= monto;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public double calcularSaldoFinal() {
        return saldo;  // en la superclase, el saldo final es el saldo actual
    }

    public void mostrarInformacion() {
        System.out.println("Número de cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }
}

// ==================== SUBCLASE CuentaAhorro ====================
class CuentaAhorro extends CuentaBancaria {
    private double tasaInteres;  // ej: 0.05 para 5%

    public CuentaAhorro(String numeroCuenta, String titular, double saldo, double tasaInteres) {
        super(numeroCuenta, titular, saldo);
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(double tasaInteres) { this.tasaInteres = tasaInteres; }

    @Override
    public double calcularSaldoFinal() {
        // SaldoFinal = saldo + saldo * tasaInteres
        return getSaldo() * (1 + tasaInteres);
    }
}

// ==================== SUBCLASE CuentaCorriente ====================
class CuentaCorriente extends CuentaBancaria {
    private double limiteDescubierto;
    private double costoMantenimiento;

    public CuentaCorriente(String numeroCuenta, String titular, double saldo,
                           double limiteDescubierto, double costoMantenimiento) {
        super(numeroCuenta, titular, saldo);
        this.limiteDescubierto = limiteDescubierto;
        this.costoMantenimiento = costoMantenimiento;
    }

    public double getLimiteDescubierto() { return limiteDescubierto; }
    public void setLimiteDescubierto(double limiteDescubierto) { this.limiteDescubierto = limiteDescubierto; }

    public double getCostoMantenimiento() { return costoMantenimiento; }
    public void setCostoMantenimiento(double costoMantenimiento) { this.costoMantenimiento = costoMantenimiento; }

    @Override
    public void extraer(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a extraer debe ser positivo.");
            return;
        }
        // Permite extraer siempre que el saldo resultante no sea menor que -limiteDescubierto
        double nuevoSaldo = getSaldo() - monto;
        if (nuevoSaldo >= -limiteDescubierto) {
            setSaldo(nuevoSaldo);
        } else {
            System.out.println("Extracción no permitida: excede el límite de descubierto.");
        }
    }

    @Override
    public double calcularSaldoFinal() {
        // SaldoFinal = saldo - costoMantenimiento
        return getSaldo() - costoMantenimiento;
    }
}

// ==================== CLASE PRINCIPAL (pública) ====================
    class Main {
    public static void main(String[] args) {
        // a. Crear una CuentaAhorro y una CuentaCorriente
        CuentaAhorro ahorro = new CuentaAhorro("AH-001", "Ana Pérez", 1000.0, 0.05);
        CuentaCorriente corriente = new CuentaCorriente("CC-001", "Luis Gómez", 500.0, 200.0, 10.0);

        // b. Realizar depósitos y extracciones
        ahorro.depositar(200);
        corriente.extraer(100);
        corriente.extraer(700);  // debería permitir porque el límite de descubierto es 200, saldo queda -200

        // c. Guardarlas en un arreglo de tipo CuentaBancaria
        CuentaBancaria[] cuentas = new CuentaBancaria[2];
        cuentas[0] = ahorro;
        cuentas[1] = corriente;

        // d. Recorrer el arreglo y ejecutar calcularSaldoFinal() (polimorfismo)
        System.out.println("=== Saldos finales (polimorfismo) ===");
        for (CuentaBancaria cuenta : cuentas) {
            System.out.println("Cuenta " + cuenta.getNumeroCuenta() +
                    " - Saldo final: " + cuenta.calcularSaldoFinal());
            cuenta.mostrarInformacion();
            System.out.println("-------------------------");
        }
    }
}