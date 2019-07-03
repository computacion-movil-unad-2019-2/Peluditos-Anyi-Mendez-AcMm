package Peluditos.com;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchDenunciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_denuncia);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.denuncia, new Buscardenuncia()).commit();
    }
}
