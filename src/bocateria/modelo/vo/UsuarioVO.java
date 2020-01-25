package bocateria.modelo.vo;

public class UsuarioVO {
    String usuario;

    @Override
    public String toString() {
        return "UsuarioVO{" +
                "usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    String nombre;
    String apellidos;
    String email;
    String contraseña;
    String direccion;
    String localidad;
    String telefono;

    public UsuarioVO() {
    }

    public UsuarioVO(String nombre, String apellidos, String email, String usuario, String contraseña, String direccion, String localidad, String telefono) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.localidad = localidad;
        this.telefono = telefono;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
}
