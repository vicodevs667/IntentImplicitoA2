package com.example.intentimplicitoa2;


import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    //Atributos Representan Vistas
    private EditText etTelefono;
    private ImageButton btnLlamar, btnCamara;

    //Atributos primitivos de clase
    private String numeroTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarVistas();
        btnLlamar.setOnClickListener(view -> {
            obtenerInformacion();
            configurarIntentImplicito();
        });
    }

    private void configurarIntentImplicito() {
        if (!numeroTelefono.isEmpty()) {
            //Tienen que evaluar si la version de Android del celular
            //es anterior o superior a la version 23 de Android, donde
            //hubieron cambios en la forma de codificar el tema de las llamadas
            //if (miVersion=ZZZ >= 23)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Codigo nuevo para llamadas
            } else {
                //Codigo para dispositivos antiguos
                activarLlamadaVersionesAntiguas();
            }
        }
    }

    private void activarLlamadaVersionesAntiguas() {
        //Configurar un Intent Implícito
        //Al intent Implicito le debes pasar algunos parametros como:
        // La accion que quieres o deseas que pase
        //Un segundo parametro que le puedes pasar a un Intent Implicito
        //es los datos que quieres enviar a otra aplicacion o servicio
        //para ello usas algo que puedes asemejar con una URL que conoces de web
        //en Android se usa algo llamado URI
        //la cadena que van a parsear es como un formato llave valor
        Intent intentLlamada = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+numeroTelefono));
        if(revisarPermisos(Manifest.permission.CALL_PHONE)) {
            startActivity(intentLlamada);
        }
    }

    private void obtenerInformacion() {
        numeroTelefono = etTelefono.getText().toString();
    }

    private void inicializarVistas() {
        etTelefono = findViewById(R.id.etTelefono);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnCamara = findViewById(R.id.btnCamara);
    }

    private boolean revisarPermisos(String permiso) {
        int valorPermiso = this.checkCallingOrSelfPermission(permiso);
        return valorPermiso == PackageManager.PERMISSION_GRANTED;
    }
}









































