package regalapp.co.regalapp.regaBeans;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andresorduz on 12/05/16.
 */
public class HomeJsonObject {

    /**
     * Request : correcto
     * Reg_categorias : [{"cat_id":12,"cat_nombre":"Animales - Mascotas","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_animalesC.png"},{"cat_id":4,"cat_nombre":"Computadores - Portatiles","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_computadoresC.png"},{"cat_id":8,"cat_nombre":"Deportes - Bicicletas","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_deportesC.png"},{"cat_id":7,"cat_nombre":"Electrodomesticos","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_electrodomesticosC.png"},{"cat_id":13,"cat_nombre":"Empleos","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_empleosC.png"},{"cat_id":11,"cat_nombre":"Hobbies - Musica - Arte - Libros","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_hobbiesC.png"},{"cat_id":15,"cat_nombre":"Maquinaria - Herramientas","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_maquinariaC.png"},{"cat_id":10,"cat_nombre":"Moda - Belleza","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_modaC.png"},{"cat_id":6,"cat_nombre":"Muebles - Hogar - Jardin","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_muebles_hogarC.png"},{"cat_id":9,"cat_nombre":"Bebes - Niños - Juguetes","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_ninosC.png"},{"cat_id":14,"cat_nombre":"Propiedades - Inmuebles","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_propiedadesC.png"},{"cat_id":16,"cat_nombre":"Servicios","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_serviciosC.png"},{"cat_id":2,"cat_nombre":"Telefonos -Tablets","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_telefonos_tabletsC.png"},{"cat_id":3,"cat_nombre":"TV - Audio - Video - Camaras","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_tv_audioC.png"},{"cat_id":1,"cat_nombre":"Vehiculos - Motos","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_vehiculos_motosC.png"},{"cat_id":5,"cat_nombre":"Video juegos - Consolas","cat_url_imagen":"http://andresorduz.co/model_regalapp/images/cat_images/ic_video_juegosC.png"}]
     */

    @SerializedName("Request")
    private String Request;

    /**
     * cat_id : 12
     * cat_nombre : Animales - Mascotas
     * cat_url_imagen : http://andresorduz.co/model_regalapp/images/cat_images/ic_animalesC.png
     */

    @SerializedName("Reg_categorias")
    private ArrayList<RegCategoriasBean> RegCategorias;


    public HomeJsonObject(String request, ArrayList<RegCategoriasBean> regCategorias) {
        Request = request;
        this.RegCategorias = regCategorias;

    }


    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public ArrayList<RegCategoriasBean> getRegCategorias() {
        return RegCategorias;
    }

    public void setRegCategorias(ArrayList<RegCategoriasBean> regCategorias) {
        RegCategorias = regCategorias;
    }

    public static class RegCategoriasBean{
        @SerializedName("cat_id")
        private int catId;
        @SerializedName("cat_nombre")
        private String catNombre;
        @SerializedName("cat_url_imagen")
        private String catUrlImagen;

        public int getCatId() {
            return catId;
        }

        public void setCatId(int catId) {
            this.catId = catId;
        }

        public String getCatNombre() {
            return catNombre;
        }

        public void setCatNombre(String catNombre) {
            this.catNombre = catNombre;
        }

        public String getCatUrlImagen() {
            return catUrlImagen;
        }

        public void setCatUrlImagen(String catUrlImagen) {
            this.catUrlImagen = catUrlImagen;
        }
    }

}
