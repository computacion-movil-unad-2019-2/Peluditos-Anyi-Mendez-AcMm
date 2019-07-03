package Peluditos.com;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class Mascotas_perdidas extends AppCompatActivity {

    public ListView ListResul;
    public String url;
    Button BtnAdd, BtnCargar;
    ListView listaResultado;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_perdidas);

        ListResul = (ListView) findViewById(R.id.lvLista);
        url = "http://peluditos.mypressonline.com/listmascotaperdida.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        BtnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String consulta = "http://peluditos.mypressonline.com/listmascotaperdida.php";
                EnviarRecibirDatos(consulta);

            }
        });


    }
    public void EnviarRecibirDatos(String URL){

        Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();

        for(int i=0;i<ja.length();i+=4){

            try {

                lista.add(ja.getString(i)+" "+ja.getString(i+1)+" "+ja.getString(i+2)+" "+ja.getString(i+3));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, lista);
        listaResultado.setAdapter(adaptador);




    }
    public void Addmascotaperdida(View view){
        Intent Addmascotaperdida=new Intent(this, Add_mascotaperdidaActivity.class);
        startActivity(Addmascotaperdida);
    }
}
