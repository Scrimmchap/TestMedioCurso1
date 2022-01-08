package mx.edu.itchetumal.evaluacionfinalzalmanwilliams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText cNombre;
    EditText cApellidop;
    EditText cApellidom;
    TextView tcurp;
    private RadioGroup grupo;
    private Spinner spinner1;
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
        setContentView(R.layout.activity_main);
        cNombre = (EditText) findViewById(R.id.cNombre);
        cApellidop = (EditText) findViewById(R.id.cApellidop);
        cApellidom = (EditText) findViewById(R.id.cApellidom);
        tcurp = (TextView) findViewById(R.id.curp);
        grupo = (RadioGroup) findViewById(R.id.opciones);
        spinner1=(Spinner)findViewById(R.id.lista);
        SpAnio=(Spinner)findViewById(R.id.spinnerAnio);
        SpMes=(Spinner)findViewById(R.id.spinnerMes);
        SpDia=(Spinner)findViewById(R.id.spinnerDia);
        TvAnio=(TextView)findViewById(R.id.textViewAnio);
        TvMes=(TextView)findViewById(R.id.textViewMes);
        TvDia=(TextView)findViewById(R.id.textViewDia);

        String[] estados = {"AGUASCALIENTES","BAJA CALIFORNIA","BAJA CALIFORNIA SUR","CAMPECHE","COAHUILA"
                ,"COLIMA","CHIAPAS","CHIHUAHUA","DISTRITO FEDERAL","DURANGO","GUANAJUATO","GUERRERO","HIDALGO","JALISCO",
                "MÉXICO","MICHOACÁN","MORELOS","NAYARIT","NUEVO LEÓN","OAXACA","PUEBLA","QUERÉTARO","QUINTANA ROO",
                "SAN LUIS POTOSÍ","SINALOA","SONORA","TABASCO","TAMAULIPAS","TLAXCALA","VERACRUZ","YUCATÁN"
                ,"ZACATECAS"
                ,"NACIDO EN EL EXTRANJERO"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                ( this,android.R.layout.simple_dropdown_item_1line,estados );
        spinner1.setAdapter(adapter);
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
    //Creacion del metodo que calcula RFC
    public void miCURP(View view) {
        try {
            //Variable a la que se asignara RFC
            String curp = "";
            //variable destinada a la primera vocal del apellido paterno
            String vocal = "";
            //almacena seleccion del spinner
            String seleccion = spinner1.getSelectedItem().toString();
            //anexo de la primera letra del EditText apellido paterno
            curp += cApellidop.getText().toString().substring(0,
                    1).toUpperCase();
            //ciclo que recorre la cadena de apellido paterno en busca de la String vocal
            for (int i = 1; i < (cApellidop.getText().toString().length() - 1);
                 i++) {
                //extrae cada una de las letras
                vocal = cApellidop.getText().toString().substring(i, i +
                        1).toUpperCase();
                //La condicion identifica si el valor de la variable "vocal" es una vocal
                if (vocal.equals("A") || vocal.equals("E") || vocal.equals("I")
                        || vocal.equals("O") || vocal.equals("U")) {
                    //Si la condicion n es verdadera la vocal es asignada a la variable rfc
                    curp += vocal;
                    //El ciclo se rompe
                    break;
                }
            }
            //Anexo de la primer letra del EditText apellido materno
            curp += cApellidom.getText().toString().substring(0,
                    1).toUpperCase();
            //Anexo de la primer letra del EdiText nombre
            curp += cNombre.getText().toString().substring(0, 1).toUpperCase();
            TvAnio.setText(SpAnio.getSelectedItem() + "");
            TvMes.setText(SpMes.getSelectedItem() + "");
            TvDia.setText(SpDia.getSelectedItem() + "");
            //Anexamos los digitos del año
            curp+=Integer.parseInt(TvAnio.getText().toString().substring(2,3));
            curp+=Integer.parseInt(TvAnio.getText().toString().substring(3,4));
            //Anexamos el mes seleccionado
            if (TvMes.getText().toString().length() < 2) {
                //Si es verdadero anexa un 0 al inicio
                //Y despues se agrega a la variable rfc
                curp += "0" + TvMes.getText().toString();
            } else {
                //Si es falso se agrega a la variable rfc
                curp += TvMes.getText().toString();
            }
//Anexamos el dia seleccionado
            if (TvDia.getText().toString().length() < 2) {
                //Si es verdadero anexa un 0 al inicio
                //Y despues se agrega a la variable rfc
                curp += "0" + TvDia.getText().toString();
            } else {
                //Si es falso se agrega a la variable rfc
                curp += TvDia.getText().toString();
            }
            if (grupo.getCheckedRadioButtonId() == R.id.radio_masculino) {
                curp += "H";
            } else {
                curp += "M";
            }
            if (seleccion.equals("AGUASCALIENTES")) {
                curp += "AS";
            } else if (seleccion.equals("BAJA CALIFORNIA")) {
                curp += "BC";
            } else if (seleccion.equals("BAJA CALIFORNIA SUR")) {
                curp += "BS";
            } else if (seleccion.equals("CAMPECHE")) {
                curp += "CC";
            } else if (seleccion.equals("COAHUILA")) {
                curp += "CL";
            } else if (seleccion.equals("COLIMA")) {
                curp += "CM";
            } else if (seleccion.equals("CHIAPAS")) {
                curp += "CS";
            } else if (seleccion.equals("CHIHUAHUA")) {
                curp += "CH";
            } else if (seleccion.equals("DISTRITO FEDERAL")) {
                curp += "DF";
            } else if (seleccion.equals("DURANGO")) {
                curp += "DG";
            } else if (seleccion.equals("GUANAJUATO")) {
                curp += "GT";
            } else if (seleccion.equals("GUERRERO")) {
                curp += "GR";
            } else if (seleccion.equals("HIDALGO")) {
                curp += "HG";
            } else if (seleccion.equals("JALISCO")) {
                curp += "JC";
            } else if (seleccion.equals("MÉXICO")) {
                curp += "MC";
            } else if (seleccion.equals("MICHOACÁN")) {
                curp += "MN";
            } else if (seleccion.equals("MORELOS")) {
                curp += "MS";
            } else if (seleccion.equals("NAYARIT")) {
                curp += "NT";
            } else if (seleccion.equals("NUEVO LEÓN")) {
                curp += "NL";
            } else if (seleccion.equals("OAXACA")) {
                curp += "OC";
            } else if (seleccion.equals("PUEBLA")) {
                curp += "PL";
            } else if (seleccion.equals("QUERÉTARO")) {
                curp += "QT";
            } else if (seleccion.equals("QUINTANA ROO")) {
                curp += "QR";
            } else if (seleccion.equals("SAN LUIS POTOSÍ")) {
                curp += "SP";
            } else if (seleccion.equals("SINALOA")) {
                curp += "SL";
            } else if (seleccion.equals("SONORA")) {
                curp += "SR";
            } else if (seleccion.equals("TABASCO")) {
                curp += "TC";
            } else if (seleccion.equals("TAMAULIPAS")) {
                curp += "TS";
            } else if (seleccion.equals("TLAXCALA")) {
                curp += "TL";
            } else if (seleccion.equals("VERACRUZ")) {
                curp += "VZ";
            } else if (seleccion.equals("YUCATÁN")) {
                curp += "YN";
            } else if (seleccion.equals("ZACATECAS")) {
                curp += "ZS";
            } else if (seleccion.equals("NACIDO EN EL EXTRANJERO")) {
                curp += "NE";
            }
            for (int i = 1; i < (cApellidop.getText().toString().length() - 1);
                 i++) {
                vocal = cApellidop.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vocal.equals("Ñ")) {
                    curp += "X";
                } else if (vocal.equals("B") || vocal.equals("C") ||
                        vocal.equals("D") || vocal.equals("F")
                        || vocal.equals("G") || vocal.equals("H") ||
                        vocal.equals("J") || vocal.equals("K")
                        || vocal.equals("L") || vocal.equals("M") ||
                        vocal.equals("N") || vocal.equals("P")
                        || vocal.equals("Q") || vocal.equals("R") ||
                        vocal.equals("S") || vocal.equals("T")
                        || vocal.equals("V") || vocal.equals("W") ||
                        vocal.equals("X") || vocal.equals("Y")
                        || vocal.equals("Z")) {
                    curp += vocal;
                    break;
                }
            }
            for (int i = 1; i < (cApellidom.getText().toString().length() - 1);
                 i++) {
                vocal = cApellidom.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vocal.equals("Ñ")) {
                    curp += "X";
                } else if (vocal.equals("B") || vocal.equals("C") ||
                        vocal.equals("D")
                        || vocal.equals("F") || vocal.equals("G") ||
                        vocal.equals("H")
                        || vocal.equals("J") || vocal.equals("K") ||
                        vocal.equals("L")
                        || vocal.equals("M") || vocal.equals("N") ||
                        vocal.equals("P")
                        || vocal.equals("Q") || vocal.equals("R") ||
                        vocal.equals("S")
                        || vocal.equals("T") || vocal.equals("V") ||
                        vocal.equals("W")
                        || vocal.equals("X") || vocal.equals("Y") ||
                        vocal.equals("Z")) {
                    curp += vocal;
                    break;
                }
            }
            for (int i = 1; i < (cNombre.getText().toString().length() - 1); i++)
            {
                vocal = cNombre.getText().toString().substring(i, i +
                        1).toUpperCase();
                if (vocal.equals("Ñ")) {
                    curp += "X";
                } else if (vocal.equals("B") || vocal.equals("C") ||
                        vocal.equals("D")
                        || vocal.equals("F") || vocal.equals("G") ||
                        vocal.equals("H")
                        || vocal.equals("J") || vocal.equals("K") ||
                        vocal.equals("L")
                        || vocal.equals("M") || vocal.equals("N") ||
                        vocal.equals("P")
                        || vocal.equals("Q") || vocal.equals("R") ||
                        vocal.equals("S")
                        || vocal.equals("T") || vocal.equals("V") ||
                        vocal.equals("W")
                        || vocal.equals("X") || vocal.equals("Y") ||
                        vocal.equals("Z")) {
                    curp += vocal;
                    break;
                }
            }
            //Homoclave
            Random rnd = new Random();
            int xtend = rnd.nextInt(90) + 10;
            curp += xtend;

            tcurp.setText("CURP:" + curp);
        } catch (Exception e) {
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
        tcurp.setText("CURP:");
    }
    public void atras(View view) {
        Intent intent2 = new Intent(view.getContext(),MainActivity.class);
        startActivityForResult(intent2,0);
    }
}
