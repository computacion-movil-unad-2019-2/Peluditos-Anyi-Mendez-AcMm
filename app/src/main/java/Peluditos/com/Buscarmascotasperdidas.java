package Peluditos.com;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Buscarmascotasperdidas extends Fragment implements Response.ErrorListener {
    EditText txtRaza, txtUbicacion, txtNmascota, txtCaracteristicas, txtID;
    Button BtnSearch, BtnActualizar, BtnDelete;
    RequestQueue rq;
    JsonRequest jrq;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_buscarmascotasperdidas, container, false);

        txtID = (EditText) vista.findViewById(R.id.idmascota);
        txtUbicacion = (EditText) vista.findViewById(R.id.lugar);
        txtRaza = (EditText) vista.findViewById(R.id.raza);
        txtNmascota = (EditText) vista.findViewById(R.id.nombremascota);
        txtCaracteristicas = (EditText) vista.findViewById(R.id.txtcaracteristicas);
        BtnSearch = (Button) vista.findViewById(R.id.BtnSearch);
        BtnActualizar = (Button) vista.findViewById(R.id.BtnActualizar);
        BtnDelete = (Button) vista.findViewById(R.id.BtnDelete);


        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchMascotaperdida();
            }
        });
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deletemascotaperdida();
            }
        });

        BtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarMascotaP();
            }
        });
        return vista;

    }

    private void ActualizarMascotaP() {


        String url = "http://peluditos.mypressonline.com/updatemascotaperdida.php?"
                +"id="+txtID.getText().toString()
                +"&ubicacion="+txtUbicacion.getText().toString()
                +"&nmascota="+ txtNmascota.getText().toString()
                +"&raza=" + txtRaza.getText().toString()
                +"&caracteristicas=" + txtCaracteristicas.getText().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue =  Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jsonArrayRequest);
        Toast.makeText(getContext(), "Registro Actualizado Correctamente", Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }

    private void searchMascotaperdida() {
        String URL = "http://peluditos.mypressonline.com/searchmascotaperdida.php?"
                + "id=" + txtID.getText().toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtUbicacion.setText(jsonObject.getString("ubicacion"));
                        txtRaza.setText(jsonObject.getString("raza"));
                        txtNmascota.setText(jsonObject.getString("nombremascota"));
                        txtCaracteristicas.setText(jsonObject.getString("caracteristicas"));


                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error de Conexión", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue =  Volley.newRequestQueue(getActivity().getApplicationContext());

        queue.add(jsonArrayRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void Deletemascotaperdida() {
        String URL = "http://peluditos.mypressonline.com/deletemascotaperdida.php?"
                + "id=" + txtID.getText().toString();

          JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue =  Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(jsonArrayRequest);
        Toast.makeText(getContext(), "Registro Eliminado Correctamente", Toast.LENGTH_SHORT).show();
        limpiarCajas();
    }



    void limpiarCajas() {

        txtUbicacion.setText("");
        txtNmascota.setText("");
        txtRaza.setText("");
        txtCaracteristicas.setText("");
    }
}


