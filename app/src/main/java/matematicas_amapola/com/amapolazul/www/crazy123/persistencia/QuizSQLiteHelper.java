package matematicas_amapola.com.amapolazul.www.crazy123.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jsmartinez on 17/03/2015.
 */
public class QuizSQLiteHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_TABLA = "preguntas";
    public static final String NOMBRE_COLUMNA_ID = "_id";
    public static final String NOMBRE_COLUMNA_IMAGEN = "imagen";
    public static final String NOMBRE_COLUMNA_RESPUESTA_CORRECTA = "respuesta_correcta";


    private static final String DATABASE_NAME = "matematicas.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + NOMBRE_TABLA + "(" + NOMBRE_COLUMNA_ID
            + " integer primary key autoincrement, " + NOMBRE_COLUMNA_IMAGEN
            + " integer not null," + NOMBRE_COLUMNA_RESPUESTA_CORRECTA
            + " text not null" +
            ");";

    public QuizSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOMBRE_TABLA);
        onCreate(db);
    }
}