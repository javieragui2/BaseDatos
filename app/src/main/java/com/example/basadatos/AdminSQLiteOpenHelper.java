package com.example.basadatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    private static final String TAG = "" ;
    public static String DATABASE_NAME = "Articulos.BD";
    public static String DB_TABLE = "Articulos_tabla";

    private static Cursor c;

    //columnas
    private static final String ID = "ID";
    private static final String NOMBRE = "NOMBRE";
    private static final String PRECIO = "PRECIO";

    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NOMBRE + " TEXT, " + PRECIO + " TEXT " + ")";

    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {
        BD.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(BD);
    }

    //Metodo para ver los datos en un listView
    public Cursor mostrarDatos(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ DB_TABLE;
        c = db.rawQuery(query, null);

        return c;
    }
    //Metodo para insertar datos
    public boolean insertData( String nombre, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOMBRE, nombre);
        contentValues.put(PRECIO, precio);

        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }

    //Metodo para eliminar datos
    public void eliminarDato(String id, String nombre, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ DB_TABLE + " WHERE  ID ="+ id );
        db.close();
    }

    //Metodo que busca datos a partir del nombre de un editText
    public Cursor buscarDatosParaEliActua(String id){
        Log.i(TAG, id);
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT NOMBRE,PRECIO FROM "+ DB_TABLE + " WHERE ID=" + id;
        c = db.rawQuery(query, null);

        return c;
    }

    //Metodo para actualizar datos
    public boolean actualizarDatos(String id, String nombre, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID",id);
        contentValues.put("NOMBRE",nombre);
        contentValues.put("PRECIO",precio);

        db.update(DB_TABLE, contentValues, "ID=?",new String[]{String.valueOf(id)});
        return true;
    }
}
