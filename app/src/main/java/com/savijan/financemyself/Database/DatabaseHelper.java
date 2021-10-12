package com.savijan.financemyself.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "udara";
    public static final int DATABASE_VERSION = 1;

    private Dao<UserItemDB, Long> userItemDBS;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, UserItemDB.class);
        }catch (SQLException | java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            if(checkTableExist(database, "item_user")){
                TableUtils.dropTable(connectionSource, UserItemDB.class, false);
                onCreate(database, connectionSource);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    private boolean checkTableExist(SQLiteDatabase database, String tableName){
        Cursor c = null;
        boolean tableExist = false;
        try{
            c = database.query(tableName, null, null, null, null, null, null, null );
            tableExist = true;
        } catch (Exception e){

        }
        return tableExist;
    }

    public Dao<UserItemDB, Long> getUserItemDao() throws SQLException, java.sql.SQLException {
        if(userItemDBS == null){
            userItemDBS = getDao(UserItemDB.class);
        }

        return userItemDBS;
    }

    public void close(){
        userItemDBS = null;
        super.close();
    }


    public void clearTable() throws SQLException, java.sql.SQLException {
        TableUtils.clearTable(getConnectionSource(), UserItemDB.class);
    }
}
