package com.example.pokemonandroidassessment.controller.page

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.pokemonandroidassessment.R
import com.example.pokemonandroidassessment.controller.adapter.ListAbilityAdapter
import com.example.pokemonandroidassessment.controller.adapter.ListFormAdapter
import com.example.pokemonandroidassessment.controller.data.PokemonDataController
import com.example.pokemonandroidassessment.model.PokemonDetailModel

class DetailActivity : AppCompatActivity() {
    private lateinit var pokemonData : PokemonDataController;
    private lateinit var listAdapter: ListAbilityAdapter;
    private lateinit var listFormAdapter: ListFormAdapter
    lateinit var pokemon : PokemonDetailModel

    private lateinit var txt_name: TextView
    private lateinit var img_profile: ImageView
    private lateinit var ls_ability: ListView
    private lateinit var ls_form: ListView

    var id : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txt_name  = findViewById(R.id.txt_name)
        img_profile  = findViewById(R.id.img_profile)
        ls_ability = findViewById(R.id.ls_ability)
        ls_form = findViewById(R.id.ls_form)

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
            txt_name.text = pokemon.name//listFormAdapter

            listAdapter = ListAbilityAdapter(this.activity, R.layout.item_data, pokemon!!.abilities!!)
            ls_ability.adapter = listAdapter

            listFormAdapter = ListFormAdapter(this.activity, R.layout.item_data, pokemon!!.forms!!)
            ls_form.adapter = listFormAdapter

            Glide.with(activity)
                .load(pokemon.sprites!!.frontDefault)
                .into(img_profile)
        }
    }
}