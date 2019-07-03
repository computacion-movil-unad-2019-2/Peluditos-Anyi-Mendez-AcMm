package Peluditos.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void Sesion(View view){
        Intent sesion=new Intent(this, InicioSesion.class);
        startActivity(sesion);
    }


    public void Registro(View view){
        Intent registro=new Intent(this, RegistroActivity.class);
        startActivity(registro);
    }

    public void mascotaperdida(View view){
        Intent mascotaperdida=new Intent(this,  SearchDenunciaActivity.class);
        startActivity(mascotaperdida);

    }

    public void Busmascota(View view){
        Intent Busmascota=new Intent(this, DenunciasActivity.class);
        startActivity(Busmascota);

    }

}

