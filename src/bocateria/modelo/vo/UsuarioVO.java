package bocateria.modelo.vo;

public class UsuarioVO {

    private String usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasena;
    private String direccion;
    private String localidad;
    private String telefono;
    private boolean gmailSetted;
    private String gMailVal;
    private String gPwdVal;


    /*
    Añadida vista para enviar correos
    Añadida vista para configurar un correo de gmail
    Añadido atributo que almacena si el usuario ha configurado gmail
    Falta crear una tabla en la base de datos que guarde el email configurado del usuario
     */
    public UsuarioVO() {
    }

    public UsuarioVO(String nombre, String apellidos, String email, String usuario, String contrasena, String direccion, String localidad, String telefono) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
        this.gmailSetted = false;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isGmailSetted() {
        return gmailSetted;
    }

    public void setGmailSetted(boolean gmailSetted) {
        this.gmailSetted = gmailSetted;
    }



    public String getgMailVal() {
        return gMailVal;
    }

    public void setgMailVal(String gMailVal) {
        this.gMailVal = gMailVal;
    }

    public String getgPwdVal() {
        return gPwdVal;
    }

    public void setgPwdVal(String gPwdVal) {
        this.gPwdVal = gPwdVal;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" +
                "usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contrasena + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
