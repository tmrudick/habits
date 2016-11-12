package com.tomrudick.habits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Receives boot events and starts the registration service.
 */
public class AutoStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("AutoStartReceiver", "Starting up UsageTracker");
        Intent service = new Intent(context, RegistrationService.class);
        context.startService(service);
    }
}