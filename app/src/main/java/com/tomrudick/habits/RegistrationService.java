package com.tomrudick.habits;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Registers receivers for screen events.
 */
public class RegistrationService extends Service {

    private FirebaseAuth firebaseAuth;

    private static boolean mIsRunning = false;
    private static Object mLock = new Object();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!isRunning()) {
            // Don't start the service if we don't have an account.
            firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser() == null) {
                return START_NOT_STICKY;
            }

            Log.v("RegistrationService", "Starting Registration Framework");
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);

            registerReceiver(new ScreenStateBroadcastReceiver(), filter);

            synchronized (mLock) {
                mIsRunning = true;
            }

            return START_STICKY;
        } else {
            return START_NOT_STICKY;
        }
    }

    public static boolean isRunning() {
        synchronized (mLock) {
            return mIsRunning;
        }
    }

    @Override
    public void onDestroy() {
        synchronized (mLock) {
            mIsRunning = false;
        }
    }
}
