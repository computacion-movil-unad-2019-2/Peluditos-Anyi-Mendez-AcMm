package Peluditos.com;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class BuscarSeguimientos extends Fragment {
    EditText txtID;
    TextView txtIdDenuncia;
    EditText txtDenuncia;
    EditText TxtSeguimiento;
    Button BtnSearch, BtnActualizar, BtnDelete;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_buscar_seguimientos, container, false);

        txtID = (EditText) vista.findViewById(R.id.IdSeguimiento);
        txtIdDenuncia = (TextView) vista.findViewById(R.id.txtiddenuncia);

        TxtSeguimiento = (EditText) vista.findViewById(R.id.txtseguimiento);

        BtnSearch = (Button) vista.findViewById(R.id.BtnSearch);
        BtnActualizar = (Button) vista.findViewById(R.id.BtnActualizar);
        BtnDelete = (Button) vista.findViewById(R.id.BtnDelete);

        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchseguimiento();
            }
        });
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteSeguimiento();
            }
        });

        BtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateseguimiento();
            }
        });

        return vista;


    }

    private void updateseguimiento() {
        String url = "http://peluditos.mypressonline.com/updateseguimiento.php?"
                +"id="+txtID.getText().toString()
                +"&seguimiento=" + TxtSeguimiento.getText().toString();

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



    private void DeleteSeguimiento() {

        String URL = "http://peluditos.mypressonline.com/deleteseguimiento.php?"
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

    private void searchseguimiento() {
        String URL = "http://peluditos.mypressonline.com/searchseguimiento.php?"
                + "id=" + txtID.getText().toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtIdDenuncia.setText(jsonObject.getString("iddenuncia"));

                        TxtSeguimiento.setText(jsonObject.getString("seguimiento"));



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

    void limpiarCajas() {

        TxtSeguimiento.setText("");
        txtIdDenuncia.setText("");
        txtID.setText("");
    }

}