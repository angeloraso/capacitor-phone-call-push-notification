package ar.com.anura.plugins.phonecallpushnotification;

import android.content.Context;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;
import java.util.function.Consumer;

public class PhoneCallPushNotification {
    private static final String TAG = "PhoneCallPushNotification";

    public static void registerPushNotifications(Context context, PhoneCallPushNotificationSettings settings, Consumer<String> onPushNotificationTokenEvent) {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging
                .getInstance()
                .getToken()
                .addOnCompleteListener(
                        task -> {
                            if (!task.isSuccessful()) {
                                try {
                                    throw new Exception(Objects.requireNonNull(task.getException()).getLocalizedMessage());
                                } catch (Exception e) {
                                    Log.d(TAG, "registerPushNotifications error: " + e.getMessage());
                                    throw new RuntimeException(e);
                                }
                            }

                            Log.d(TAG, "FCM remote message: " + task.getResult());
                            PhoneCallPushNotificationSettings.saveSettings(context, settings);
                            onPushNotificationTokenEvent.accept(task.getResult());
                        }
                );
    }

    public static boolean areNotificationsEnabled(Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context.getApplicationContext());
        return notificationManager.areNotificationsEnabled();
    }

    public static void unregisterPushNotifications(Context context) {
        try {
            PhoneCallPushNotificationSettings.clear(context);
            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
            FirebaseMessaging.getInstance().deleteToken();
        } catch (Exception e) {
            Log.d(TAG, "unregisterPushNotifications error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
