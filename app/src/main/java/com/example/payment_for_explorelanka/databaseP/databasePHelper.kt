package com.example.payment_for_explorelanka.databaseP

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.payment_for_explorelanka.PayModel.PayListModel

class databasePHelper(context:Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        private val DB_NAME = "paymentDB"
        private val DB_VERSION = 1
        private val table_Name = "taskList"
        private val ID = "id"
        private val Customer_Name = "CustomerName"
        private val Customer_number ="PhoneNumber"
        private val Payment_type = "PaymentType"
        private val booking_type = "Type"
        private val Payment_amount = "Amount"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $table_Name($ID INTEGER PRIMARY KEY, $Customer_Name TEXT, $Customer_number TEXT, $Payment_type TEXT, $booking_type TEXT, $Payment_amount TEXT);"
        p0?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $table_Name"
        p0?.execSQL(DROP_TABLE)
        onCreate(p0)
    }


    fun getAllInfo (): List<PayListModel>{
        val payList = ArrayList<PayListModel>()
        val db = writableDatabase
        val selectQuery =  "SELECT * FROM $table_Name"
        //val cursor = db.rawQuery(selectQuery, null)
        val cursor = db.rawQuery(selectQuery, null)

        if(cursor != null ){
            if(cursor.moveToFirst()){
                do{
                    val payTask = PayListModel()
                    payTask.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
                    payTask.CustomerName = cursor.getString(cursor.getColumnIndexOrThrow(Customer_Name))
                    payTask.CustomerNum = cursor.getString(cursor.getColumnIndexOrThrow(Customer_number))
                    payTask.payType = cursor.getString(cursor.getColumnIndexOrThrow(Payment_type))
                    payTask.bookType = cursor.getString(cursor.getColumnIndexOrThrow(booking_type))
                    payTask.amount = cursor.getString(cursor.getColumnIndexOrThrow(Payment_amount))

                    payList.add(payTask)
                }while(cursor.moveToNext())
            }

        }
        cursor.close()
        return payList
    }

    fun addPayinfo(pays: PayListModel): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Customer_number, pays.CustomerNum)
        values.put(Payment_type, pays.payType)
        values.put(booking_type, pays.bookType)
        values.put(Payment_amount, pays.amount)

        val _success = db.insert(table_Name, null, values)
        db.close()
        return (Integer.parseInt("$_success")!=-1)

    }


    fun getPayinfo(_id: Int):PayListModel{
        val payTask = PayListModel()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $table_Name WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()

        payTask.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ID)))
        payTask.CustomerName = cursor.getString(cursor.getColumnIndexOrThrow(Customer_Name))
        payTask.CustomerNum = cursor.getString(cursor.getColumnIndexOrThrow(Customer_number))
        payTask.payType = cursor.getString(cursor.getColumnIndexOrThrow(Payment_type))
        payTask.bookType = cursor.getString(cursor.getColumnIndexOrThrow(booking_type))
        payTask.amount = cursor.getString(cursor.getColumnIndexOrThrow(Payment_amount))

        cursor.close()
        return payTask
    }

    fun deletePayInfo(_id:Int):Boolean{
        val db = this.writableDatabase
        val _success = db.delete(table_Name, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

    fun updatePayInfo (pays:PayListModel):Boolean{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Customer_Name, pays.CustomerName)
        values.put(Customer_number, pays.CustomerNum)
        values.put(Payment_type, pays.payType)
        values.put(booking_type, pays.bookType)
        values.put(Payment_amount, pays.amount)

        val _success  = db.update(table_Name, values, ID + "=?", arrayOf(pays.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success")!= -1
    }


}