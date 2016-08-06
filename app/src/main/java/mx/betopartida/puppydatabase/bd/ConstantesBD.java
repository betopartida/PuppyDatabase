package mx.betopartida.puppydatabase.bd;

import java.util.Date;

/**
 * Created by beto on 05/08/2016.
 */
public class ConstantesBD {
    public static final String DATABASE_NAME="mascotas";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_MASCOTA="mascota";
    public static final String TABLE_MASCOTA_LIKE="mascota_like";

    public static final String MASCOTA_IDMASCOTA="idmascota";
    public static final String MASCOTA_NOMBRE="nombre";
    public static final String MASCOTA_FOTO="foto";
    public static final String MASCOTA_LIKES="likes";
    public static final String MASCOTA_ULTIMO_LIKE="ultimoLike";

    public static final String MASCOTA_LIKE_LIKE="like";
    public static final String MASCOTA_LIKE_FECHA ="fecha_like";

}
