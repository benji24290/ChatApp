package ch.juventus.bs.firebaseinstachat;

import android.util.Log;

/**
 * Created by root on 30.10.16.
 */

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService {

    private static final String TAG = "FMService";

    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle data payload of FCM messages.
        Log.d(TAG, "FCM Message Id: " + remoteMessage.getMessageId());
        Log.d(TAG, "FCM Notification Message: " + remoteMessage.getNotification());
        Log.d(TAG, "FCM Data Message: " + remoteMessage.getData());
    }

}
