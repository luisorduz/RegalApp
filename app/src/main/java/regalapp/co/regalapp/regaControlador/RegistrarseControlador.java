package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaUtilidades.WebUtil;

/**
 * Created by andresorduz on 28/09/15.
 */
public class RegistrarseControlador extends Activity {

    EditText ETnombre;
    EditText ETusuario;
    EditText ETpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        ETnombre = (EditText) findViewById(R.id.nombre_registro);
        ETusuario = (EditText) findViewById(R.id.usuario_registro);
        ETpassword = (EditText) findViewById(R.id.password_registro);

    }

    public void hacerRegistro(View view) {
        HiloRegistro hiloRegistro = new HiloRegistro();
        hiloRegistro.execute(ETnombre.getText().toString(),
                                ETusuario.getText().toString(),
                                    ETpassword.getText().toString());

    }

    public class HiloRegistro extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            String str_nombre = params[0];
            String str_usuario = params[1];
            String str_password = params[2];
            Log.i("info",str_nombre+str_password+str_usuario);

            Uri.Builder builder = new Uri.Builder();

            builder.appendQueryParameter("nombre",str_nombre)
                    .appendQueryParameter("usuario",str_usuario)
                    .appendQueryParameter("password", str_password);
            String url = "http://www.andresorduz.co/model_regalapp/dao_login/request_registrarUsuarios.php";

            try {
                String respuesta = WebUtil.POSTrequest(url, builder);
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("USER_EXISTS")){
                Toast.makeText(getApplicationContext(), "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "El registro ha sido exitoso", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

}
