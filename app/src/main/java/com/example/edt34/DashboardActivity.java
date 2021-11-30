package com.example.edt34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    public static final String TITLE = "com.example.edt34.TITLE";
    public static final String IMAGE = "com.example.edt34.IMAGE";
    private ListView listView;
    private TextView openClose;

    Refuge refuge1 = new Refuge(
            "Refugi Josep Maria Blanc",
            R.drawable.foto1,
            "Parc Aigüestortes",
            "Vall d'Aran",
            "Catalunya",
            "Distancia: 2:30h",
            "Desnivell: 1200m",
            "2.100m",
            "30",
            "Open"
    );
    Refuge refuge2 = new Refuge(
            "Refugi Cap de Llauset",
            R.drawable.foto2,
            "Parc Aigüestortes",
            "Osca",
            "Arago",
            "Distancia: 2:15h",
            "Desnivell: 1100m",
            "2.800m",
            "25",
            "Open"
    );
    Refuge refuge3 = new Refuge(
            "Refugi Ventosa i Clavell",
            R.drawable.foto3,
            "Parc Aigüestortes",
            "Vall d'Aran",
            "Catalunya",
            "Distancia: 3:15h",
            "Desnivell: 800m",
            "2.150m",
            "45",
            "Close"
    );
    Refuge refuge4 = new Refuge(
            "Refugi Amitges",
            R.drawable.foto4,
            "Parc Aigüestortes",
            "Vall d'Aran",
            "Catalunya",
            "Distancia: 2:30h",
            "Desnivell: 750m",
            "2.400m",
            "87",
            "Open"
    );
    Refuge refuge5 = new Refuge(
            "Refugi Josep Maria Montfort",
            R.drawable.foto5,
            "Alt Pirineu",
            "Vall Ferrera",
            "Catalunya",
            "Distancia: 2:30h",
            "Desnivell: 950m",
            "2.875m",
            "23",
            "Open"
    );

    ArrayList<Refuge> refuges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        listView = findViewById(R.id.listView);
        openClose = findViewById(R.id.openClosed);

        refuges.add(refuge1);
        refuges.add(refuge2);
        refuges.add(refuge3);
        refuges.add(refuge4);
        refuges.add(refuge5);

        CustomAdapter customAdapter = new CustomAdapter(this,refuges);
        listView.setAdapter(customAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DashboardActivity.this,ReservaActivity.class);
                intent.putExtra(TITLE, refuges.get(i).getRefugeName());
                startActivity(intent);
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        private Context context; //context
        private List<Refuge> items; //data source of the list adapter
        //constructor
        public CustomAdapter(Context context, ArrayList<Refuge> items) {
            this.context = context;
            this.items = items;
        }
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_row,null);
            TextView refuge = view.findViewById(R.id.refuge);
            ImageView imgRefuge = view.findViewById(R.id.imageView);
            TextView parc = view.findViewById(R.id.parc);
            TextView comarca = view.findViewById(R.id.comarca);
            TextView regio = view.findViewById(R.id.regio);
            TextView distancia = view.findViewById(R.id.distancia);
            TextView desnivell = view.findViewById(R.id.desnivell);
            TextView altura = view.findViewById(R.id.altura);
            TextView temps = view.findViewById(R.id.temps);
            TextView openClosed = view.findViewById(R.id.openClosed);

            refuge.setText(refuges.get(position).getRefugeName());
            parc.setText(refuges.get(position).getParcName());
            comarca.setText(refuges.get(position).getComarca());
            regio.setText(refuges.get(position).getRegio());
            distancia.setText(refuges.get(position).getDistancia());
            desnivell.setText(refuges.get(position).getDesnivell());
            altura.setText(refuges.get(position).getAltura());
            temps.setText(refuges.get(position).getTemps());
            openClosed.setText(refuges.get(position).getOpenClose());
            imgRefuge.setImageResource(refuges.get(position).getImageName());

            return view;
        }
    }

}