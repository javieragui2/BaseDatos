package com.example.basadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button anyadir, mostrar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Mostrar(View v){
        Intent intent = new Intent(v.getContext(), MostrarProducto.class);
        startActivityForResult(intent, 0);
    }
    public void Anyadir(View v){
        Intent intent = new Intent(v.getContext(), Anyadir_producto.class);
        startActivityForResult(intent, 0);
    }
    public void Editar(View v){
        Intent intent = new Intent(v.getContext(), Borrar_Actualizar_Activity.class);
        startActivityForResult(intent, 0);
    }
}
