package com.example.basadatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MostrarProducto extends AppCompatActivity {

    AdminSQLiteOpenHelper db;

    ListView userlist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_producto);

        db = new AdminSQLiteOpenHelper(this);

        listItem = new ArrayList<>();

        userlist = findViewById(R.id.user_list);

        mostrarDatos();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userlist.getItemAtPosition(position).toString();
                Toast.makeText(MostrarProducto.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addDialog(View view){

        final AlertDialog.Builder dialog = new AlertDialog.Builder(MostrarProducto.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_singin, null);

        final EditText nombreDialog = (EditText) mView.findViewById(R.id.nombre);
        final EditText precioDialog = (EditText) mView.findViewById(R.id.precio);
        Button addBtn = (Button) mView.findViewById(R.id.anyadirArticulo);

        dialog.setView(mView);

        AlertDialog addDialog = dialog.show();
        addDialog.setCanceledOnTouchOutside(false);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreDialog.getText().toString();
                String precio = precioDialog.getText().toString();

                if(!nombre.equals("") && !precio.equals("") && db.insertData(nombre,precio)){
                    Toast.makeText(MostrarProducto.this, "Datos Añadidos", Toast.LENGTH_SHORT).show();
                    nombreDialog.setText("");
                    precioDialog.setText("");
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MostrarProducto.this, "No se han podido añadir los datos", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    public void mostrarDatos(){
        cursor = db.mostrarDatos();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No se han podido mostrar datos", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                listItem.add("ID: " + cursor.getString(0)+ " Nombre: " + cursor.getString(1) + " Precio: " + cursor.getString(2));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userlist.setAdapter(adapter);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> userslist = new ArrayList<>();

                for (String user : listItem){
                    if(user.toLowerCase().contains(newText.toLowerCase())){
                        userslist.add(user);
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MostrarProducto.this,
                        android.R.layout.simple_list_item_1, userslist);

                userlist.setAdapter(adapter);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
