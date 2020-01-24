package bocateria.exepcion;

public class ExcepcionBocateria extends Exception {

    public ExcepcionBocateria() {
        super();
    }

    public ExcepcionBocateria(String message) {
        super(message);
    }

    public ExcepcionBocateria(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcepcionBocateria(Throwable cause) {
        super(cause);
    }

    protected ExcepcionBocateria(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
