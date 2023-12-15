package com.example.pokemonandroidassessment.controller.page

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ListView
import com.example.pokemonandroidassessment.R
import com.example.pokemonandroidassessment.config.DatabaseHelper
import com.example.pokemonandroidassessment.controller.adapter.ListAdapter
import com.example.pokemonandroidassessment.controller.data.PokemonDataController
import com.example.pokemonandroidassessment.model.PokemonModel

class HomeActivity : AppCompatActivity() {
    private lateinit var pokemonData : PokemonDataController;
    private lateinit var listAdapter: ListAdapter
    lateinit var listData : List<PokemonModel>

    private lateinit var txt_search: EditText
    private lateinit var ls_data: ListView

    var searchData = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txt_search  = findViewById(R.id.txt_search)
        ls_data = findViewById(R.id.ls_data)

        pokemonData = PokemonDataController()
        GetData(this).execute()

        txt_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                searchData = txt_search.text.toString()

                listAdapter = ListAdapter(this, R.layout.item_data, dataList())
                ls_data.adapter = listAdapter

                return@setOnEditorActionListener true
            }
            false
        }

        ls_data.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", position + 1)
            startActivity(intent)
        }
    }

    fun dataList() : List<PokemonModel> {
        return if (searchData.isNotEmpty()) {
            var data = listData
                .filter { e -> e.name!!.contains(searchData, ignoreCase = true) || e.name!! == searchData }
            data
        } else {
            listData
        }
    }

    inner class GetData(private val activity: HomeActivity) : AsyncTask<Void, Void, List<PokemonModel>?>() {

        override fun doInBackground(vararg params: Void?): List<PokemonModel>? {
            return pokemonData.getListPokemon()
        }

        override fun onPostExecute(result: List<PokemonModel>?) {
            super.onPostExecute(result)
            val db = DatabaseHelper(this.activity)

            listData = result!!

            listAdapter = ListAdapter(this.activity, R.layout.item_data, dataList())
            ls_data.adapter = listAdapter

            listData.forEach { pokemon ->
                db.addPokemon(pokemon)
            }
        }
    }
}