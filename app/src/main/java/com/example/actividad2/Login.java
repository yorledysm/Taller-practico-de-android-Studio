package com.example.actividad2;

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

public class Login extends AppCompatActivity {
    private EditText usuario, contrasena;
    private Button inicioses, registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario= findViewById(R.id.textUsuario);
        contrasena=findViewById(R.id.textPass);

        inicioses=findViewById(R.id.btnIniciar);
        registrar=findViewById(R.id.btnRegistrar);
    }
    public void Validarbnt(View view) {
        String user = usuario.getText().toString();
        String pass = contrasena.getText().toString();


        if (user.isEmpty()) {
            usuario.setError("Usario vacio ");
            usuario.requestFocus();
        } else if (pass.isEmpty()) {
            contrasena.setError("Contraseña vacia ");
            contrasena.requestFocus();
            // Toast.makeText(getApplicationContext(), "Usuario o contraseña  inavalido ", Toast.LENGTH_SHORT).show();

        } else if (user.equals("iud222") && pass.equals("nnnn")) {
            // Toast.makeText(getApplicationContext(), " Bienvenido a MaxiElados", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);

        } else {
            Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }


    }
    public void Registrar( View view) {


        String user = usuario.getText().toString();
        String pass = contrasena.getText().toString();

        DbSQLite admin = new DbSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        if (user.isEmpty()) {
            usuario.setError("Usario vacio ");
            usuario.requestFocus();
        } else if (pass.isEmpty()) {
            contrasena.setError("Contraseña vacia ");
            contrasena.requestFocus();

        } else {
            ContentValues datos = new ContentValues();
            datos.put("User", user);
            datos.put("Contrasena", pass);
            db.insert(DbSQLite.TABLE_NAME, null,datos);
        }
    }


    public  void  Consultar (View view){
        String user = usuario.getText().toString();
        String columns [ ]= new String[]{"user", "contrasena"};
        //  String pass = contrasena.getText().toString();

        DbSQLite admin = new DbSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT *FROM usuario WHERE user='" +user+"'" , null );
        Cursor cursore =db.query("usuario", columns, null, null, null, null, null );
        if(cursor!=null){
            if(cursor.moveToFirst()){
                contrasena.setText(cursor.getString(2));
            }
        }

    }

    public  void Eliminar (View view){
        String user = usuario.getText().toString();

        DbSQLite admin = new DbSQLite(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        int res= db.delete("usuario", "user='" +user+"'" , null  );
        if (res!=0){
            Toast.makeText(this, "Usuario elminado", Toast.LENGTH_SHORT).show();
        }
    }

    public void  Actualizar(View view){

    }



}