package mx.betopartida.puppydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Acercade extends AppCompatActivity {

    private Toolbar miActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acercade);

        miActionBar=(Toolbar) findViewById(R.id.miActionBar);
        miActionBar.findViewById(R.id.btnStar).setVisibility(View.GONE);
        miActionBar.findViewById(R.id.cat_paw).setVisibility(View.GONE);
        TextView tv = (TextView) (miActionBar.findViewById(R.id.titulo));
        tv.setText(R.string.menu_acercade);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Acercade.this.finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
