package primerTP;
class Pedido{
        private int numero;
        private String estado;
        private int total;

        public Pedido(int numero, int total) {
            this.numero =numero;
            this.estado ="NO LISTO";
            this.total =total;

        }

        public String getEstado() {
            return estado;
        }

        public int getNumero() {
            return numero;
        }

        public int getTotal() {
            return total;
        }

        public boolean setEstado(String nuevoestado) {
            if (estado != nuevoestado) {
                this.estado = nuevoestado;
                return true;
            } else {
                return false;
            }
        }
    }
    class Notificador{
        private String destinatario;
        private String mensaje;
        private String TipoDeMensaje;
        public Notificador(String destinatario, String tipoDeMensaje){
            this.destinatario = destinatario;
            this.mensaje = "";
            this.TipoDeMensaje = tipoDeMensaje;
        }
        public void enviar(){
            if(mensaje==null || mensaje.isEmpty()){
                System.out.println("no hay mensaje para mandar");
            }else{
                switch (TipoDeMensaje){
                    case "EMAIL":
                        System.out.println("Enviando Email a "+destinatario+":"+mensaje);
                        break;
                    case "SMS":
                        System.out.println("Enviando SMS a "+destinatario+":"+mensaje);
                        break;
                    default:
                        System.out.println("Tipo de mensaje no soportado");
                }
            }
        }
        public String getDestinatario() {return destinatario;}
        public String getMensaje() {return mensaje;}
        public String getTipoDeMensaje() {return TipoDeMensaje;}
        public void setDestinatario(String destinatario) {this.destinatario = destinatario;}
        public void setMensaje(String mensaje) {this.mensaje = mensaje;}
        public void setTipoDeMensaje(String tipoDeMensaje) {TipoDeMensaje = tipoDeMensaje;}

    }


    class GestorPedido {
        public void realizarcambiodeestado(Pedido pedido,Notificador notificador){
            boolean cambio = pedido.setEstado("LISTO");
            if (cambio == true){
                String mensaje ="El pedido "+pedido.getNumero()+ " esta listo para retirar";
                notificador.setMensaje(mensaje);
                notificador.enviar();
            }else{
                System.out.println("El pedido que queria cambiar de estado ya se encontraba en estado LISTO");
            }
        }
    }
    public class ServicioDeNotificaciones {
        public static void main(String[] args) {
            Pedido pedido1 = new Pedido(11,33);
            Notificador notiSMS = new Notificador("+5491112345678","SMS");
            Notificador notiEMAIL = new Notificador("example@gmail.com","EMAIL");
            GestorPedido gestorPedido = new GestorPedido();

            gestorPedido.realizarcambiodeestado(pedido1,notiEMAIL);
            gestorPedido.realizarcambiodeestado(pedido1,notiSMS);


        }
    }