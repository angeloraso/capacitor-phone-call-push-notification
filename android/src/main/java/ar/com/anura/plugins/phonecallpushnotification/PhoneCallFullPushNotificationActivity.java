package ar.com.anura.plugins.phonecallpushnotification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneCallFullPushNotificationActivity extends AppCompatActivity {
  private static final String TAG = "PhoneCallFullPushNotificationActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Log.d(TAG, "Phone call full push notification activity");

    setShowWhenLocked(true);
    setTurnScreenOn(true);

    Intent intent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName());
    if (intent != null) {
      Log.d(TAG, "Open main activity");
      getApplicationContext().startActivity(intent);
    }

    Log.d(TAG, "Finish activity");
    finish();
  }
}
