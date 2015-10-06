package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaAdaptadores.CategoriasArrayAdapter;
import regalapp.co.regalapp.regaUtilidades.RippleView;

/**
 * Created by AndresOs on 29/09/15.
 */
public class HomeControlador extends Activity {

    private ListView LVcategorias;

    CategoriasArrayAdapter categoriasArrayAdapter;
    ArrayList<HashMap<String, String>> arrayCategoria = new ArrayList<HashMap<String, String>>();

    private com.andexert.library.RippleView btn_publicar_regalo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);

        btn_publicar_regalo = (com.andexert.library.RippleView) findViewById(R.id.publicaRegalo);
        LVcategorias = (ListView) findViewById(R.id.LV_categorias);


        //String.valueOf(R.drawable.ic_action_redeem
        String[] categorias = new  String[]{"Hogar","Electrodomésticos","Deportes","Mascotas",
                                            "Tecnología","Servicios","Uso hombre","Uso mujer"};
        categoriasArrayAdapter = new CategoriasArrayAdapter(this,categorias);
        LVcategorias.setAdapter(categoriasArrayAdapter);


        btn_publicar_regalo.setOnRippleCompleteListener(new com.andexert.library.RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(com.andexert.library.RippleView rippleView) {
                Intent publicar_regalo = new Intent(getApplicationContext(), PublicarRegaloControlador.class);
                startActivity(publicar_regalo);
            }
        });






    }
}
