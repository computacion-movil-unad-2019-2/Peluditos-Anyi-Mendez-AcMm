package Peluditos.com;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DenunciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncias);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.denuncias, new addDenunciaMaltrato()).commit();


    }
}
