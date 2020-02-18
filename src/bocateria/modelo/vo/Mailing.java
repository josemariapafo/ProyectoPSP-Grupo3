package bocateria.modelo.vo;

import bocateria.Main;

public class Mailing {
    private String server = "smtp.gmail.com";
    private String gUser = "scuesta.test@gmail.com";
    private String gPwd = "testpwd1";
    private int puerto = 587;
    private String remintente = "scuesta.test@gmail.com";
    private String destinatario = "";
    private String asunto = "";
    private String mensaje = "";

    public Mailing(String destinatario) {
        this.destinatario = destinatario;
    }


}
