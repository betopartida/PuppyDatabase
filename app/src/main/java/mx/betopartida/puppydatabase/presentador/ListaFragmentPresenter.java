package mx.betopartida.puppydatabase.presentador;

import android.content.Context;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.bd.ConstructorMascota;
import mx.betopartida.puppydatabase.fragment.IListaFragmentView;
import mx.betopartida.puppydatabase.pojo.Mascota;

/**
 * Created by beto on 05/08/2016.
 */
public class ListaFragmentPresenter implements IListaFragmentPresenter {

    private IListaFragmentView iListaFragmentView;
    private Context context;
    private ArrayList<Mascota> mascotas;

    public ListaFragmentPresenter (IListaFragmentView iListaFragmentView,Context context) {
        this.iListaFragmentView=iListaFragmentView;
        this.context=context;
        obtenerMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMascotas() {
        ConstructorMascota constructor=new ConstructorMascota(context);
        mascotas=constructor.cargarDatosMascotas();
    }

    @Override
    public void mostrarMascotasRV() {
        iListaFragmentView.inicializarAdaptador(iListaFragmentView.crearAdaptador(mascotas));
        iListaFragmentView.generarLista();
    }
}
