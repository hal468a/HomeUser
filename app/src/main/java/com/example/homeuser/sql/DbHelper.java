package com.example.homeuser.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.homeuser.Health;
import com.example.homeuser.SignIn;
import com.example.homeuser.SignInRecord;
import com.example.homeuser.TempRecord;
import com.example.homeuser.Temperature;
import com.example.homeuser.Users;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2; //current version of the dataabase
    public static final String DATABASE_NAME = "HomeUser.db"; //name of the file you stored the date

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.SQL_CREATE_USERS);
        db.execSQL(DbContract.SQL_CREATE_TEMP);
        db.execSQL(DbContract.SQL_CREATE_HEALTH);
        db.execSQL(DbContract.SQL_CREATE_SIGN_IN_RECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DbContract.SQL_CREATE_USERS);
        db.execSQL(DbContract.SQL_CREATE_TEMP);
        db.execSQL(DbContract.SQL_CREATE_HEALTH);
        db.execSQL(DbContract.SQL_CREATE_SIGN_IN_RECORD);
        onCreate(db);
    }

    //adds User
    public boolean addUser(Users users) {
        if(!isValueExist(users.getPhone())){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.UserEntity.COLUMN_NAME, users.getName());
        cv.put(DbContract.UserEntity.COLUMN_SEX, users.getSex());
        cv.put(DbContract.UserEntity.COLUMN_EMAIL, users.getEmail());
        cv.put(DbContract.UserEntity.COLUMN_PHONE, users.getPhone());
        cv.put(DbContract.UserEntity.COLUMN_PWD, users.getPassword());
        cv.put(DbContract.UserEntity.COLUMN_REGISTER_DATE, users.getRegister_date());
        long l = db.insert(DbContract.UserEntity.TABLE_NAME, null, cv);
        db.close();
        return l != -1;}
        else{
            return false;
        }
    }

    private boolean isValueExist(String phone){
        String query = "SELECT * FROM " + DbContract.UserEntity.TABLE_NAME + " WHERE " + DbContract.UserEntity.COLUMN_PHONE + " = ?";
        String[] whereArgs = {phone};

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, whereArgs);

        int count = cursor.getCount();
        cursor.close();
        return count >= 1;
    }

    //SignIn ListView Data
    public ArrayList<SignIn>getAllData(){
        ArrayList<SignIn>arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT date, status FROM signInRecord", null);

        while (cursor.moveToNext()){
            String date = cursor.getString(0);
            String status = cursor.getString(1);

            SignIn signIn = new SignIn(date, status);

            arrayList.add(signIn);
        }
        return arrayList;
    }

    //TempRecord ListView Data
    public ArrayList<TempRecord>getAllTempData(){
        ArrayList<TempRecord>arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT date, time, temp FROM temperature", null);

        while (cursor.moveToNext()){
            String date = cursor.getString(0);
            String time = cursor.getString(1);
            String temp = cursor.getString(2);

            TempRecord tempRecord = new TempRecord(date, time, temp);

            arrayList.add(tempRecord);
        }
        return arrayList;
    }

    //adds Temp
    public long addTemp(Temperature temp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.TempEntity.COLUMN_DATE, temp.getDate());
        cv.put(DbContract.TempEntity.COLUMN_TIME, temp.getTime());
        cv.put(DbContract.TempEntity.COLUMN_TEMP, temp.getTemp());
        cv.put(DbContract.TempEntity.COLUMN_USER_PHONE, temp.getUser_phone());
        long l = db.insert(DbContract.TempEntity.TABLE_NAME, null, cv);
        db.close();
        return l;
    }

    //get Temp
    public String getTemp(String phone){
        if(phone == null)return null;
        Temperature temp = new Temperature();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT `temp`  FROM temperature WHERE user_phone = ? ORDER BY temp_id DESC LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{phone});//sorting

        String str = "";
        if(cursor.moveToFirst())
            str  =  cursor.getString( cursor.getColumnIndex(DbContract.TempEntity.COLUMN_TEMP) );
        cursor.close();
        return str;
    }

    //adds SignInRecord
    public long addSignInRecord(SignInRecord signInRecord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.SignInRecordEntity.COLUMN_DATE, signInRecord.getDate());
        cv.put(DbContract.SignInRecordEntity.COLUMN_SIGN_IN_STATUS, signInRecord.getSignInStatus());
        cv.put(DbContract.SignInRecordEntity.COLUMN_USER_PHONE, signInRecord.getUser_phone());
        long l = db.insert(DbContract.SignInRecordEntity.TABLE_NAME, null, cv);
        db.close();
        return l;
    }


//    //get UserName
    public String getUserName(String phone){

        Users users = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DbContract.UserEntity.COLUMN_NAME,
                DbContract.UserEntity.COLUMN_SEX,
                DbContract.UserEntity.COLUMN_EMAIL,
                DbContract.UserEntity.COLUMN_PHONE,
                DbContract.UserEntity.COLUMN_PWD,
                DbContract.UserEntity.COLUMN_REGISTER_DATE};

                Cursor cursor = db.query(
                DbContract.UserEntity.TABLE_NAME,  //table name
                projection, //columns we select
                "phone=?", //columns for WHERE clause
                new String[]{phone}, //parameters for where clause
                null, //groupby
                null, //having
                null); //sorting

        if (cursor != null) {
            cursor.moveToFirst();
        }
        users.setName(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_NAME)));
        users.setSex(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_SEX)));
        users.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_EMAIL)));
        users.setPhone(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PHONE)));
        users.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PWD)));
        users.setRegister_date(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_REGISTER_DATE)));
        db.close();
        return users.getName();
    }


    //get UserSex
    public String getUserSex(String phone){

        Users users = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DbContract.UserEntity.COLUMN_NAME,
                DbContract.UserEntity.COLUMN_SEX,
                DbContract.UserEntity.COLUMN_EMAIL,
                DbContract.UserEntity.COLUMN_PHONE,
                DbContract.UserEntity.COLUMN_PWD,
                DbContract.UserEntity.COLUMN_REGISTER_DATE};

        Cursor cursor = db.query(
                DbContract.UserEntity.TABLE_NAME,  //table name
                projection, //columns we select
                "phone=?", //columns for WHERE clause
                new String[]{phone}, //parameters for where clause
                null, //groupby
                null, //having
                null); //sorting

        if (cursor != null) {
            cursor.moveToFirst();
        }
        users.setName(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_NAME)));
        users.setSex(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_SEX)));
        users.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_EMAIL)));
        users.setPhone(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PHONE)));
        users.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PWD)));
        users.setRegister_date(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_REGISTER_DATE)));
        db.close();
        return users.getSex();
    }

    //get UserPwd
    public String getUserPwd(String phone){

        Users users = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DbContract.UserEntity.COLUMN_NAME,
                DbContract.UserEntity.COLUMN_SEX,
                DbContract.UserEntity.COLUMN_EMAIL,
                DbContract.UserEntity.COLUMN_PHONE,
                DbContract.UserEntity.COLUMN_PWD,
                DbContract.UserEntity.COLUMN_REGISTER_DATE};

        Cursor cursor = db.query(
                DbContract.UserEntity.TABLE_NAME,  //table name
                projection, //columns we select
                "phone=?", //columns for WHERE clause
                new String[]{phone}, //parameters for where clause
                null, //groupby
                null, //having
                null); //sorting

        if (cursor != null) {
            cursor.moveToFirst();
        }
        users.setName(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_NAME)));
        users.setSex(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_SEX)));
        users.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_EMAIL)));
        users.setPhone(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PHONE)));
        users.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PWD)));
        users.setRegister_date(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_REGISTER_DATE)));
        return users.getPassword();
    }

    //get UserEmail
    public String getUserEmail(String phone){

        Users users = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                DbContract.UserEntity.COLUMN_NAME,
                DbContract.UserEntity.COLUMN_SEX,
                DbContract.UserEntity.COLUMN_EMAIL,
                DbContract.UserEntity.COLUMN_PHONE,
                DbContract.UserEntity.COLUMN_PWD,
                DbContract.UserEntity.COLUMN_REGISTER_DATE};

        Cursor cursor = db.query(
                DbContract.UserEntity.TABLE_NAME,  //table name
                projection, //columns we select
                "phone=?", //columns for WHERE clause
                new String[]{phone}, //parameters for where clause
                null, //groupby
                null, //having
                null); //sorting

        if (cursor != null) {
            cursor.moveToFirst();
        }
        users.setName(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_NAME)));
        users.setSex(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_SEX)));
        users.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_EMAIL)));
        users.setPhone(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PHONE)));
        users.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PWD)));
        users.setRegister_date(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_REGISTER_DATE)));
        db.close();
        return users.getEmail();
    }

    //get UserRegisterDate
    public String getUserRegisterDate(String phone){
        if(phone == null)return null;
        Users users = new Users();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {

                DbContract.UserEntity.COLUMN_NAME,
                DbContract.UserEntity.COLUMN_SEX,
                DbContract.UserEntity.COLUMN_EMAIL,
                DbContract.UserEntity.COLUMN_PHONE,
                DbContract.UserEntity.COLUMN_PWD,
                DbContract.UserEntity.COLUMN_REGISTER_DATE};

        Cursor cursor = db.query(
                DbContract.UserEntity.TABLE_NAME,  //table name
                projection, //columns we select
                "phone=?", //columns for WHERE clause
                new String[]{phone}, //parameters for where clause
                null, //groupby
                null, //having
                null); //sorting

        if (cursor != null) {
            cursor.moveToFirst();
        }

        users.setName(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_NAME)));
        users.setSex(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_SEX)));
        users.setEmail(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_EMAIL)));
        users.setPhone(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PHONE)));
        users.setPassword(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_PWD)));
        users.setRegister_date(cursor.getString(cursor.getColumnIndex(DbContract.UserEntity.COLUMN_REGISTER_DATE)));
        db.close();
        return users.getRegister_date();
    }


    //adds Health
    public long addHealth(Health health){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DbContract.HealthEntity.COLUMN_DATE, health.getDate());
        cv.put(DbContract.HealthEntity.COLUMN_INFO, health.getHealthInfo());
        cv.put(DbContract.HealthEntity.COLUMN_A, health.getA());
        cv.put(DbContract.HealthEntity.COLUMN_B, health.getB());
        cv.put(DbContract.HealthEntity.COLUMN_C, health.getC());
        cv.put(DbContract.HealthEntity.COLUMN_D, health.getD());
        cv.put(DbContract.HealthEntity.COLUMN_E, health.getE());
        cv.put(DbContract.HealthEntity.COLUMN_F, health.getF());
        cv.put(DbContract.HealthEntity.COLUMN_G, health.getG());
        cv.put(DbContract.HealthEntity.COLUMN_H, health.getH());
        cv.put(DbContract.HealthEntity.COLUMN_I, health.getI());
        cv.put(DbContract.HealthEntity.COLUMN_J, health.getJ());
        cv.put(DbContract.HealthEntity.COLUMN_K, health.getK());
        cv.put(DbContract.HealthEntity.COLUMN_USER_PHONE, health.getUser_phone());
        long addReturn = db.insert(DbContract.HealthEntity.TABLE_NAME,null,cv);
        db.close();
        return addReturn;
    }

    public List<BarEntry> getData() {
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;
        int i = 0;
        int j = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{"A","B","C","D","E","F","G","H","I","J"};
        Cursor cursor = db.query("health", columns, null, null,null, null, null);
        List<BarEntry> dataValues = new ArrayList<>();
        for(int i1 = 0; i1 < cursor.getCount(); i1++) {
            if(cursor.moveToNext()) {
                int counta = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_A);
                int countb = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_B);
                int countc = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_C);
                int countd = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_D);
                int counte = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_E);
                int countf = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_F);
                int countg = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_G);
                int counth = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_H);
                int counti = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_I);
                int countj = cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_J);


                if (counta == 0 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_A)) == 1) {
                    a++;
                }
                if (countb == 1 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_B)) == 1) {
                    b++;
                }
                if (countc == 2 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_C)) == 1) {
                    c++;
                }
                if (countd == 3 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_D)) == 1) {
                    d++;
                }
                if (counte == 4 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_E)) == 1) {
                    e++;
                }
                if (countf == 5 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_F)) == 1) {
                    f++;
                }
                if (countg == 6 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_G)) == 1) {
                    g++;
                }
                if (counth == 7 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_H)) == 1) {
                    h++;
                }
                if (counti == 8 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_I)) == 1) {
                    i++;
                }
                if (countj == 9 && cursor.getInt(cursor.getColumnIndex(DbContract.HealthEntity.COLUMN_J)) == 1) {
                    j++;
                }

            }

        }
        dataValues.add(new BarEntry(0,a));
        dataValues.add(new BarEntry(1,b));
        dataValues.add(new BarEntry(2,c));
        dataValues.add(new BarEntry(3,d));
        dataValues.add(new BarEntry(4,e));
        dataValues.add(new BarEntry(5,f));
        dataValues.add(new BarEntry(6,g));
        dataValues.add(new BarEntry(7,h));
        dataValues.add(new BarEntry(8,i));
        dataValues.add(new BarEntry(9,j));


        cursor.close();
        return  dataValues;
    }



}
