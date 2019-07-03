package Peluditos.com;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.escenarioregistro, new RegistroFragment()).commit();

    }
    public void Sesion(View view){

        // Intent sesion=new Intent(this,  InicioSesion.class);
        Intent sesion=new Intent(this, InicioSesion.class);
        startActivity(sesion);
    }

}
