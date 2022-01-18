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
            + COLUMN_PILL_DAY + " TEXT" + ")" )
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

    fun findPill(pillName: String): Pill? { val query =
        "SELECT * FROM $TABLE_PILLS WHERE $COLUMN_PILL_NAME = \"$pillName\""
        val database = this.writableDatabase
        val cursor = database.rawQuery(query, null)
        var pill: Pill? = null

        if (cursor.moveToFirst()) { cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val pillName = cursor.getString(1)
            val pill_quantity = Integer.parseInt(cursor.getString(2))
            val date = cursor.getString(3)
            pill = Pill(id, pillName, pill_quantity, date)
            cursor.close()
        }
        database.close()
        return pill
    }

    fun deletePill(pillName: String): Boolean { var result = false
        val query =
            "SELECT * FROM $TABLE_PILLS WHERE $COLUMN_PILL_NAME = \"$pillName\""
        val database = this.writableDatabase
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            database.delete(
                TABLE_PILLS, COLUMN_ID + " = ?",
            arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        database.close()
        return result
    }

}