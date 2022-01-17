package com.example.pillservice

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        var DATABASE_VERSION = 1
        var DATABASE_NAME = "pillsDataBase.db"
        val TABLE_PILLS = "pills"
        val COLUMN_ID = "pill_id"
        val COLUMN_PILL_NAME = "pill_name"
        val COLUMN_PILL_QUANTITY = "pill_quantity"
        val COLUMN_PILL_DAY = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PILLS_TABLE = ("CREATE TABLE " +
            TABLE_PILLS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_PILL_NAME + " TEXT,"
            + COLUMN_PILL_QUANTITY + " INTEGER,"
            + COLUMN_PILL_DAY + " TEXT," + ")" )
        db.execSQL(CREATE_PILLS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PILLS)
        onCreate(db)
    }

    fun addPill(pill: Pill) {
        val data = ContentValues()
        data.put(COLUMN_PILL_NAME, pill.namePill)
        data.put(COLUMN_PILL_QUANTITY, pill.quantity)
        data.put(COLUMN_PILL_DAY, pill.datePill)
        val database = this.writableDatabase
        database.insert(TABLE_PILLS, null, data)
        database.close()
    }
}