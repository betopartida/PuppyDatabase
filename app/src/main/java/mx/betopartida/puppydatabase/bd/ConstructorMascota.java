package mx.betopartida.puppydatabase.bd;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

import mx.betopartida.puppydatabase.pojo.Mascota;

/**
 * Created by beto on 05/08/2016.
 */
public class ConstructorMascota {

    private static final int LIKE = 1 ;
    Context contexto;

    public ConstructorMascota(Context context) {
        this.contexto=context;
    }

    public ArrayList<Mascota> cargarDatosMascotas() {
        BaseDatos bd=new BaseDatos(contexto);
        return bd.obtenerListaMascotas();
    }

    public ArrayList<Mascota> cargarDatosMascotasBD() {
        BaseDatos bd=new BaseDatos(contexto);
        return bd.obtenerMascotasBD();
    }

    public void insertar(Mascota mascota) {
        BaseDatos bd=new BaseDatos(contexto);
        ContentValues cv=new ContentValues();
        cv.put(ConstantesBD.MASCOTA_IDMASCOTA,mascota.getIdmascota());
        cv.put(ConstantesBD.MASCOTA_NOMBRE,mascota.getNombre());
        cv.put(ConstantesBD.MASCOTA_FOTO,mascota.getFoto());
        cv.put(ConstantesBD.MASCOTA_LIKES,mascota.getLikes());
        bd.insertarMascota(cv);
    }

    public void darLike(Mascota mascota) {
        BaseDatos bd=new BaseDatos(contexto);
        ContentValues cv=new ContentValues();
        long fecha=new Date().getTime();

        cv.put(ConstantesBD.MASCOTA_IDMASCOTA,mascota.getIdmascota());
        cv.put(ConstantesBD.MASCOTA_LIKE_LIKE,LIKE);
        cv.put(ConstantesBD.MASCOTA_LIKE_FECHA,fecha);
        bd.insertarLikeMascota(cv);
    }

    public int obtenerLikes(Mascota mascota) {
        BaseDatos bd=new BaseDatos(contexto);
        return bd.obtenerLikesMascota(mascota);
    }
}
