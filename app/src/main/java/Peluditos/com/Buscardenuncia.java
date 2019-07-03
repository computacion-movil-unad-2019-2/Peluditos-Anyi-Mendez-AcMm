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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Buscardenuncia extends Fragment {
    EditText txtDescripcion, txtUbicacion, txtID, txtseguimiento;
    Button BtnSearch, BtnActualizar, BtnDelete, BtnAddSeguimiento;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_buscardenuncia, container, false);
        View vista = inflater.inflate(R.layout.fragment_buscardenuncia, container, false);

        txtID = (EditText) vista.findViewById(R.id.txtid);
        txtUbicacion = (EditText) vista.findViewById(R.id.lugardenuncia);
        txtDescripcion = (EditText) vista.findViewById(R.id.descripciondenuncia);
        txtseguimiento = (EditText) vista.findViewById(R.id.seguimiento);
        BtnSearch = (Button) vista.findViewById(R.id.BtnSearch);
        BtnActualizar = (Button) vista.findViewById(R.id.BtnActualizar);
        BtnDelete = (Button) vista.findViewById(R.id.BtnDelete);
        BtnAddSeguimiento = (Button) vista.findViewById(R.id.BtnSeguimiento);


        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDenuncia();
            }
        });
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteDenuncia();
            }
        });

        BtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarDenuncia();
            }
        });

        BtnAddSeguimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSeguimiento();
            }
        });
        return vista;


    }



    void AddSeguimiento(){
        //192.168.1.66(172.29.243.3
        String urlseguimiento = "http://peluditos.mypressonline.com/addseguimientodenuncia.php?"
                + "id=" + txtID.getText().toString()
                +"&seguimiento=" + txtseguimiento.getText().toString();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlseguimiento, new Response.Listener<JSONArray>() {
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
        Toast.makeText(getContext(), "Seguimiento Creado Correctamente", Toast.LENGTH_SHORT).show();
        limpiarCajas();

    }

    private void DeleteDenuncia() {
        String URL = "http://peluditos.mypressonline.com/deletedenuncia.php?"
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

    private void searchDenuncia() {
        String URL = "http://peluditos.mypressonline.com/searchdenuncia.php?"
                + "id=" + txtID.getText().toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtUbicacion.setText(jsonObject.getString("ubicacion"));
                        txtDescripcion.setText(jsonObject.getString("descripcion"));


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


    private void ActualizarDenuncia() {

        String url = "http://peluditos.mypressonline.com/updatedenuncia.php?"
                +"id="+txtID.getText().toString()
                +"&ubicacion="+txtUbicacion.getText().toString()
                +"&descripcion="+ txtDescripcion.getText().toString();

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
    void limpiarCajas() {

        txtUbicacion.setText("");
        txtDescripcion.setText("");

    }
}
