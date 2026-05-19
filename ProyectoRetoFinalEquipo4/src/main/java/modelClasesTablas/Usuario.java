package modelClasesTablas;

/**
 * Entidad que modela las cuentas de los usuarios del sistema de control del
 * taller. Permite gestionar las autorizaciones de acceso diferenciando las
 * vistas por el rol.
 *
 * @author sergio camacho
 */
public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private boolean activo;
    private String rol;

    /**
     * Constructor predeterminado vacío obligatorio para gestiones de login y
     * mapeos parciales.
     */
    public Usuario() {
    }

    /**
     * Constructor parametrizado completo para instanciar cuentas de usuario
     * registradas en el sistema.
     *
     * @param id_usuario identificador numérico correlativo de la cuenta.
     * @param nombre nombre de pila del profesor o administrador.
     * @param apellidos apellidos completos.
     * @param email dirección de correo utilizada para realizar el login seguro.
     * @param password hash criptográfico (Bcrypt) de seguridad de la
     * contraseña.
     * @param activo bandera lógica de control para ver si la cuenta está dada
     * de alta.
     * @param rol tipo de perfil asignado de permisos ('administrador' o
     * 'profesor').
     */
    public Usuario(int id_usuario, String nombre, String apellidos, String email, String password, boolean activo, String rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", password=" + password + ", activo=" + activo + '}';
    }
}
