package Peluditos.com;

import android.annotation.SuppressLint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class SearchmascotaPActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchmascota_p);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.buscarmascota, new Buscarmascotasperdidas()).commit();

    }
}
