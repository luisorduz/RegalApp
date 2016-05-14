package regalapp.co.regalapp.regaControlador;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaBeans.LoginBean;
import regalapp.co.regalapp.regaBeans.LoginJsonObjet;
import regalapp.co.regalapp.regaUtilidades.RippleView;
import regalapp.co.regalapp.regaUtilidades.WebUtil;

public class LoginControlador extends Activity {

    private ProgressDialog progressDialog;
    private RippleView btn_Ingresar;
    private TextView tvRegistrarse;
    private ArrayList<LoginBean> loginArrayList;
    LoginJsonObjet usuarioJsonObjet;
    private StringBuffer topicList;

    EditText login_Usuario;
    EditText login_Contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        login_Usuario = (EditText) findViewById(R.id.usuario_login);
        login_Contrasena = (EditText) findViewById(R.id.contrasena_login);


        tvRegistrarse = (TextView) findViewById(R.id.registrarse);
        btn_Ingresar = (RippleView) findViewById(R.id.btn_Ingresar);


        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingresar2 = new Intent(getApplicationContext(), RegistrarseControlador.class);
                startActivity(ingresar2);
            }
        });
    }

    public void btnIngresar(View view) {

       HiloLogueo hiloRegistro = new HiloLogueo();
        hiloRegistro.execute(login_Usuario.getText().toString(),
                login_Contrasena.getText().toString());
    }


    public class HiloLogueo extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(LoginControlador.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Cargando...");
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... params) {

            String str_usuario = params[0];
            String str_password = params[1];
            Log.i("info", str_password + str_usuario);

            Uri.Builder builder = new Uri.Builder();

            builder.appendQueryParameter("username", str_usuario)
                    .appendQueryParameter("contrasena", str_password);
            String url = "http://andresorduz.co/model_regalapp/dao_login/request_loguearUsuarios.php";

            try {
                String respuesta = WebUtil.POSTrequest(url, builder);
                Log.i("respuesta login: ", respuesta);

                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String respuesta) {
            super.onPostExecute(respuesta);

            //Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_SHORT).show();
            usuarioJsonObjet = new GsonBuilder().create().fromJson(respuesta, LoginJsonObjet.class);
            Log.e("Subject: ", usuarioJsonObjet.getSuccess() + "");

            if (usuarioJsonObjet.getSuccess().equals("fallo")){
                Toast.makeText(getApplicationContext(), usuarioJsonObjet.getSuccess()+"", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }else {

                loginArrayList = usuarioJsonObjet.getBeanLogins();
                topicList=new StringBuffer();
                for(LoginBean topic: loginArrayList){
                    Log.e("topic title: ",topic.getUsu_id()+"");
                    topicList.append("* "+topic.getUsu_id()+"\n");
                }

                Intent ingresar = new Intent(getApplicationContext(),HomeControlador.class);
                startActivity(ingresar);
                Toast.makeText(getApplicationContext(), usuarioJsonObjet.getSuccess()+"", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }

           /* Gson gson = new Gson();
            Type tipo = new TypeToken<ArrayList<Usuario>>(){}.getType();

            ArrayList<Usuario> usuarios = gson.fromJson(respuesta,tipo);
            */
            /*for (int i =0 ; i < usuarios.size(); i++){

               String resultado = String.valueOf(usuarios.get(1).getUsu_email());
                 Log.i("RESULTADO USUARIO", resultado);
                break;
            }*/

            /*
            if (respuesta.equals("USER_EXISTS")){
                Toast.makeText(getApplicationContext(), "Este usuario ya esta registrado", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(), "El registro ha sido exitoso", Toast.LENGTH_SHORT).show();
            }*/
            //finish();
        }
    }
}
