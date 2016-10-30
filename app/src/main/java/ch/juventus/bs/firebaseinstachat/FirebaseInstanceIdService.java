package ch.juventus.bs.firebaseinstachat;

/**
 * Created by root on 30.10.16.
 */

public class FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIIDService";
    private static final String FRIENDLY_ENGAGE_TOPIC = "engage";

    /**
     * The Application's current Instance ID token is no longer valid and thus a new one must be requested.
     */
    public void onTokenRefresh() {
    }

}