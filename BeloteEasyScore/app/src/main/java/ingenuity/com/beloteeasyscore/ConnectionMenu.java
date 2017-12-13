package ingenuity.com.beloteeasyscore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnectionMenu extends Activity {

    private static final String LogTag = "BeloteEasyScore";
    private static final String SubLogTag = "ConnectionMenu: ";
    private Context mContext = null;
    private Activity mActivity = null;


    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    //Facebook login button
    private FacebookCallback<LoginResult> callback = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LogTag, SubLogTag +"onCreate Called");
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = (Activity) mContext;
        setContentView(R.layout.activity_connection_menu);

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
                Log.d(LogTag, SubLogTag +"onCurrentAccessTokenChanged Called");
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                Log.d(LogTag, SubLogTag +"onCurrentProfileChanged Called");
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(LogTag, SubLogTag +"callback2 :: onSuccess Called");
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                if(profile == null) {
                    profileTracker.startTracking();
                }
                else {
                    nextActivity(profile);
                }
                Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();    }

            @Override
            public void onCancel() {
                Log.d(LogTag, SubLogTag +"callback2 :: onCancel Called");
            }

            @Override
            public void onError(FacebookException e) {
                Log.d(LogTag, SubLogTag +"callback2 :: onError Called");
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LogTag, SubLogTag +"onResume Called");
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    protected void onPause() {
        Log.d(LogTag, SubLogTag +"onPause Called");
        super.onPause();
    }

    protected void onStop() {
        Log.d(LogTag, SubLogTag +"onStop Called");
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        Log.d(LogTag, SubLogTag +"onActivityResult Called");
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);
    }

    private void nextActivity(Profile profile){
        Log.d(LogTag, SubLogTag +"nextActivity Called");
        if(profile != null){
            Log.d(LogTag, SubLogTag +"nextActivity :: profile != null");
            Intent main = new Intent(mActivity, ScoreActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
        else Log.d(LogTag, SubLogTag +"nextActivity :: profile == null");
    }

}
