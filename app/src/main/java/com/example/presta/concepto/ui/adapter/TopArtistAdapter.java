package com.example.presta.concepto.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.presta.concepto.R;
import com.example.presta.concepto.domain.Artista;
import com.squareup.picasso.Picasso;

import java.util.Random;

import java.util.ArrayList;

/**
 * Created by Presta on 08/03/2016.
 */
public class TopArtistAdapter extends RecyclerView.Adapter<TopArtistAdapter.TopArtistViewHolder> {

    ArrayList<Artista> artistas;
    Context context;

    public TopArtistAdapter(Context context) {
        this.context = context;
        this.artistas = new ArrayList<>();
    }

    // Este metodo se ejecuta cada vez que un elemento se tiene que dibujar
    @Override
    public TopArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_top_artist, parent, false);

        return new TopArtistViewHolder(itemView);
    }

    // Este metodo se ejecuta cada vez que un elemento tiene que conectarse a la fuente de datos
    @Override
    public void onBindViewHolder(TopArtistViewHolder holder, int position) {
        Artista currentArtist = artistas.get(position);

        holder.setArtistName(currentArtist.getNombre());
        holder.setArtistImage(currentArtist.getUrlMediumImage());
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

    public class TopArtistViewHolder extends RecyclerView.ViewHolder {

        TextView artistName;
        ImageView artistImage;

        public TopArtistViewHolder(View itemView) {
            super(itemView);

            // Con esto consigo que cada vez que se dibuja el item tenga un tamaño distinto, para darle mas "onda"
            Random aRandom = new Random();
            long fraction = (long) (1000 * aRandom.nextDouble());
            int randomNumber = (int) (fraction + 500);
            itemView.getLayoutParams().height = randomNumber;
            //

            artistName = (TextView) itemView.findViewById(R.id.txt_name);
            artistImage = (ImageView) itemView.findViewById(R.id.img_artist);

        }

        public void setArtistName(String nombre) {
            artistName.setText(nombre);
        }

        public void setArtistImage(String url){

            if (url != null) {
               /* Glide.with(context)
                        .load(url)
                        .placeholder(R.drawable.placeholder)
                        .into(artistImage);*/

                Picasso.with(context)
                        .load(url)
                        .placeholder(R.drawable.placeholder)
                        .into(artistImage);
            }
            else {
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