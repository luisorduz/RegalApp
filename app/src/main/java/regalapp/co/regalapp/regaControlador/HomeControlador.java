package regalapp.co.regalapp.regaControlador;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.lucasr.twowayview.TwoWayView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import regalapp.co.regalapp.R;
import regalapp.co.regalapp.regaAdaptadores.CategoriasArrayAdapter;
import regalapp.co.regalapp.regaAdaptadores.HorizontalAdapter;
import regalapp.co.regalapp.regaAdaptadores.RecyclerView_Adapter;
import regalapp.co.regalapp.regaBeans.BeanPost;
import regalapp.co.regalapp.regaBeans.Data_Model;
import regalapp.co.regalapp.regaBeans.HomeJsonObject;
import regalapp.co.regalapp.regaBeans.image_loader.ImageLoader;
import regalapp.co.regalapp.regaUtilidades.WebUtil;

/**
 * Created by AndresOs on 29/09/15.
 */
public class HomeControlador extends Activity {

    ProgressDialog progressDialog;
    ListView postList;
    HomeJsonObject homeJsonObjects;
    ArrayList<HomeJsonObject.RegCategoriasBean> regCategoriasBean;
    private StringBuffer topicList;
    PostAdapter postAdapter;


    private com.andexert.library.RippleView btn_publicar_regalo;

    private static RecyclerView recyclerView;
    //String and Integer array for Recycler View Items
    public static final String[] TITLES= {"Hood","Full Sleeve Shirt","Shirt","Jean Jacket","Jacket"};
    public static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);

        btn_publicar_regalo = (com.andexert.library.RippleView) findViewById(R.id.publicaRegalo);
        postList = (ListView)findViewById(R.id.postList);


        initViews();
        populatRecyclerView();

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog=new ProgressDialog(HomeControlador.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Cargando...");
                progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... voids) {

                String url = "http://andresorduz.co/model_regalapp/dao_home/modelo_get_reg_categorias.php";

                try {
                    String respuesta = WebUtil.GETrequest(url);
                    Log.i("respuesta login: ", respuesta);

                    //Type listType = new TypeToken<ArrayList<HomeJsonObject>>(){}.getType();
                    homeJsonObjects = new GsonBuilder().create().fromJson(respuesta, HomeJsonObject.class);
                    regCategoriasBean = homeJsonObjects.getRegCategorias();
                    topicList = new StringBuffer();
                    for(HomeJsonObject.RegCategoriasBean topic: regCategoriasBean){
                        Log.e("topic title: ",topic.getCatNombre()+"");
                        topicList.append("* "+topic.getCatNombre()+"\n");
                    }

                    handleJsonData();

                    return null;

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
            }
        }.execute();




        btn_publicar_regalo.setOnRippleCompleteListener(new com.andexert.library.RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(com.andexert.library.RippleView rippleView) {
                Intent publicar_regalo = new Intent(getApplicationContext(), PublicarRegaloControlador.class);
                startActivity(publicar_regalo);
            }
        });

        postList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("aqui ", "aqui" + i);
                OpenRecyclerViewActivity("staggered");
                //OpenRecyclerViewActivity("horizontal");
            }
        });


    }


    public void handleJsonData(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                postAdapter = new PostAdapter(HomeControlador.this,regCategoriasBean);
                postList.setAdapter(postAdapter);
            }
        });
    }


    public class PostAdapter extends BaseAdapter {

        Context context;
        ArrayList<HomeJsonObject.RegCategoriasBean> postArrayList;
        ImageLoader imageLoader;

        public PostAdapter(Context context, ArrayList<HomeJsonObject.RegCategoriasBean> postArrayList) {
            imageLoader=new ImageLoader(HomeControlador.this);
            this.context = context;
            this.postArrayList = postArrayList;
        }

        public int getCount() {
            return postArrayList.size();
        }

        public Object getItem(int position) {
            return postArrayList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView txtPostTitle,txtPostDate,txtPostDescription;
            ImageView imgPost;
        }

        public View getView(final int position, View convertView,ViewGroup parent) {

            final ViewHolder holder;
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_list, parent, false);
                holder = new ViewHolder();
                holder.txtPostTitle = (TextView) convertView.findViewById(R.id.txtPostTitle);
                holder.txtPostDate = (TextView) convertView.findViewById(R.id.txtPostDate);
                holder.txtPostDescription = (TextView) convertView.findViewById(R.id.txtPostDescription);
                holder.imgPost = (ImageView) convertView.findViewById(R.id.imgPost);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.txtPostTitle.setText(postArrayList.get(position).getCatNombre());
            holder.txtPostDate.setText(postArrayList.get(position).getCatNombre());
            holder.txtPostDescription.setText(postArrayList.get(position).getCatNombre());
            imageLoader.DisplayImage(postArrayList.get(position).getCatUrlImagen(),holder.imgPost);
            return convertView;
        }

    }


    // Initialize the view
    private void initViews() {


        recyclerView = (RecyclerView)
                findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);

        recyclerView
                    .setLayoutManager(new LinearLayoutManager(HomeControlador.this, LinearLayoutManager.HORIZONTAL, false));

    }
    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {
        ArrayList<Data_Model> arrayList = new ArrayList<>();
        for (int i = 0; i < TITLES.length; i++) {
            arrayList.add(new Data_Model(TITLES[i],IMAGES[i]));
        }

        RecyclerView_Adapter adapter = new RecyclerView_Adapter(HomeControlador.this, arrayList,"horizontal");
        recyclerView.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter

    }

    //Open Recycler View activity and pass a string to notify which recyclerview is to setup
    private void OpenRecyclerViewActivity(String navigateFrom){
        Intent in = new Intent(HomeControlador.this, RecyclerView_Activity.class);
        in.putExtra("navigateFrom",navigateFrom);
        startActivity(in);
    }
}
