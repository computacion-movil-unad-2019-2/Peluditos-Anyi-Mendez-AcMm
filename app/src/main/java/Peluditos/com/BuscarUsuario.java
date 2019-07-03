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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BuscarUsuario extends Fragment implements Response.ErrorListener {

    EditText txtUser, txtPwd, txtNames, txtDocumento;
    Button BtnSearch, BtnActualizar, BtnDelete;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_buscar_usuario, container, false);

        txtUser = (EditText) vista.findViewById(R.id.user);
        txtPwd = (EditText) vista.findViewById(R.id.pwd);
        txtNames= (EditText) vista.findViewById(R.id.names);
        txtDocumento= (EditText) vista.findViewById(R.id.documento);
        BtnSearch = (Button) vista.findViewById(R.id.BtnSearch);
        BtnActualizar = (Button) vista.findViewById(R.id.BtnActualizar);
        BtnDelete = (Button) vista.findViewById(R.id.BtnDelete);



        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchUsuario();
            }
        });
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteUsuario();
            }
        });

        BtnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarUsuario();
            }
        });





        return vista;
    }

    private void SearchUsuario() {
        String URL = "http://peluditos.mypressonline.com/searchusuarios.php?"+ "documento=" + txtDocumento.getText().toString();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txtUser.setText(jsonObject.getString("user"));
                        txtNames.setText(jsonObject.getString("names"));
                        txtPwd.setText(jsonObject.getString("pwd"));



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



    private void ActualizarUsuario() {
        String url = "http://peluditos.mypressonline.com/updateusuario.php?names="
                +txtNames.getText().toString()
                +"&user="+ txtUser.getText().toString()
                +"&pwd=" + txtPwd.getText().toString()
                +"&documento=" + txtDocumento.getText().toString();
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


    private void DeleteUsuario() {

        String URL = "http://peluditos.mypressonline.com/deleteusuario.php?"
                + "documento=" + txtDocumento.getText().toString();

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

        txtNames.setText("");
        txtUser.setText("");
        txtPwd.setText("");

    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
