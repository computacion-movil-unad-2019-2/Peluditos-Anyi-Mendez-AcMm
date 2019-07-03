package Peluditos.com;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class menu extends AppCompatActivity {
       public static final String nombres="names";
    EditText txtBienvenido;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

            }



    public void mascotaperdida(View view){
        Intent mascotaperdida=new Intent(this, Add_mascotaperdidaActivity.class);
        startActivity(mascotaperdida);

    }
    public void Searchmascotaperdida(View view){
        Intent buscarmp=new Intent(this,  SearchmascotaPActivity.class);
        startActivity(buscarmp);

    }

    public void denuncias(View view){
        Intent denuncias=new Intent(this,  DenunciasActivity.class);
        startActivity(denuncias);

    }

    public void BuscarDenuncia(View view){
        Intent BuscarDenuncia=new Intent(this, SearchDenunciaActivity.class);
        startActivity(BuscarDenuncia);

    }

    public void BuscarUsuario(View view){
        Intent BuscarUsuario=new Intent(this, SearchUsuarioActivity.class);
        startActivity(BuscarUsuario);

    }

    public void BuscarSeguimiento(View view){
        Intent Buscarseguimiento=new Intent(this, SearchSeguimientos.class);
        startActivity(Buscarseguimiento);

    }





}


