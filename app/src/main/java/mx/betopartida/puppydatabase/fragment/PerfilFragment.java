package mx.betopartida.puppydatabase.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.pojo.Mascota;
import mx.betopartida.puppydatabase.R;
import mx.betopartida.puppydatabase.adapter.FotoAdapter;

public class PerfilFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    public RecyclerView rvFotos;
    public FotoAdapter adapter;

    private CircularImageView circularImageView;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        mascotas=new ArrayList<Mascota>();
        rvFotos=(RecyclerView) v.findViewById(R.id.rvFotos);

        GridLayoutManager glm=new GridLayoutManager(getActivity(),3);
        glm.setOrientation(LinearLayoutManager.VERTICAL);

        rvFotos.setLayoutManager(glm);

        inicializaListaFotos();

        TextView tvNombre=(TextView) v.findViewById(R.id.tvNombre);
        CircularImageView imgFoto=(CircularImageView) v.findViewById(R.id.civ_profile);

        imgFoto.setImageResource(mascotas.get(1).getFoto());

        tvNombre.setText(mascotas.get(1).getNombre());

        inicializaAdaptador();

        return v;
    }

    public void inicializaListaFotos() {
        mascotas.add(new Mascota("Nina",R.drawable.pet4,6));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,9));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,1));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,4));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,10));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,12));
    }

    public void inicializaAdaptador() {
        adapter=new FotoAdapter(mascotas,getActivity());
        rvFotos.setAdapter(adapter);
    }

}
