package primerTP;

enum TipoNotificacion {
    EMAIL,SMS
}

class Pedido {
    private int numero;
    private String estado;
    private int total;

    public Pedido(int numero,int total){
        this.numero=numero;
        this.total=total;
        this.estado="NO LISTO";
    }

    public int getNumero(){return numero;}
    public String getEstado(){return estado;}
    public int getTotal(){return total;}

    public boolean cambiarEstado(String nuevoEstado){
        if(!estado.equals(nuevoEstado)){
            estado=nuevoEstado;
            return true;
        }
        return false;
    }
}

class Notificador {
    private String destinatario;
    private String mensaje;
    private TipoNotificacion tipo;

    public Notificador(String destinatario,TipoNotificacion tipo){
        this.destinatario=destinatario;
        this.tipo=tipo;
        this.mensaje="";
    }

    public String getDestinatario(){return destinatario;}
    public void setDestinatario(String destinatario){this.destinatario=destinatario;}
    public String getMensaje(){return mensaje;}
    public void setMensaje(String mensaje){this.mensaje=mensaje;}
    public TipoNotificacion getTipo(){return tipo;}
    public void setTipo(TipoNotificacion tipo){this.tipo=tipo;}

    public void enviar(String mensaje){
        this.mensaje=mensaje;
        if(tipo==TipoNotificacion.EMAIL)System.out.println("Enviando Email a "+destinatario+": "+mensaje);
        else if(tipo==TipoNotificacion.SMS)System.out.println("Enviando SMS a "+destinatario+": "+mensaje);
    }
}

class GestorPedido {
    public void marcarComoListo(Pedido pedido,Notificador notificador){
        if(pedido==null||notificador==null)return;
        if(pedido.cambiarEstado("LISTO")){
            String mensaje="El pedido "+pedido.getNumero()+" está listo para retirar";
            notificador.enviar(mensaje);
        }
    }
}

public class ServicioDeNotificaciones {
    public static void main(String[] args){
        Pedido pedido1=new Pedido(11,33);
        Notificador notificadorEmail=new Notificador("example@gmail.com",TipoNotificacion.EMAIL);
        Notificador notificadorSms=new Notificador("+5491112345678",TipoNotificacion.SMS);
        GestorPedido gestor=new GestorPedido();

        gestor.marcarComoListo(pedido1,notificadorEmail);
        gestor.marcarComoListo(pedido1,notificadorSms);
    }
}