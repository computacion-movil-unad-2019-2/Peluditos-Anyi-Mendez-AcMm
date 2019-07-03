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


public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
        RequestQueue rq;
        JsonRequest jrq;
        EditText txtUser, txtPwd, txtNames, txtDocumento;
        Button btnSesion, btnRegistrar;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_registro, container, false);
        txtUser = (EditText) vista.findViewById(R.id.raza);
        txtPwd = (EditText) vista.findViewById(R.id.lugar);
        txtNames= (EditText) vista.findViewById(R.id.txtnames);
        txtDocumento= (EditText) vista.findViewById(R.id.txtIdentificacion);

        btnSesion = (Button) vista.findViewById(R.id.btnsesion);
        btnRegistrar = (Button) vista.findViewById(R.id.btnregistrar);
        rq = Volley.newRequestQueue(getContext());

        btnSesion.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        iniciar_sesion();
        }
        });


        btnRegistrar.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        registrar_usuario();
        }
        });


        // Inflate the layout for this fragment
        return vista;
        }

@Override
public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No Se pudó registrar el usuario " +error.toString()+ txtUser.getText().toString(), Toast.LENGTH_LONG).show();
        }

@Override
public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Se ha registrado el usuario " + txtUser.getText().toString(), Toast.LENGTH_SHORT).show();
        limpiarCajas();
        }

        void registrar_usuario(){
                //192.168.1.66(172.29.243.3
                String url = "http://peluditos.mypressonline.com/registrarse.php?"
                        +"names="+txtNames.getText().toString()
                        +"&user="+ txtUser.getText().toString()
                        +"&pwd="+ txtPwd.getText().toString()
                        +"&documento=" + txtDocumento.getText().toString();



                //jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
                rq.add(jrq);

        }


        void limpiarCajas() {
        txtNames.setText("");
        txtUser.setText("");
        txtPwd.setText("");
        txtDocumento.setText("");
        }
        void iniciar_sesion() {

        Sesion fr=new Sesion();
        //fr.setArguments(bn);
        getActivity().getSupportFragmentManager().beginTransaction()
        .replace(R.id.escenarioregistro,fr)
        .addToBackStack(null)
        .commit();
        }




        }
