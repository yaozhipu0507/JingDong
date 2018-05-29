package com.example.jingdong;

import android.app.Application;
import android.content.Context;

import com.example.jingdong.Sou.db.DaoMaster;
import com.example.jingdong.Sou.db.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

public class MyApp extends Application {

    private static Context mContext;
    private DaoSession daoSession;
    public static final boolean ENCRYPTED = true;

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        mContext=getApplicationContext();

        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, ENCRYPTED ? "users_select" : "users_select-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;

    }

    public static Context getContext() {
        return mContext;
    }
}
