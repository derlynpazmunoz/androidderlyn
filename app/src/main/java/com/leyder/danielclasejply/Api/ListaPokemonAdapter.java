package com.leyder.danielclasejply.Api;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leyder.danielclasejply.Pokemon;
import com.leyder.danielclasejply.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class ListaPokemonAdapter extends RecyclerView.Adapter <ListaPokemonAdapter.ViewHolder> {
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private ArrayList<Pokemon> dataset ;


    private Context context;
    private String[] localDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView2;
        private TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textView);
        }
    }

    public ListaPokemonAdapter(Context context) {

        this.context = context;
        dataset = new ArrayList<>();
    }

@Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poke ,parent,false );
        return new ViewHolder(view);
}
@Override
    public  int getItemCount() {

    return  dataset.size();
}
@Override
    public void onBindViewHolder(ViewHolder holder , int position){
    Pokemon p = dataset.get(position);
    holder.name.setText(p.getName());


}
 public void adicionaraListaPokemon(ArrayList<Pokemon> listapokemon) {
        dataset.addAll(listapokemon);
        notifyDataSetChanged();
 }

}