package mx.betopartida.puppydatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.adapter.MascotaAdapter;
import mx.betopartida.puppydatabase.bd.ConstructorMascota;
import mx.betopartida.puppydatabase.pojo.Mascota;

public class Favoritas extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private MascotaAdapter adapter;
    private Toolbar miActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        miActionBar.findViewById(R.id.btnStar).setVisibility(View.GONE);
        TextView tv=(TextView) miActionBar.findViewById(R.id.titulo);
        tv.setText(R.string.titulo_favs);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //recuperamos el array de mascotas
        //Intent intent = this.getIntent();
        ConstructorMascota constructorMascota=new ConstructorMascota(getApplicationContext());
        //mascotas = (ArrayList<Mascota>) intent.getSerializableExtra("mascotas");
        mascotas=constructorMascota.cargarDatosMascotasBD();
        rvMascotas=(RecyclerView) this.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);

        inicializaAdaptador();

    }

    public void inicializaAdaptador() {
        adapter=new MascotaAdapter(mascotas,this);
        rvMascotas.setAdapter(adapter);
    }

}
