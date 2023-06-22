package com.leyder.danielclasejply;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.leyder.danielclasejply.Api.ListaPokemonAdapter;
import com.leyder.danielclasejply.Api.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private ListaPokemonAdapter listaPokemonAdapter;

   private EditText  e_name;
    private Retrofit retrofit;
    private final String TAG="pokeapi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamdo de recivleviwe
        //
        recyclerView=findViewById(recyclerView.getItemDecorationCount());
        listaPokemonAdapter=new ListaPokemonAdapter(this);
        recyclerView .setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
       final  LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
       recyclerView.setLayoutManager(linearLayoutManager);



        retrofit=new  Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtnerDatos();

    }

    private void obtnerDatos() {
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<POKEMONRESPUESTA> POKEMONRESPUESTACall = service.obtenerlistapokemon();
        POKEMONRESPUESTACall.enqueue(new Callback<POKEMONRESPUESTA>() {


            @Override
            public void onResponse(Call<POKEMONRESPUESTA> call, Response<POKEMONRESPUESTA> response) {
                if (response.isSuccessful()) {
                    POKEMONRESPUESTA pokemonrespuesta = response.body();
                    List<Pokemon> listapokemon = pokemonrespuesta.getResults();
                    for (int i = 0; i < listapokemon.size(); i++) {
                        Pokemon p = listapokemon.get(i);
                        Log.e(TAG, "pokemon" + p.getName());
                    }
                    listaPokemonAdapter.adicionaraListaPokemon((ArrayList<Pokemon>) listapokemon);

                } else {
                    Log.e(TAG, "onResponde" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<POKEMONRESPUESTA> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());

            }

        });

    }
}