package Peluditos.com;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Add_mascotaperdidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mascotaperdida);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mascotaperdida, new Addmascotaperdida()).commit();
    }
}
