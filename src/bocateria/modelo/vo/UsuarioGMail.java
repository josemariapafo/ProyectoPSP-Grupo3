package bocateria.modelo.vo;

public class UsuarioGMail {
    String usuario, gmail, gPwd;

    public UsuarioGMail(String usuario, String gmail, String gPwd) {
        this.usuario = usuario;
        this.gmail = gmail;
        this.gPwd = gPwd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getgPwd() {
        return gPwd;
    }

    public void setgPwd(String gPwd) {
        this.gPwd = gPwd;
    }
}
