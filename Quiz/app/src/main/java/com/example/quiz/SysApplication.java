package com.example.quiz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;

public class SysApplication extends Application {
    private ArrayList<Activity> arrayList = new ArrayList<Activity>();
    private static SysApplication instance;

    private SysApplication() {
    }
    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }
    public void addActivity(Activity activity) {
        arrayList.add(activity);
    }

    public void exit() {
        try {

            for (int i = 0;i < arrayList.size(); i++)
            {
                if (arrayList.get(i)!=null)
                {
                    arrayList.get(i).finish();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}

