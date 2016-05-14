package regalapp.co.regalapp.regaAdaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaBeans.Data_Model;
import regalapp.co.regalapp.regaBeans.RecyclerViewHolder;


/**
 * Created by SONU on 25/09/15.
 */
public class RecyclerView_Adapter extends
        RecyclerView.Adapter<RecyclerViewHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private ArrayList<Data_Model> arrayList;
    private Context context;
    public String tipoAdaptador;

    public RecyclerView_Adapter(Context context,ArrayList<Data_Model> arrayList, String tipoAdaptador) {
        this.context = context;
        this.arrayList = arrayList;
        this.tipoAdaptador = tipoAdaptador;

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final Data_Model model = arrayList.get(position);

        RecyclerViewHolder mainHolder = (RecyclerViewHolder) holder;// holder

        Bitmap image = BitmapFactory.decodeResource(context.getResources(),
                model.getImage());// This will convert drawbale image into
        // bitmap

        // setting title
        mainHolder.title.setText(model.getTitle());

        mainHolder.imageview.setImageBitmap(image);

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // This method will inflate the custom layout and return as viewholder
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        if (tipoAdaptador.equals("horizontal")){
            ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_row_horizontal, viewGroup, false);
            RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
            Log.i("adaptadorH",""+tipoAdaptador);
            return listHolder;

        }else{
            ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.item_row, viewGroup, false);
            RecyclerViewHolder listHolder = new RecyclerViewHolder(mainGroup);
            Log.i("adaptadorV",""+tipoAdaptador);
            return listHolder;
        }


    }

}
