package mx.betopartida.puppydatabase.fragment;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.adapter.MascotaAdapter;
import mx.betopartida.puppydatabase.pojo.Mascota;

/**
 * Created by beto on 05/08/2016.
 */
public interface IListaFragmentView {

    public void generarLista();
    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptador (MascotaAdapter adapter);

}
