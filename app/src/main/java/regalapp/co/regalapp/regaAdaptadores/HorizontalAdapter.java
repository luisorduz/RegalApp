package regalapp.co.regalapp.regaAdaptadores;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import regalapp.co.regalapp.R;

/**
 * Created by andresorduz on 8/10/15.
 */
public class HorizontalAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public HorizontalAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_horizontal, values);
        this.context = context;
        this.values = values;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_horizontal, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.TVhorizontal);
        textView.setText(values[position]);

        ImageView imageCategoriaH = (ImageView) rowView.findViewById(R.id.IMcategoriaH);
        // Change the icon for Windows and iPhone
        String s = values[position];

        if (s.startsWith("Hogar")) {
            imageCategoriaH.setImageResource(R.drawable.ic_action_home);
        }

        if (s.startsWith("Electrodomésticos")) {
            imageCategoriaH.setImageResource(R.drawable.ic_electrodomesticos);
        }
        if (s.startsWith("Deportes")) {
            imageCategoriaH.setImageResource(R.drawable.ic_deportes);
        }
        if (s.startsWith("Mascotas")) {
            imageCategoriaH.setImageResource(R.drawable.ic_mascotas);
        }
        if (s.startsWith("Tecnología")) {
            imageCategoriaH.setImageResource(R.drawable.ic_tecnologia);
        }
        if (s.startsWith("Servicios")) {
            imageCategoriaH.setImageResource(R.drawable.ic_servicios);
        }
        if (s.startsWith("Uso hombre")) {
            imageCategoriaH.setImageResource(R.drawable.ic_uso_hombre);
        }
        if (s.startsWith("Uso mujer")) {
            imageCategoriaH.setImageResource(R.drawable.ic_uso_mujer);
        }
        return rowView;
    }
}

