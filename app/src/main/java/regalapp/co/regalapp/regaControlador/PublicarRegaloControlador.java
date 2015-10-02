package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.andexert.library.RippleView;

import java.io.File;
import java.util.Date;

import regalapp.co.regalapp.R;

/**
 * Created by AndresOs on 2/10/15.
 */
public class PublicarRegaloControlador  extends Activity{


    private RippleView rippleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_regalo);

        rippleView = (RippleView) findViewById(R.id.RPtomar_Foto);


        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {

            }

        });
    }


}
