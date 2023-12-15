package com.example.pokemonandroidassessment.controller.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.pokemonandroidassessment.R
import com.example.pokemonandroidassessment.model.AbilitiesItem
import com.example.pokemonandroidassessment.model.FormsItem

class ListFormAdapter(context: Context, private val resource: Int, private val pokemons: List<FormsItem>) :
    ArrayAdapter<FormsItem>(context, resource, pokemons) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {1
        var itemView = convertView
        val holder: ViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(resource, parent, false)
            holder = ViewHolder()
            holder.textViewPokemonName = itemView.findViewById(R.id.txt_item)
            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val pokemon = pokemons[position]
        holder.textViewPokemonName?.text = pokemon!!.name

        return itemView!!
    }

    private class ViewHolder {
        var textViewPokemonName: TextView? = null
    }
}