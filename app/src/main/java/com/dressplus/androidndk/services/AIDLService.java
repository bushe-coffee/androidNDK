package com.dressplus.androidndk.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.dressplus.androidndk.IMyAidlInterface;

public class AIDLService extends Service {


    public final IMyAidlInterface.Stub mbinder = new IMyAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
        }

        @Override
        public int testAIDL() throws RemoteException {
            return testAIDLFunction();
        }

        @Override
        public int getPID() throws RemoteException {
            return 0;
        }
    };

    public AIDLService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder.asBinder();
    }

    private int testAIDLFunction() {
        Log.e("yangxinyu", "testAIDLFunction  " + android.os.Process.myPid());
        return 101;
    }
}
