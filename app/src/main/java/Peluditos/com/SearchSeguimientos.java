package Peluditos.com;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchSeguimientos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_seguimientos);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.seguimiento, new BuscarSeguimientos()).commit();

    }
}
