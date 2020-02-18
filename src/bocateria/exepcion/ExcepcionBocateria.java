package bocateria.exepcion;

public class ExcepcionBocateria extends Exception {
    Alertas a = new Alertas();

    public ExcepcionBocateria() {
        super();
    }

    public ExcepcionBocateria(String message) {
        super(message);
        a.error(message);
    }

    public ExcepcionBocateria(String message, Throwable cause) {
        super(message, cause);
        a.error(message);
    }

    public ExcepcionBocateria(Throwable cause) {
        super(cause);
        a.error(cause.toString());
    }

    protected ExcepcionBocateria(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        a.error(message);
    }

}
