package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.andexert.library.RippleView;

import java.io.File;
import java.util.Date;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaAdaptadores.CategoriasArrayAdapter;

/**
 * Created by AndresOs on 2/10/15.
 */
public class PublicarRegaloControlador  extends Activity{


    private RippleView rpTomarFoto;

    private Spinner SPcategorias;
    private SeekBar sbEstado,sbPostulados;
    private TextView tvEstado,tvPostulados;
    CategoriasArrayAdapter categoriasArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_regalo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        rpTomarFoto = (RippleView) findViewById(R.id.RPtomar_Foto);


        SPcategorias = (Spinner) findViewById(R.id.SPcategorias);
        sbEstado = (SeekBar) findViewById(R.id.SBestado);
        sbPostulados = (SeekBar) findViewById(R.id.SBpostulados);
        tvEstado = (TextView) findViewById(R.id.TVestado);
        tvPostulados = (TextView) findViewById(R.id.TVpostulados);

        //String.valueOf(R.drawable.ic_action_redeem
        String[] categorias = new  String[]{"Hogar","Electrodomésticos","Deportes","Mascotas",
                "Tecnología","Servicios","Uso hombre","Uso mujer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categorias);
        SPcategorias.setAdapter(adapter);


        rpTomarFoto.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {

                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File capturas = new File(Environment.getExternalStorageDirectory(),"capturas");

                if (!capturas.exists()){
                    capturas.mkdirs();
                }

                File foto = new File(capturas,"foto.jpg");
                Uri uri_foto = Uri.fromFile(foto);
                camara.putExtra(MediaStore.EXTRA_OUTPUT, uri_foto);
                startActivityForResult(camara,1);
            }

        });

        sbEstado.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvEstado.append("Valor: " + progress + "\n");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sbPostulados.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPostulados.append("Valor: " + progress + "\n");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/capturas/foto.jpg");
            ImageView IV = (ImageView) findViewById(R.id.IVfoto);
            IV.setImageBitmap(bitmap);
        }
    }




}
