package mx.betopartida.puppydatabase.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.betopartida.puppydatabase.pojo.Mascota;
import mx.betopartida.puppydatabase.R;

/**
 * Created by beto on 30/06/2016.
 */
public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.FotoViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public FotoAdapter(ArrayList mascotas, Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @Override
    public FotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_foto,parent,false);
        return new FotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FotoViewHolder holder, final int position) {
        final Mascota mascota=mascotas.get(position);
        //holder.tvNombre.setText(mascota.getNombre());
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvLikes.setText(String.valueOf(mascota.getLikes()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {

        //private TextView tvNombre;
        private ImageView imgFoto;
        private TextView tvLikes;

        public FotoViewHolder(View itemView) {
            super(itemView);
            //tvNombre=(TextView) itemView.findViewById(R.id.tvNombre);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikes=(TextView) itemView.findViewById(R.id.tvLikes);
        }
    }
}
