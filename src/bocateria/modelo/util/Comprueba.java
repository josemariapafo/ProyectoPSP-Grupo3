package bocateria.modelo.util;

import bocateria.exepcion.Alertas;

import java.util.regex.Pattern;

public class Comprueba {
    private static Alertas alerta;

    public static boolean email(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            alerta.error("E-Mail VACIO");
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean gmail(String email) {
        if (email.contains("@gmail.com"))
            return true;
        else
            return false;
    }

    public static boolean pwd(String p1, String p2) {
        return p1.equals(p2);
    }

    public static boolean longitud(String str, int maxLength) {
        return str.length() <= maxLength;
    }
}
