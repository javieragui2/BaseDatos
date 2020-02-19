package com.example.basadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Borrar_Actualizar_Activity extends AppCompatActivity {

    private static final String TAG = "";
    private static AdminSQLiteOpenHelper db;
    private static EditText  id, nombre, precio;
    private static Button buscar, eliminar;
    private static Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar__actualizar_);

        db = new AdminSQLiteOpenHelper(this);

        id = (EditText) findViewById(R.id.txt_idArticulo);
        nombre = (EditText) findViewById(R.id.txt_nombreArticulo);
        precio = (EditText) findViewById(R.id.txt_precioArticulo);
        buscar = (Button) findViewById(R.id.btn_buscarEli);
        eliminar = (Button) findViewById(R.id.btn_Eliminar);

    }

    //Evento que llama al metodo de la bbdd para buscar y mostrar datos.
    public void buscar(View v) {
        String idArt = id.getText().toString();

        if(!idArt.isEmpty()) {
            c = db.buscarDatosParaEliActua(idArt);

            if (c.getCount() == 0) {
                Log.d(TAG, idArt);
                Toast.makeText(this, "No se han podido mostrar datos", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, idArt);
                while (c.moveToNext()) {
                    nombre.setText(c.getString(0));
                    precio.setText(c.getString(1));
                }
            }
        }else{
            Toast.makeText(this, "Debes introducir un numero en el ID del articulo.", Toast.LENGTH_SHORT).show();
        }
    }

    //Evento que llama al metodo de la bbdd para eliminar datos.
    public void eliminarFila(View v){

        String idArt = id.getText().toString();
        String nombreArt = nombre.getText().toString();
        String precioArt = precio.getText().toString();

        if(!idArt.isEmpty() && !nombreArt.isEmpty() && !precioArt.isEmpty()){
            db.eliminarDato(idArt,nombreArt,precioArt);
            Toast.makeText(this, "Articulo eliminado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }else{
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    //Evento que llama al metodo de la bbdd para elminar datos
    public void actualizarFila(View v){
        String idArt = id.getText().toString();
        String nombreArt = nombre.getText().toString();
        String precioArt = precio.getText().toString();

        if(!idArt.isEmpty() && !nombreArt.isEmpty() && !precioArt.isEmpty()){
            if(db.actualizarDatos(idArt,nombreArt,precioArt)) {
                Toast.makeText(this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }else{
                Toast.makeText(this, "No se han podido actualizar los datos", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
        }

    }
}

