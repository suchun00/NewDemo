package com.sc.utils;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.sc.greendao.dao.DaoMaster;
import com.sc.greendao.dao.DaoSession;

/**
 * Created by suchun on 2017/7/24.
 */
public class App extends Application{
    private DaoMaster.DevOpenHelper dbHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initDatabase();
    }
    public static App getInstance(){
        return instance;
    }

    private void initDatabase() {
        dbHelper = new DaoMaster.DevOpenHelper(this, "NET_ADDRESS", null);
        db = dbHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }
    public DaoSession getSession(){
        return mDaoSession;
    }
    public SQLiteDatabase getDb(){
        return db;
    }

    private void init() {
        instance = this;
    }
}
