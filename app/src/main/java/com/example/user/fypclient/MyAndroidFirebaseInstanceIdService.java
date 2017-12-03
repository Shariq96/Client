package com.example.user.fypclient;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by User on 11/30/2017.
 */

public class MyAndroidFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "MyAndroidFCMIIDService";
    String refreshedToken;
    @Override
    public void onTokenRefresh() {
        //Get hold of the registration token
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Log the token
        Log.d(TAG, "Refreshed token: " + refreshedToken);

    }

    private void sendRegistrationToServer(String Token) {
        //Implement this method if you want to store the token on your server

    }
    public String getToken()
    {
        return refreshedToken;
    }
}
