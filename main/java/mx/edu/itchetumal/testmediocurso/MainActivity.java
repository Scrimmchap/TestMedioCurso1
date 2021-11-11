package mx.edu.itchetumal.testmediocurso;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText cNombre;
    EditText cApellidop;
    EditText cApellidom;
    TextView trfc;
    Spinner SpAnio, SpMes, SpDia;
    TextView TvAnio, TvMes, TvDia;
    ArrayList arrayListAnio = new ArrayList();
    ArrayAdapter Adaptador;
    ArrayList arrayListMes = new ArrayList();
    ArrayAdapter Adaptador2;
    ArrayList arrayListDia = new ArrayList();
    ArrayAdapter Adaptador3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfc);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        cNombre = (EditText) findViewById(R.id.cNombre);
        cApellidop = (EditText) findViewById(R.id.cApellidop);
        cApellidom = (EditText) findViewById(R.id.cApellidom);
        trfc = (TextView) findViewById(R.id.rfc);
        SpAnio=(Spinner)findViewById(R.id.spinnerAnio);
        SpMes=(Spinner)findViewById(R.id.spinnerMes);
        SpDia=(Spinner)findViewById(R.id.spinnerDia);
        TvAnio=(TextView)findViewById(R.id.textViewAnio);
        TvMes=(TextView)findViewById(R.id.textViewMes);
        TvDia=(TextView)findViewById(R.id.textViewDia);

        for(int i = 2020; i>=1940;i--)
        {
            arrayListAnio.add(i+"");
        }
        Adaptador=new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListAnio);
        SpAnio = (Spinner)findViewById(R.id.spinnerAnio);
        SpAnio.setAdapter(Adaptador);
        for(int i = 12; i>0;i--)
        {
            arrayListMes.add(i+"");
        }
        Adaptador2=new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListMes);
        SpMes = (Spinner)findViewById(R.id.spinnerMes);
        SpMes.setAdapter(Adaptador2);
        for(int i = 31; i>0;i--)
        {
            arrayListDia.add(i+"");
        }
        Adaptador3=new ArrayAdapter(getBaseContext(),
                android.R.layout.simple_spinner_item, arrayListDia);
        SpDia = (Spinner)findViewById(R.id.spinnerDia);
        SpDia.setAdapter(Adaptador3);
    }

    public void miRFC(View view) {
        try {
            String rfc = "";
            String vocal = "";
            rfc += cApellidop.getText().toString().substring(0, 1).toUpperCase();
            for (int i = 1; i < (cApellidop.getText().toString().length() - 1); i++)
            {
                vocal = cApellidop.getText().toString().substring(i, i +
                        1).toUpperCase();

                if (vocal.equals("A") || vocal.equals("E") || vocal.equals("I") ||
                        vocal.equals("O") || vocal.equals("U")) {

                    rfc += vocal;
                    break;
                }
            }
            rfc += cApellidom.getText().toString().substring(0, 1).toUpperCase();
            rfc += cNombre.getText().toString().substring(0, 1).toUpperCase();
            TvAnio.setText(SpAnio.getSelectedItem() + "");
            TvMes.setText(SpMes.getSelectedItem() + "");
            TvDia.setText(SpDia.getSelectedItem() + "");
            rfc+=Integer.parseInt(TvAnio.getText().toString().substring(2,3));
            rfc+=Integer.parseInt(TvAnio.getText().toString().substring(3,4));
            if (TvDia.getText().toString().length() < 2) {
                rfc += "0" + TvDia.getText().toString();
            } else {
                rfc += TvDia.getText().toString();
            }
            trfc.setText("RFC:" + rfc);
            trfc.setText("RFC:" + rfc);
        }catch (Exception e){
            Toast.makeText(this, "Llena los datos", Toast.LENGTH_SHORT).show();
        }
    }
    public void borrar(View view) {
        cNombre.setText("");
        cApellidop.setText("");
        cApellidom.setText("");
        TvAnio.setText("");
        TvMes.setText("");
        TvDia.setText("");
        trfc.setText("RFC:");
    }
    public void atras(View view) {
        Intent intent2 = new Intent(view.getContext(),MainActivity.class);
        startActivityForResult(intent2,0);
    }
}