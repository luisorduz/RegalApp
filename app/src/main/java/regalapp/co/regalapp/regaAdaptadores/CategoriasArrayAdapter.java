package regalapp.co.regalapp.regaAdaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaUtilidades.RippleView;

/**
 * Created by AndresOs on 30/09/15.
 */
public class CategoriasArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public CategoriasArrayAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_categorias, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_categorias, parent, false);
        RippleView textView = (RippleView) rowView.findViewById(R.id.TVcategoria);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.IMcategoria);
        imageView.setBackground(null);
        textView.setText(values[position]);
        // Change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("Cat1")) {
            imageView.setImageResource(R.drawable.ic_social_person);
        } else {
            imageView.setImageResource(R.drawable.ic_action_redeem);
        }

        return rowView;
    }
}
