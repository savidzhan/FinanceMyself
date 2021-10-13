package com.savijan.financemyself.Database;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DatabaseManager {

    private final String TAG = DatabaseManager.this.getClass().getSimpleName();
    private final Context mContext;
    public static DatabaseManager INSTANCE;
    private DatabaseHelper databaseHelper;

    private Dao<UserItemDB, Long> userItemDao;
    private static String INDEX = "index";
    private static String NAME = "name";
    private static String AGE = "age";
    private static String ID = "id";

    public DatabaseManager(Context mContext) {
        Log.i(TAG, "DatabaseManager");
        this.mContext = mContext;
        databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);

        try{
            userItemDao = databaseHelper.getUserItemDao();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(Context context){
        if(INSTANCE == null) INSTANCE = new DatabaseManager(context);
        return INSTANCE;
    }

    public void releaseDB(){
        if(databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
        }
    }

    public int clearAllData(){
        try{
            if(databaseHelper == null) return -1;
            databaseHelper.clearTable();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    

}
