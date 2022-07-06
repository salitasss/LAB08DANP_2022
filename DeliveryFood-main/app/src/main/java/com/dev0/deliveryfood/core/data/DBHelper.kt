package com.dev0.deliveryfood.core.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE food(" +
                "   id INTEGER PRIMARY KEY," +
                "   name TEXT," +
                "   description TEXT," +
                "   price DOUBLE," +
                "   image TEXT," +
                "   restaurant INTEGER," +
                "   qualification TEXT" +
                ");")
        db.execSQL("CREATE TABLE restaurant(" +
                "   id INTEGER PRIMARY KEY," +
                "   name TEXT," +
                "   description TEXT," +
                "   district TEXT," +
                "   restaurant INTEGER," +
                "   image TEXT" +
                ");")
        db.execSQL("CREATE TABLE orders(" +
                "   id INTEGER PRIMARY KEY," +
                "   detail TEXT," +
                "   date TEXT," +
                "   user TEXT," +
                "   status INTEGER" +
                ");")
        db.execSQL("CREATE TABLE orders_detail(" +
                "   id INTEGER PRIMARY KEY," +
                "   ordered INTEGER," +
                "   restaurant INTEGER," +
                "   food INTEGER," +
                "   quantity INTEGER," +
                "   total DOUBLE" +
                ");")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS food")
        db.execSQL("DROP TABLE IF EXISTS restaurant")
        db.execSQL("DROP TABLE IF EXISTS orders_detail")
        db.execSQL("DROP TABLE IF EXISTS orders")
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "delivery_db"
    }
}