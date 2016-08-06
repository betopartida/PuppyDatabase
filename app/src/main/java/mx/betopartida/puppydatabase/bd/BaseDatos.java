package mx.betopartida.puppydatabase.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.R;
import mx.betopartida.puppydatabase.pojo.Mascota;

/**
 * Created by beto on 05/08/2016.
 */
public class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBD.DATABASE_NAME, null, ConstantesBD.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascota="CREATE TABLE " + ConstantesBD.TABLE_MASCOTA + " (" +
                ConstantesBD.MASCOTA_IDMASCOTA      + " INTEGER PRIMARY KEY, " +
                ConstantesBD.MASCOTA_NOMBRE         + " TEXT, " +
                ConstantesBD.MASCOTA_FOTO           + " INTEGER,"+
                ConstantesBD.MASCOTA_LIKES          + " INTEGER, " +
                ConstantesBD.MASCOTA_ULTIMO_LIKE    + " DATETIME DEFAULT CURRENT_TIMESTAMP" +
                ")";

        String queryCrearTablaMascotaLike="CREATE TABLE " + ConstantesBD.TABLE_MASCOTA_LIKE + " (" +
                ConstantesBD.MASCOTA_IDMASCOTA      + " INTEGER, " +
                ConstantesBD.MASCOTA_LIKE_LIKE      + " INTEGER, " +
                ConstantesBD.MASCOTA_LIKE_FECHA     + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (" + ConstantesBD.MASCOTA_IDMASCOTA + ") REFERENCES " +
                ConstantesBD.TABLE_MASCOTA + "(" + ConstantesBD.MASCOTA_IDMASCOTA + ")"+
                ")";

        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaMascotaLike);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_MASCOTA);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBD.TABLE_MASCOTA_LIKE);
    }

    public ArrayList<Mascota> obtenerListaMascotas() {
        ArrayList<Mascota> mascotas=new ArrayList<Mascota>();
        mascotas.add(new Mascota(1,"Juno",R.drawable.pet1,0));
        mascotas.add(new Mascota(2,"Frida",R.drawable.pet2,0));
        mascotas.add(new Mascota(3,"Candy",R.drawable.pet3,0));
        mascotas.add(new Mascota(4,"Nina",R.drawable.pet4,0));
        mascotas.add(new Mascota(5,"Tito",R.drawable.pet5,0));
        mascotas.add(new Mascota(6,"Virus",R.drawable.pet6,0));
        mascotas.add(new Mascota(7,"Daemon",R.drawable.pet7,0));
        //Se cargan los likes de la tabla
        for(int i=0; i<mascotas.size(); i++) {
            mascotas.get(i).setLikes(obtenerLikesMascota(mascotas.get(i)));
        }
        return mascotas;
    }

    public ArrayList<Mascota> obtenerMascotasBD(){
        ArrayList<Mascota> mascotas=new ArrayList<Mascota>();
        String query="SELECT * FROM "+ConstantesBD.TABLE_MASCOTA+" ORDER BY ultimoLike DESC"; //order by fecha_like desc
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while(registros.moveToNext()) {
            Mascota mascotaActual=new Mascota();
            mascotaActual.setIdmascota(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            query="SELECT COUNT("+ConstantesBD.MASCOTA_LIKE_LIKE+")"+
                    " FROM "+ ConstantesBD.TABLE_MASCOTA_LIKE +
                    " WHERE " + ConstantesBD.MASCOTA_IDMASCOTA + "=" + mascotaActual.getIdmascota();
            Cursor cLikes=db.rawQuery(query,null);
            if(cLikes.moveToNext()) mascotaActual.setLikes(cLikes.getInt(0));
            else mascotaActual.setLikes(0);

            mascotas.add(mascotaActual);
        }
        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues) {

        SQLiteDatabase db=this.getWritableDatabase();
        //borro la mascota para insertarla de nuevo y se actualice la fecha
        String query="DELETE FROM "+ConstantesBD.TABLE_MASCOTA+
                " WHERE "+ConstantesBD.MASCOTA_IDMASCOTA+"="+contentValues.get(ConstantesBD.MASCOTA_IDMASCOTA);
        db.execSQL(query);

        if(hayCincoRegistros(db)) borraMasAntiguo(db);

        db.insert(ConstantesBD.TABLE_MASCOTA,null,contentValues);

        db.close();

    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(ConstantesBD.TABLE_MASCOTA_LIKE,null,contentValues);
        db.close();
    }

    public void borraTablaMascotas() {
        SQLiteDatabase db=this.getWritableDatabase();

        String query="DELETE FROM "+ConstantesBD.TABLE_MASCOTA;
        db.execSQL(query);
        query="DELETE FROM "+ConstantesBD.TABLE_MASCOTA_LIKE;
        db.execSQL(query);

        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota) {
        int likes=0;
        String query="SELECT COUNT("+ConstantesBD.MASCOTA_LIKE_LIKE+")"+
                " FROM "+ ConstantesBD.TABLE_MASCOTA_LIKE+
                " WHERE " + ConstantesBD.MASCOTA_IDMASCOTA + "=" + mascota.getIdmascota();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToNext()) likes=cursor.getInt(0);
        db.close();
        return likes;
    }

    public void borraMasAntiguo(SQLiteDatabase db) {
        //Borra la mascota con la fecha de like mas antigua
        String query="DELETE FROM "+ConstantesBD.TABLE_MASCOTA+
                " WHERE "+ConstantesBD.MASCOTA_ULTIMO_LIKE+"="+
                "(SELECT MIN("+ConstantesBD.MASCOTA_ULTIMO_LIKE+ ") "+
                "FROM "+ConstantesBD.TABLE_MASCOTA+")";
        db.execSQL(query);
    }

    public boolean hayCincoRegistros(SQLiteDatabase db) {
        int cuantos=0;
        String query="SELECT COUNT(*) FROM "+ConstantesBD.TABLE_MASCOTA;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToNext()) cuantos=cursor.getInt(0);
        return cuantos>=5;
    }



}
