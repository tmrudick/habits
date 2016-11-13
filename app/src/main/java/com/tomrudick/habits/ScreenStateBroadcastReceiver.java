package com.tomrudick.habits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Handles events when the screen turns on or off.
 */
public class ScreenStateBroadcastReceiver extends BroadcastReceiver {

    private static final String SCREEN_EVENTS_CHILD = "screen-events";

    private DatabaseReference firebaseDatabaseReference;

    @Override
    public void onReceive(Context context, Intent intent) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            return;
        }

        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.v("ScreenStateReceiver", "SCREEN_ON");
            firebaseDatabaseReference
                    .child(SCREEN_EVENTS_CHILD)
                    .child(user.getUid())
                    .push()
                    .setValue(new ScreenEvent("on"));
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v("ScreenStateReceiver", "SCREEN_OFF");
            firebaseDatabaseReference
                    .child(SCREEN_EVENTS_CHILD)
                    .child(user.getUid())
                    .push()
                    .setValue(new ScreenEvent("off"));
        }
    }
}
