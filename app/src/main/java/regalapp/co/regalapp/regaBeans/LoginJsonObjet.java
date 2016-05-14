package regalapp.co.regalapp.regaBeans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by andresorduz on 13/04/16.
 */
public class LoginJsonObjet {

/*{
        "Success": "correcto",
            "Login": [
        {
                "usu_id": 13,
                "usu_nombre": "1",
                "usu_apellido": "2",
                "usu_username": "andres",
                "usu_contrasena": "123",
                "usu_email": "5",
                "usu_fecha_nacimiento": "1991-09-09",
                "usu_fecha_registro": "2016-04-03 21:02:11"
        }
        ]
    }
*/

    @SerializedName("Success")
    private String Success;
    @SerializedName("Login")
    private ArrayList<LoginBean> beanLogins;

    public LoginJsonObjet(String success, ArrayList<LoginBean> beanLogins) {
        Success = success;
        this.beanLogins = beanLogins;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public ArrayList<LoginBean> getBeanLogins() {
        return beanLogins;
    }

    public void setBeanLogins(ArrayList<LoginBean> beanLogins) {
        this.beanLogins = beanLogins;
    }
}
