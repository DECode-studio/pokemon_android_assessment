package com.example.pokemonandroidassessment.controller.page

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import com.example.pokemonandroidassessment.R
import com.example.pokemonandroidassessment.controller.adapter.ListAbilityAdapter
import com.example.pokemonandroidassessment.controller.data.PokemonDataController
import com.example.pokemonandroidassessment.model.PokemonDetailModel

class DetailActivity : AppCompatActivity() {
    private lateinit var pokemonData : PokemonDataController;
    private lateinit var listAdapter: ListAbilityAdapter
    lateinit var pokemon : PokemonDetailModel

    private lateinit var txt_name: TextView
    private lateinit var ls_ability: ListView

    var id : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txt_name  = findViewById(R.id.txt_name)
        ls_ability = findViewById(R.id.ls_ability)

        id = intent.getIntExtra("id", -1)
        pokemonData = PokemonDataController()

        GetData(this).execute()
    }



    inner class GetData(private val activity: DetailActivity) : AsyncTask<Void, Void, PokemonDetailModel?>() {

        override fun doInBackground(vararg params: Void?): PokemonDetailModel? {
            return pokemonData.getDetailPokemon(id)
        }

        override fun onPostExecute(result: PokemonDetailModel?) {
            super.onPostExecute(result)
            pokemon = result!!

            txt_name.text = pokemon.name
            listAdapter = ListAbilityAdapter(this.activity, R.layout.item_data, pokemon!!.abilities!!)
            ls_ability.adapter = listAdapter
        }
    }
}