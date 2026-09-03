package primerTP;

class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta,String titular,double saldo){
        this.numeroCuenta=numeroCuenta;
        this.titular=titular;
        this.saldo=saldo;
    }

    public String getNumeroCuenta(){return numeroCuenta;}
    public void setNumeroCuenta(String numeroCuenta){this.numeroCuenta=numeroCuenta;}
    public String getTitular(){return titular;}
    public void setTitular(String titular){this.titular=titular;}
    public double getSaldo(){return saldo;}
    public void setSaldo(double saldo){this.saldo=saldo;}

    public void depositar(double monto){
        if(monto>0)saldo+=monto;
    }

    public void extraer(double monto){
        if(monto>0&&saldo>=monto)saldo-=monto;
    }

    public double calcularSaldoFinal(){return saldo;}

    public void mostrarInformacion(){
        System.out.println("Número de cuenta: "+numeroCuenta);
        System.out.println("Titular: "+titular);
        System.out.println("Saldo: "+saldo);
    }
}

class CuentaAhorro extends CuentaBancaria {
    private double tasaInteres;

    public CuentaAhorro(String numeroCuenta,String titular,double saldo,double tasaInteres){
        super(numeroCuenta,titular,saldo);
        this.tasaInteres=tasaInteres;
    }

    public double getTasaInteres(){return tasaInteres;}
    public void setTasaInteres(double tasaInteres){this.tasaInteres=tasaInteres;}

    @Override
    public double calcularSaldoFinal(){return getSaldo()+getSaldo()*tasaInteres;}
}

class CuentaCorriente extends CuentaBancaria {
    private double limiteDescubierto;
    private double costoMantenimiento;

    public CuentaCorriente(String numeroCuenta,String titular,double saldo,double limiteDescubierto,double costoMantenimiento){
        super(numeroCuenta,titular,saldo);
        this.limiteDescubierto=limiteDescubierto;
        this.costoMantenimiento=costoMantenimiento;
    }

    public double getLimiteDescubierto(){return limiteDescubierto;}
    public void setLimiteDescubierto(double limiteDescubierto){this.limiteDescubierto=limiteDescubierto;}
    public double getCostoMantenimiento(){return costoMantenimiento;}
    public void setCostoMantenimiento(double costoMantenimiento){this.costoMantenimiento=costoMantenimiento;}

    @Override
    public void extraer(double monto){
        double nuevoSaldo=getSaldo()-monto;
        if(monto>0&&nuevoSaldo>=-limiteDescubierto)setSaldo(nuevoSaldo);
    }

    @Override
    public double calcularSaldoFinal(){return getSaldo()-costoMantenimiento;}
}

class Main {
    public static void main(String[] args){
        CuentaAhorro ahorro=new CuentaAhorro("AH-001","Ana Pérez",1000,0.05);
        CuentaCorriente corriente=new CuentaCorriente("CC-001","Luis Gómez",500,200,10);

        ahorro.depositar(200);
        corriente.extraer(100);
        corriente.extraer(700);

        CuentaBancaria[] cuentas={ahorro,corriente};

        for(CuentaBancaria cuenta:cuentas){
            System.out.println("Cuenta "+cuenta.getNumeroCuenta()+" - Saldo final: "+cuenta.calcularSaldoFinal());
            cuenta.mostrarInformacion();
            System.out.println("-------------------------");
        }
    }
}