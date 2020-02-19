package com.example.basadatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Anyadir_producto extends AppCompatActivity {

    private AdminSQLiteOpenHelper db;
    private EditText  et_nombre, et_precio;
    private Button btn_anyadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_producto);

        db = new AdminSQLiteOpenHelper(this);

        et_nombre = (EditText) findViewById(R.id.txt_descripcion);
        et_precio = (EditText) findViewById(R.id.txt_precio);

        btn_anyadir = (Button) findViewById(R.id.add_datos);
        btn_anyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = et_nombre.getText().toString();
                String precio = et_precio.getText().toString();

                if(!nombre.equals("") && !precio.equals("") && db.insertData(nombre,precio)){
                    Toast.makeText(Anyadir_producto.this, "Datos Añadidos", Toast.LENGTH_SHORT).show();
                    et_nombre.setText("");
                    et_precio.setText("");
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Anyadir_producto.this, "No se han podido añadir los datos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
