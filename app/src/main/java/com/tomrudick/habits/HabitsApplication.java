package com.tomrudick.habits;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Application class for Habits.
 */
public class HabitsApplication extends Application {

    private final Object firebaseLock = new Object();

    private FirebaseApp firebaseApp;

    public FirebaseApp getFirebaseApp() {
        synchronized (firebaseLock) {
            if (firebaseApp == null) {
                firebaseApp = FirebaseApp.initializeApp(getApplicationContext());
            }

            return firebaseApp;
        }
    }
}
