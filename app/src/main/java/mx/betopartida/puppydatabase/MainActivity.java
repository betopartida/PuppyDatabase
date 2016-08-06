package mx.betopartida.puppydatabase;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.adapter.PageAdapter;
import mx.betopartida.puppydatabase.bd.BaseDatos;
import mx.betopartida.puppydatabase.fragment.ListaFragment;
import mx.betopartida.puppydatabase.fragment.PerfilFragment;
import mx.betopartida.puppydatabase.pojo.Mascota;

/*
* Notas: el boton FAB limpia la tabla de mascotas, pero mantiene los likes
*por lo tanto al hacer click en la estrella se muestran las mascotas
* recientemente 'raiteadas'
*/

public class MainActivity extends AppCompatActivity {

    Toolbar miActionBar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if(miActionBar!=null) {
            setSupportActionBar(miActionBar);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        activaEstrella();
        activaFAB();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intent=new Intent(MainActivity.this,Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcercaDe:
                Intent intent2=new Intent(MainActivity.this,Acercade.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void activaEstrella() {
        ImageView estrella = (ImageView) MainActivity.this.miActionBar.findViewById(R.id.btnStar);
        estrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Favoritas.class);
                //paso el array de mascotas para no tener que volver a crearlo
                //intent.putExtra("mascotas",mascotas);
                startActivity(intent);

            }
        });
    }

    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListaFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }
    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragment()));
        
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_icon);
    }

    public void activaFAB() {
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.btnFAB);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, R.string.borrar, Snackbar.LENGTH_LONG).show();
                borraTablaMascotas();
                finish();
                startActivity(getIntent());
            }
        });
    }

    private void borraTablaMascotas() {
        BaseDatos bd=new BaseDatos(getApplicationContext());
        bd.borraTablaMascotas();
    }

}
