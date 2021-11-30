package com.example.edt34;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservaActivity extends AppCompatActivity {

    DatePickerDialog pickerEntrada;
    DatePickerDialog pickerSortida;

    private EditText textEntrada;
    private EditText textSortida;

    private Spinner spinner;
    private ImageView imageReserva;
    private Switch aSwitch;
    private RadioGroup radioGroup;
    private RadioButton capButton;


    private Button booking;

    private EditText nom;
    private EditText cognom;
    private EditText email;
    private EditText numPersones;


    List<String> refugesList = new ArrayList<>();
    List<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        spinner = findViewById(R.id.spinner);
        imageReserva = findViewById(R.id.imageReserva);
        aSwitch = findViewById(R.id.switchPensio);
        radioGroup = findViewById(R.id.radioGroup);
        capButton = findViewById(R.id.cap);
        textEntrada = findViewById(R.id.date1);
        textSortida = findViewById(R.id.date2);
        booking = findViewById(R.id.booking);



        nom = findViewById(R.id.nom);
        cognom = findViewById(R.id.cognom);
        email= findViewById(R.id.email);
        numPersones = findViewById(R.id.numperson);

        textEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePickerDialog
                //DatePickerDialog.OnDateSetListener
                pickerEntrada = new DatePickerDialog(ReservaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1++;
                                textEntrada.setText(i2 + "/" + i1 + "/" + i);
                            }
                        },year,month,day);
                    pickerEntrada.show();
            }
        });

        textSortida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                //DatePickerDialog
                //DatePickerDialog.OnDateSetListener
                pickerSortida = new DatePickerDialog(ReservaActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1++;
                                textSortida.setText(i2 + "/" + i1 + "/" + i);
                            }
                        },year,month,day);
                pickerSortida.show();
            }
        });

        refugesList.add(0, "Selecciona Refugi");
        refugesList.add("Refugi Josep Maria Blanc");
        refugesList.add("Refugi Cap de Llauset");
        refugesList.add("Refugi Ventosa i Clavell");
        refugesList.add("Refugi Amitges");
        refugesList.add("Refugi Josep Maria Montfort");

        imageList.add(0);
        imageList.add(R.drawable.foto1);
        imageList.add(R.drawable.foto2);
        imageList.add(R.drawable.foto3);
        imageList.add(R.drawable.foto4);
        imageList.add(R.drawable.foto5);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                refugesList
        );
        spinner.setAdapter(dataAdapter);
        radioGroup.setAlpha(0f);

        Intent intent = getIntent();
        String text_Title = intent.getStringExtra(DashboardActivity.TITLE);
        int imageDefault = intent.getIntExtra(DashboardActivity.IMAGE,0);
        imageReserva.setImageResource(imageDefault);

        for(int i =0; i<refugesList.size();i++){
            if(text_Title.equals(refugesList.get(i))){
                spinner.setSelection(i);
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageReserva.setImageResource(imageList.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//aaa
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                radioGroup.setAlpha(1f);
                else
                    capButton.setChecked(true);

            }
        });


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean field1 = new ReservaActivity().checkFields(nom);
                boolean field2 = new ReservaActivity().checkFields(cognom);
                boolean field3 = new ReservaActivity().checkFields(email);
                boolean field4 = new ReservaActivity().checkFields(numPersones);
                boolean field5 = new ReservaActivity().checkFields(textEntrada);
                boolean field6 = new ReservaActivity().checkFields(textSortida);
                if(field1&&field2&&field3&&field4&& field5&&field6){

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");

                String[] TO = {"user1@escoladeltreball.org", "user2@escoladeltreball.org"};
                String[] CC = {"address1@gmail.com", "address2@gmail.com"};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reserva per app");

                String str ="";
                boolean yesNo = aSwitch.isChecked();
                int menu = radioGroup.getCheckedRadioButtonId();
                if(yesNo){
                    switch (menu){
                        case R.id.radioButton:
                            str = "Vegetarià";
                            break;
                        case R.id.radioButton2:
                            str = "Sense Gluten";
                            break;
                        case R.id.radioButton3:
                            str = "Celíac";
                            break;
                        case R.id.cap:
                            str = "";
                    }
                }

                emailIntent.putExtra(Intent.EXTRA_TEXT,
                        "Nom: "  +nom.getText()+ " " + cognom.getText() +
                        "\nRefugi: " + spinner.getSelectedItem() +
                        "\nEntrada: " + textEntrada.getText() +
                        "\nSortida: " + textSortida.getText() +
                        "\nEmail: " + email.getText()+
                        "\nNum Persones: " +numPersones.getText() +
                        "\nPensió Completa: "+ (yesNo?"SÍ":"NO") +
                        "\nMenu: " + str);
                //Email to...
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ReservaActivity.this, "There is no email client installed.",
                            Toast.LENGTH_SHORT).show();
                }

            }else {
                    Toast toast = Toast.makeText(ReservaActivity.this,"Fill the empty fields first",Toast.LENGTH_SHORT  );
                    toast.show();
                }

            }
        });


        //texttitle.setText(textTitle);
        /*spinnerPerJava.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {@Overridepublic void onItemSelected(AdapterView<?> parent, View view, int position, long id) {// Get Selected value name from the listString selectedCondition = parent.getItemAtPosition(position).toString();        Toast.makeText(MainActivity.this,selectedCondition , Toast.LENGTH_SHORT).show();    }@Overridepublic void onNothingSelected(AdapterView<?> parent) {        Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();    }});
         * */
    }
    public boolean checkFields(EditText editText){
        if(editText.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}