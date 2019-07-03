package Peluditos.com;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sesion extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
RequestQueue rq;
JsonRequest jrq;
EditText  user, pwd;
Button BtnSesion, BtnRegistrar;
//BtnRegistro



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_sesion, container, false);
       View vista=inflater.inflate(R.layout.fragment_sesion, container, false);
        user=(EditText) vista.findViewById(R.id.usuario);
        pwd=(EditText) vista.findViewById(R.id.pwd);
        BtnSesion=(Button) vista.findViewById(R.id.BtnRegistro);
        BtnRegistrar=(Button) vista.findViewById(R.id.BtnRegistrar);
        rq= (RequestQueue) Volley.newRequestQueue(getContext());

        BtnSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        BtnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro();
            }
        });

        return vista;



    }
    void Registro() {

        RegistroFragment fr=new RegistroFragment();
        //fr.setArguments(bn);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenarioregistro,fr)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onResponse(JSONObject response) {
        User usuario= new User();
        Toast.makeText(getContext(),"Inicio de Sesion Exitoso " + user.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;


        try {
            jsonObject=jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPwd(jsonObject.optString("pwd"));
            usuario.setNombres(jsonObject.optString("nombres"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intencionmenu= new Intent(getContext(), menu.class);
        intencionmenu.putExtra(menu.nombres, usuario.getNombres());

        startActivity(intencionmenu);


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se encontro Usuario"+error.toString(), Toast.LENGTH_SHORT).show();
    }

    private void iniciarSesion(){
        String url ="http://peluditos.mypressonline.com/sesion.php?user="+user.getText().toString()+"&pwd="+pwd.getText().toString();


        jrq=new JsonObjectRequest(Request.Method.GET, url, null, this, this );
        rq.add(jrq);


    }
}
