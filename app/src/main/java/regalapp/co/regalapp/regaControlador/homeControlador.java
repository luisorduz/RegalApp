package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
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
public class homeControlador extends Activity {

    private ListView LVcategorias;
    CategoriasArrayAdapter categoriasArrayAdapter;
    ArrayList<HashMap<String, String>> arrayCategoria = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LVcategorias = (ListView) findViewById(R.id.LV_categorias);


        //String.valueOf(R.drawable.ic_action_redeem
        String[] categorias = new  String[]{"Cat1","Cat2","Cat3","Cat1","Cat2","Cat3","Cat1","Cat2","Cat3","Cat1","Cat2","Cat3","Cat1","Cat2","Cat3","Cat1","Cat2","Cat3"};
        categoriasArrayAdapter = new CategoriasArrayAdapter(this,categorias);

        LVcategorias.setAdapter(categoriasArrayAdapter);

    }
}
