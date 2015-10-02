package regalapp.co.regalapp.regaControlador;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaUtilidades.RippleView;

public class LoginControlador extends Activity {

    private RippleView btn_Ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);



        btn_Ingresar = (RippleView) findViewById(R.id.btn_Ingresar);

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingresar = new Intent(getApplicationContext(),HomeControlador.class);
                startActivity(ingresar);
            }
        });
    }

}
