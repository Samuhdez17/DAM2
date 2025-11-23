package com.example.buscaminas

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NOMBRE + " TEXT, "
                + FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP)"
                )

        db.execSQL(query)
    }

    fun aniadirPersona(
        nombre: String?,
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NOMBRE, nombre)

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun leerPersonas(): ArrayList<String> {
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $FECHA DESC", null)

        val listaGanadores: ArrayList<String> = ArrayList()

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(NOMBRE))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow(FECHA))
                listaGanadores.add("$nombre - $fecha")
            } while (cursor.moveToNext())
        }

        cursor.close()
        return listaGanadores
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "ganadores"

        private const val DB_VERSION = 1

        private const val TABLE_NAME = "persona"

        private const val ID = "id"

        private const val NOMBRE = "nombre"

        private const val FECHA = "fecha_hora"
    }
}