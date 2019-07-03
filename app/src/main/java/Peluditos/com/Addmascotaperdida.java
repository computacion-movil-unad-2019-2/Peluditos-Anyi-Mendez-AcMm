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

import static android.app.PendingIntent.getActivity;


public class Addmascotaperdida  extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    Button listado;
    RequestQueue rq;
    JsonRequest jrq;
    EditText txtRaza, txtUbicacion, txtNmascota,txtCaracteristicas;
    Button BtnAddperdida;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_addmascotaperdida , container, false);
       txtUbicacion = (EditText) vista.findViewById(R.id.lugar);
        txtRaza = (EditText) vista.findViewById(R.id.raza);
        txtNmascota = (EditText) vista.findViewById(R.id.nombremascota);
        txtCaracteristicas = (EditText) vista.findViewById(R.id.txtcaracteristicas);
        BtnAddperdida=(Button) vista.findViewById(R.id.Btnaddperdida);
        rq = Volley.newRequestQueue(getContext());
        BtnAddperdida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMascotaPerdida();
            }
        });
        return vista;


    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "Se ha presentado un error:  " +error.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se ha registrado la perdida de:  " + txtNmascota.getText().toString(), Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }


    void AddMascotaPerdida(){
        //192.168.1.66(172.29.243.3
        String url = "http://peluditos.mypressonline.com/addmascotaperdida.php?"
                +"ubicacion="+txtUbicacion.getText().toString()
                +"&nmascota="+ txtNmascota.getText().toString()
                +"&raza=" + txtRaza.getText().toString()
                +"&caracteristicas=" + txtCaracteristicas.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);

    }



    void limpiarCajas() {
        txtUbicacion.setText("");
        txtNmascota.setText("");
        txtRaza.setText("");
        txtCaracteristicas.setText("");
    }
    //listado de animales perdidos



}