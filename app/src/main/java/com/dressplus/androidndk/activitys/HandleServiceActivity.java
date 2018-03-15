package com.dressplus.androidndk.activitys;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dressplus.androidndk.IMyAidlInterface;
import com.dressplus.androidndk.R;
import com.dressplus.androidndk.services.AIDLService;
import com.dressplus.androidndk.services.CustomerBinderService;

public class HandleServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_service);
    }


    public void showImageTop(View view) {
        Intent intent = new Intent(this, CustomerBinderService.class);
        bindService(intent,connection, Context.BIND_AUTO_CREATE);
    }

    public void AIDLButton(View view) {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent,connection2, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("yangxinyu  ","onServiceConnected");
            final CustomerBinderService.LocalBinder localBinder = (CustomerBinderService.LocalBinder) iBinder;
            localBinder.createView();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("yangxinyu  ","onServiceDisconnected");
        }
    };

    private ServiceConnection connection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMyAidlInterface aidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                aidlInterface.getPID();
                aidlInterface.testAIDL();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("yangxinyu  ","onServiceDisconnected");
        }
    };

}
