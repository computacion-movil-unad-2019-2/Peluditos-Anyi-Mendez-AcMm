package Peluditos.com;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
//btndenuncias

public class addDenunciaMaltrato extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;
    EditText txtDescripcion, txtUbicacion;
    Button denunciarBtn;
    Button listado;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_add_denuncia_maltrato, container, false);
        txtUbicacion = (EditText) vista.findViewById(R.id.lugar);
        txtDescripcion = (EditText) vista.findViewById(R.id.descripciondenuncia);
        denunciarBtn=(Button) vista.findViewById(R.id.denunciarBtn);
        rq = Volley.newRequestQueue(getContext());
        denunciarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddDenuncia();
            }
        });



        return vista;



    }

    void AddDenuncia(){
        //192.168.1.66(172.29.243.3
        String url = "http://peluditos.mypressonline.com/adddenuncia.php?"
                +"ubicacion="+txtUbicacion.getText().toString()
                +"&descripcion=" + txtDescripcion.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }



    void limpiarCajas() {
        txtUbicacion.setText("");
        txtDescripcion.setText("");

    }
    //listado de animales perdidos


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Se ha presentado un error:  " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se ha registrado la Denuncia:  " , Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }

}
