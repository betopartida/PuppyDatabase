package mx.betopartida.puppydatabase.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import mx.betopartida.puppydatabase.pojo.Mascota;
import mx.betopartida.puppydatabase.adapter.MascotaAdapter;
import mx.betopartida.puppydatabase.R;
import mx.betopartida.puppydatabase.presentador.IListaFragmentPresenter;
import mx.betopartida.puppydatabase.presentador.ListaFragmentPresenter;

public class ListaFragment extends Fragment implements IListaFragmentView {

    private ArrayList<Mascota> mascotas;
    private RecyclerView rvMascotas;
    private IListaFragmentPresenter listaFragmentPresenter;

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista, container, false);

        mascotas=new ArrayList<Mascota>();
        rvMascotas=(RecyclerView) v.findViewById(R.id.rvMascotas);
        listaFragmentPresenter =new ListaFragmentPresenter(this,getContext());

        return v;
    }

    @Override
    public void generarLista() {
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdapter adapter=new MascotaAdapter(mascotas,getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptador(MascotaAdapter adapter) {
        rvMascotas.setAdapter(adapter);
    }
}
