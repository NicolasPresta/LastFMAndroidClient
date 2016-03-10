package com.example.presta.concepto.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.presta.concepto.R;
import com.example.presta.concepto.domain.Artista;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Presta on 09/03/2016.
 */
public class TagArtistAdapter extends RecyclerView.Adapter<TagArtistAdapter.TagArtistViewHolder> {

    ArrayList<Artista> artistas;
    Context context;

    public TagArtistAdapter(Context context) {
        this.context = context;
        this.artistas = new ArrayList<>();
    }

    // Este metodo se ejecuta cada vez que un elemento se tiene que dibujar
    @Override
    public TagArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_tag_artist, parent, false);

        return new TagArtistViewHolder(itemView);
    }

    // Este metodo se ejecuta cada vez que un elemento tiene que conectarse a la fuente de datos
    @Override
    public void onBindViewHolder(TagArtistViewHolder holder, int position) {
        Artista currentArtist = artistas.get(position);

        holder.setArtistName(currentArtist.getNombre());
        holder.setArtistImage(currentArtist.getUrlMediumImage());
        holder.setArtistPlayCount(currentArtist.getPlayCount());
        holder.setArtistListeners(currentArtist.getListeners());
    }

    // La cantidad de items de la lista
    @Override
    public int getItemCount() {
        return artistas.size();
    }

    public void addAll(@NonNull ArrayList<Artista> artistasParametro) {

        if (artistasParametro == null)
            throw new NullPointerException("Los item están en nulos");

        int cantidadActual = artistas.size();

        artistas.addAll(artistasParametro);
        // notifyItemRangeInserted(cantidadActual - 1, artistasParametro.size());
        notifyDataSetChanged();
    }

    public class TagArtistViewHolder extends RecyclerView.ViewHolder {

        TextView artistName;
        ImageView artistImage;
        TextView artistPlayCount;
        TextView artistListeners;

        public TagArtistViewHolder(View itemView) {
            super(itemView);

            artistName = (TextView) itemView.findViewById(R.id.txt_name);
            artistImage = (ImageView) itemView.findViewById(R.id.img_artist);
            artistPlayCount = (TextView) itemView.findViewById(R.id.txt_playcount);
            artistListeners = (TextView) itemView.findViewById(R.id.txt_nam_listeners);

        }

        public void setArtistName(String nombre) {
            artistName.setText(nombre);
        }

        public void setArtistPlayCount(String playCount) {
            artistPlayCount.setText(playCount);
        }

        public void setArtistListeners(String listeners) {
            artistListeners.setText(listeners);
        }

        public void setArtistImage(String url) {

            if (url != null) {
               /* Glide.with(context)
                        .load(url)
                        .placeholder(R.drawable.placeholder)
                        .into(artistImage);*/

                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.placeholder)
                        .into(artistImage);
            } else {
              /*  Glide.with(context)
                        .load(R.drawable.placeholder)
                        .into(artistImage);*/

                Picasso.with(context)
                        .load(R.drawable.placeholder)
                        .into(artistImage);
            }
        }

    }
}
