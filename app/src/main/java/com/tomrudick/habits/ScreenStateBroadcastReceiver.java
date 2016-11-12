package com.tomrudick.habits;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Handles events when the screen turns on or off.
 */
public class ScreenStateBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.v("ScreenStateReceiver", "SCREEN_ON");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.v("ScreenStateReceiver", "SCREEN_OFF");
        }
    }
}
