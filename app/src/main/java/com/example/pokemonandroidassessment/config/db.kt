package com.example.pokemonandroidassessment.config

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.pokemonandroidassessment.model.PokemonModel

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_POKEMON_TABLE = ("CREATE TABLE " + TABLE_POKEMON + "("
                + KEY_ID + " INTEGER PRIMARY KEY," // Kolom ID
                + KEY_NAME + " TEXT,"             // Kolom nama Pokemon
                + KEY_TYPE + " TEXT"              // Kolom jenis Pokemon (contoh: Electric, Grass, dll.)
                + ")")
        db.execSQL(CREATE_POKEMON_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_POKEMON")
        onCreate(db)
    }

    // Metode untuk menambahkan data PokemonModel ke dalam tabel
    fun addPokemon(pokemon: PokemonModel) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, pokemon.name)
        values.put(KEY_TYPE, pokemon.url)
        db.insert(TABLE_POKEMON, null, values)
        db.close()
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PokemonDB"
        private const val TABLE_POKEMON = "pokemon"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_TYPE = "type"
    }
}