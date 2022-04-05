package com.example.homeuser.sql;

import android.provider.BaseColumns;

public final class DbContract {

    public static class UserEntity implements BaseColumns {
        public static final String TABLE_NAME = "users"; // name of your table
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SEX = "sex";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_PWD = "password";
        public static final String COLUMN_REGISTER_DATE = "register_date";
    }

    public static final String SQL_CREATE_USERS= "CREATE TABLE "+
            UserEntity.TABLE_NAME+" ( "+
            UserEntity.COLUMN_NAME+" TEXT, "+
            UserEntity.COLUMN_SEX+" TEXT, "+
            UserEntity.COLUMN_EMAIL+" TEXT, "+
            UserEntity.COLUMN_PHONE+" TEXT PRIMARY KEY, "+
            UserEntity.COLUMN_PWD+" TEXT, "+
            UserEntity.COLUMN_REGISTER_DATE+" TEXT ) ";



    public static final String SQL_DROP_USERS =
            "DROP TABLE IF EXISTS "+ UserEntity.TABLE_NAME;

    public static class TempEntity implements BaseColumns {
        public static final String TABLE_NAME = "temperature"; // name of your table
        public static final String COLUMN_ID = "temp_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_TEMP = "temp";
        public static final String COLUMN_USER_PHONE = "user_phone";
    }

    public static final String SQL_CREATE_TEMP= "CREATE TABLE "+
            TempEntity.TABLE_NAME+" ( "+
            TempEntity.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TempEntity.COLUMN_DATE+" TEXT, "+
            TempEntity.COLUMN_TIME+" TEXT, "+
            TempEntity.COLUMN_TEMP+" TEXT, "+
            TempEntity.COLUMN_USER_PHONE+" TEXT, "+
            "FOREIGN KEY ("+ TempEntity.COLUMN_USER_PHONE+") " +
            "REFERENCES "+ UserEntity.TABLE_NAME+" ("+ UserEntity.COLUMN_PHONE+") )";


    public static final String SQL_DROP_TEMP =
            "DROP TABLE IF EXISTS "+ TempEntity.TABLE_NAME;

    public static class HealthEntity implements BaseColumns {
        public static final String TABLE_NAME = "health"; // name of your table
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_A = "A";
        public static final String COLUMN_B = "B";
        public static final String COLUMN_C = "C";
        public static final String COLUMN_D = "D";
        public static final String COLUMN_E = "E";
        public static final String COLUMN_F = "F";
        public static final String COLUMN_G = "G";
        public static final String COLUMN_H = "H";
        public static final String COLUMN_I = "I";
        public static final String COLUMN_J = "J";
        public static final String COLUMN_K = "K";
        public static final String COLUMN_USER_PHONE = "user_phone";
    }

    public static final String SQL_CREATE_HEALTH= "CREATE TABLE "+
            HealthEntity.TABLE_NAME+" ( "+
            HealthEntity.COLUMN_DATE+" TEXT, "+
            HealthEntity.COLUMN_INFO+" TEXT, "+
            HealthEntity.COLUMN_A+" TEXT, "+
            HealthEntity.COLUMN_B+" TEXT, "+
            HealthEntity.COLUMN_C+" TEXT, "+
            HealthEntity.COLUMN_D+" TEXT, "+
            HealthEntity.COLUMN_E+" TEXT, "+
            HealthEntity.COLUMN_F+" TEXT, "+
            HealthEntity.COLUMN_G+" TEXT, "+
            HealthEntity.COLUMN_H+" TEXT, "+
            HealthEntity.COLUMN_I+" TEXT, "+
            HealthEntity.COLUMN_J+" TEXT, "+
            HealthEntity.COLUMN_K+" TEXT, "+
            HealthEntity.COLUMN_USER_PHONE+" TEXT, "+
            "FOREIGN KEY ("+ HealthEntity.COLUMN_USER_PHONE+") " +
            "REFERENCES "+ UserEntity.TABLE_NAME+" ("+ UserEntity.COLUMN_PHONE+") )";

    public static final String SQL_DROP_HEALTH =
            "DROP TABLE IF EXISTS "+ HealthEntity.TABLE_NAME;

    public static class SignInRecordEntity implements BaseColumns {
        public static final String TABLE_NAME = "signInRecord"; // name of your table
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_SIGN_IN_STATUS = "status";
        public static final String COLUMN_USER_PHONE = "user_phone";
    }

    public static final String SQL_CREATE_SIGN_IN_RECORD= "CREATE TABLE "+
            SignInRecordEntity.TABLE_NAME+" ( "+
            SignInRecordEntity.COLUMN_DATE+" TEXT, "+
            SignInRecordEntity.COLUMN_SIGN_IN_STATUS+" TEXT, "+
            SignInRecordEntity.COLUMN_USER_PHONE+" TEXT, "+
            "FOREIGN KEY ("+ SignInRecordEntity.COLUMN_USER_PHONE+") " +
            "REFERENCES "+ UserEntity.TABLE_NAME+" ("+ UserEntity.COLUMN_PHONE+") )";

    public static final String SQL_DROP_SIGN_IN_RECORD =
            "DROP TABLE IF EXISTS "+ SignInRecordEntity.TABLE_NAME;




}
