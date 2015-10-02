package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaAdaptadores.CategoriasArrayAdapter;

/**
 * Created by AndresOs on 29/09/15.
 */
public class CasaControlador extends Activity {

    private ListView LVcategorias;
    CategoriasArrayAdapter categoriasArrayAdapter;
    ArrayList<HashMap<String, String>> arrayCategoria = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);

        LVcategorias = (ListView) findViewById(R.id.LV_categorias);

        //String.valueOf(R.drawable.ic_action_redeem
        String[] categorias = new  String[]{"Hogar","Electrodomésticos","Deportes","Mascotas",
                                            "Tecnología","Servicios","Uso hombre","Uso mujer"};

        categoriasArrayAdapter = new CategoriasArrayAdapter(this,categorias);

        LVcategorias.setAdapter(categoriasArrayAdapter);

    }
}
