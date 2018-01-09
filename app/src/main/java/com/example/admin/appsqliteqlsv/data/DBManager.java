package com.example.admin.appsqliteqlsv.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.appsqliteqlsv.model.Students;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29/12/2017.
 */

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "student_manager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHONE_NUMBER = "number";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "address";
    public final String sql_Query = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PHONE_NUMBER + " TEXT, " +
            EMAIL + " TEXT, " +
            ADDRESS + " TEXT )" ;
    private Context context;


    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_Query);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void hello(){
        Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
    }
    public void addStudent(Students students){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, students.getName());
        values.put(PHONE_NUMBER, students.getNumber());
        values.put(EMAIL, students.getEmail());
        values.put(ADDRESS, students.getAddress());

        database.insert(TABLE_NAME, null, values);
        database.close();
        Log.d(TAG, "addStudent: ");
    }
    public List<Students> getAllStudents(){
        List<Students> listStudent = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();//cursor de lay kq tra ve
        Cursor cursor = db.rawQuery(selectQuery, null);//k can dieu kien gi het
        if(cursor.moveToFirst()){//kq tra ve co it nhat 1 gt
            do{//chu y cai  vi tri trong cau query
                Students students = new Students();
                students.setId(cursor.getInt(0));
                students.setName(cursor.getString(1));
                students.setNumber(cursor.getString(2));
                students.setEmail(cursor.getString(3));
                students.setAddress(cursor.getString(4));

                listStudent.add(students);


            } while (cursor.moveToNext());//sau kq do con cai kq nao nua khong
        }
        db.close();
        return listStudent;
    }
    public int updateStudent(Students student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getName());
        contentValues.put(PHONE_NUMBER, student.getNumber());
        contentValues.put(EMAIL, student.getEmail());
        contentValues.put(ADDRESS, student.getAddress());
//        return db.update(TABLE_NAME, contentValues, ID +" ="+student.getId(),null);
        int number= db.update(TABLE_NAME, contentValues, ID +" =?",new String[]{String.valueOf(student.getId())});
        if(number>0){
            Log.d(TAG, "updateStudent successfully");
        } else {
            Log.d(TAG, "update fail ");
        }
        return number;
        //co the update thong qua cau lenh sql
    }
    public int deleteStudent(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete(TABLE_NAME, ID+" =?", new String[] {String.valueOf(id)});
    }

}
