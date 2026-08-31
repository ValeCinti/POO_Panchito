public class SistemaDeCuentasBancarias {
    private String NumeroCuenta;
    private String titular;
    private double Saldo;

    public double getSaldo() {return Saldo;}
    public String getTitular() {return titular;}
    public String getNumeroCuenta() {return NumeroCuenta;}

    public void setNumeroCuenta(String numeroCuenta) {NumeroCuenta = numeroCuenta;}
    public void setTitular(String titular) {this.titular = titular;}
    public void setSaldo(double saldo) {Saldo = saldo;}


    public void SistemaDeCuentasBancarias(double saldo, String titular, String numeroCuenta){
        this.Saldo= saldo;
        this.titular = titular;
        this.NumeroCuenta = numeroCuenta;


    }

    public double calcularsaldofinal(){
        return getSaldo();
    }


    public void Depositar(double monto){
        calcularsaldofinal();
        setSaldo(getSaldo()+monto);
    }
    public void extraer(double monto){
        if (getSaldo() > 0){
           if (getSaldo() >= monto){
               setSaldo(getSaldo()-monto);
           }

        }else{
            System.out.println("no hay salgo suficiente para realizar la accion");
        }
    }
    public void MostrarInformacion(){
        System.out.println("NumeroDecuenta: " + getNumeroCuenta());
        System.out.println("Titular: "+ getTitular() );
        System.out.println("Saldo: "+ getSaldo());
    }


    public class CuentaAhorro extends SistemaDeCuentasBancarias{
        protected double tasaInteres;
        public double getTasaInteres(){return tasaInteres;}
        @Override
        public double calcularsaldofinal(){
            return getTasaInteres() + getSaldo();
    }

    public class CuentaCorriente extends SistemaDeCuentasBancarias{
        protected double limiteDescubierto;
        protected double costoMantenimiento;


        public double getLimiteDescubierto() {
            return limiteDescubierto;
        }

        public double getCostoMantenimiento() {
            return costoMantenimiento;
        }

        @Override

        public double calcularsaldofinal(){
            return getCostoMantenimiento() + getSaldo();
        }

        @Override
        public void extraer(double monto) {
           if(getLimiteDescubierto()>=monto){

           }
        }



    }
}


