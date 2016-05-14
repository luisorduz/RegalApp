package regalapp.co.regalapp.regaBeans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by andresorduz on 25/04/16.
 */
public class LoginBean {


    /**
     * usu_id : 13
     * usu_nombre : 1
     * usu_apellido : 2
     * usu_username : andres
     * usu_contrasena : 123
     * usu_email : 5
     * usu_fecha_nacimiento : 1991-09-09
     * usu_fecha_registro : 2016-04-03 21:02:11
     */

    @SerializedName("usu_id")
    private int usu_id;
    @SerializedName("usu_nombre")
    private String usu_nombre;
    @SerializedName("usu_apellido")
    private String usu_apellido;
    @SerializedName("usu_username")
    private String usu_username;
    @SerializedName("usu_contrasena")
    private String usu_contrasena;
    @SerializedName("usu_email")
    private String usu_email;
    @SerializedName("usu_fecha_nacimiento")
    private String usu_fecha_nacimiento;
    @SerializedName("usu_fecha_registro")
    private String usu_fecha_registro;

    public LoginBean(int usu_id, String usu_nombre, String usu_apellido, String usu_username, String usu_contrasena, String usu_email, String usu_fecha_nacimiento, String usu_fecha_registro) {
        this.usu_id = usu_id;
        this.usu_nombre = usu_nombre;
        this.usu_apellido = usu_apellido;
        this.usu_username = usu_username;
        this.usu_contrasena = usu_contrasena;
        this.usu_email = usu_email;
        this.usu_fecha_nacimiento = usu_fecha_nacimiento;
        this.usu_fecha_registro = usu_fecha_registro;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_apellido() {
        return usu_apellido;
    }

    public void setUsu_apellido(String usu_apellido) {
        this.usu_apellido = usu_apellido;
    }

    public String getUsu_username() {
        return usu_username;
    }

    public void setUsu_username(String usu_username) {
        this.usu_username = usu_username;
    }

    public String getUsu_contrasena() {
        return usu_contrasena;
    }

    public void setUsu_contrasena(String usu_contrasena) {
        this.usu_contrasena = usu_contrasena;
    }

    public String getUsu_email() {
        return usu_email;
    }

    public void setUsu_email(String usu_email) {
        this.usu_email = usu_email;
    }

    public String getUsu_fecha_nacimiento() {
        return usu_fecha_nacimiento;
    }

    public void setUsu_fecha_nacimiento(String usu_fecha_nacimiento) {
        this.usu_fecha_nacimiento = usu_fecha_nacimiento;
    }

    public String getUsu_fecha_registro() {
        return usu_fecha_registro;
    }

    public void setUsu_fecha_registro(String usu_fecha_registro) {
        this.usu_fecha_registro = usu_fecha_registro;
    }
}
