package ar.com.anura.plugins.phonecallpushnotification;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

public class PhoneCallPushNotificationService extends FirebaseMessagingService {
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    private static final String TAG = "PhoneCallPushNotificationService";
    private static final long REMOVE_NOTIFICATION_AFTER_DELAY = 90000;

    @Override
    public void onNewToken(@NonNull final String token) {
      super.onNewToken(token);
      Log.d(TAG, "Refreshed token: " + token);
      if (PhoneCallPushNotificationPlugin.isAppInForeground()) {
        Runnable runnable = () -> PhoneCallPushNotificationPlugin.onNewToken(token);
        dispatchOnUIThread(runnable);
      }
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      super.onMessageReceived(remoteMessage);
      Log.d(TAG, "Remote message received: " + remoteMessageToString(remoteMessage));
      Runnable runnable;
      if (PhoneCallPushNotificationPlugin.isAppInForeground()) {
        runnable = () -> PhoneCallPushNotificationPlugin.onMessageReceived(remoteMessage);
      } else {
        runnable = () -> {
          showPhoneCallNotification(getApplicationContext(), remoteMessage);
        };
      }
      dispatchOnUIThread(runnable);
    }

  private void showPhoneCallNotification(Context context, RemoteMessage remoteMessage) {
    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

    if (!notificationManager.areNotificationsEnabled()) {
      return;
    }

    PhoneCallPushNotificationSettings settings = PhoneCallPushNotificationSettings.getSettings(context);
    Map<String, String> data = remoteMessage.getData();

    String type = data.get(settings.getTypeKey());

    if (!Objects.equals(type, settings.getIncomingSessionTypeValue())) {
      return;
    }


    String callingName = data.get(settings.getCallingNameKey());
    if (callingName != null && callingName.startsWith("\"") && callingName.endsWith("\"")) {
      callingName = callingName.substring(1, callingName.length() - 1);
    }
    Log.d(TAG, "Calling name key: " + settings.getCallingNameKey());
    Log.d(TAG, "Calling name: " + callingName);

    String callingNumber = data.get(settings.getCallingNumberKey());
    if (callingNumber != null && callingNumber.startsWith("\"") && callingNumber.endsWith("\"")) {
      callingNumber = callingNumber.substring(1, callingNumber.length() - 1);
    }

    Log.d(TAG, "Calling number key: " + settings.getCallingNumberKey());
    Log.d(TAG, "Calling number: " + callingNumber);

    String callId = data.get("call-id");
    long timestamp = System.currentTimeMillis();

    final NotificationChannel notificationChannel = getNotificationChannel(settings.getChannelName(), settings);
    notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, null);
    notificationManager.createNotificationChannel(notificationChannel);

    String TAP_ACTION = "tap";
    String DECLINE_ACTION = "decline";
    String ANSWER_ACTION = "answer";
    int NOTIFICATION_ID = 1740164950;

    Notification.Builder notificationBuilder = new Notification.Builder(this, settings.getChannelName())
      .setContentTitle(settings.getChannelName())
      .setTicker(settings.getChannelName())
      .setCategory(Notification.CATEGORY_CALL)
      .setWhen(System.currentTimeMillis())
      .setVisibility(Notification.VISIBILITY_PUBLIC)
      .setAutoCancel(true)
      .setContentIntent(getPendingIntent(context, NOTIFICATION_ID, TAP_ACTION, callId, timestamp))
      .setColor(Color.parseColor(settings.getColor()))
      .setLocalOnly(true);

    Intent intent = new Intent(context, PhoneCallFullPushNotificationActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(
      context,
      0,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
    );
    notificationBuilder.setFullScreenIntent(pendingIntent, true);


    String iconName = settings.getIcon();
    int iconResource = getIconResId(iconName);
    // If no icon at all was found, fall back to the app's icon
    if (iconResource == 0) {
      iconResource = getApplicationContext().getApplicationInfo().icon;
    }

    String pictureName = settings.getPicture();
    int pictureResource = getIconResId(pictureName);
    // If no icon at all was found, fall back to the app's icon
    if (pictureResource == 0) {
        pictureResource = getApplicationContext().getApplicationInfo().icon;
    }

    // Android 12+
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      Icon icon = Icon.createWithResource(this, pictureResource);
      Person caller = new Person.Builder()
        .setIcon(icon)
        .setName(callingName  + " - " + callingNumber)
        .setImportant(true)
        .build();

      Notification.CallStyle notificationStyle;
      notificationStyle =
        Notification.CallStyle.forIncomingCall(caller, getPendingIntent(context, NOTIFICATION_ID, DECLINE_ACTION, callId, timestamp), getPendingIntent(context, NOTIFICATION_ID, ANSWER_ACTION, callId, timestamp));

      notificationStyle.setAnswerButtonColorHint(Color.parseColor(settings.getAnswerButtonColor()));
      notificationStyle.setDeclineButtonColorHint(Color.parseColor(settings.getDeclineButtonColor()));
      notificationBuilder.setStyle((notificationStyle));
      notificationBuilder.setSmallIcon(getIconResId("answer", "drawable"));
      notificationBuilder.setForegroundServiceBehavior(Notification.FOREGROUND_SERVICE_IMMEDIATE);
    } else {
      notificationBuilder.setSmallIcon(iconResource);
      notificationBuilder.setContentText(callingName + " - " + callingNumber);

      Notification.Action declineAction = new Notification.Action.Builder(
        Icon.createWithResource(this, getIconResId("decline", "drawable")),
        Html.fromHtml(
          "<font color=\"" +
            Color.parseColor(settings.getDeclineButtonColor()) +
            "\">" +
            settings.getDeclineButtonText() +
            "</font>",
          Html.FROM_HTML_MODE_LEGACY
        ),
        getPendingIntent(context, NOTIFICATION_ID, DECLINE_ACTION, callId, timestamp)
      ).build();

      Notification.Action answerAction = new Notification.Action.Builder(
        Icon.createWithResource(this, getIconResId("answer", "drawable")),
        Html.fromHtml(
          "<font color=\"" +
            Color.parseColor(settings.getAnswerButtonColor()) +
            "\">" +
            settings.getAnswerButtonText() +
            "</font>",
          Html.FROM_HTML_MODE_LEGACY
        ),
        getPendingIntent(context, NOTIFICATION_ID, ANSWER_ACTION, callId, timestamp)
      ).build();

      notificationBuilder.setActions(declineAction, answerAction);
    }

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
      return;
    }

    Notification notification = notificationBuilder.build();
    notificationManager.notify(NOTIFICATION_ID, notification);
    removeNotificationAfterDelay(context, NOTIFICATION_ID, REMOVE_NOTIFICATION_AFTER_DELAY);
  }

  @NonNull
  private static NotificationChannel getNotificationChannel(String CHANNEL_ID, PhoneCallPushNotificationSettings settings) {
    final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    final long[] DEFAULT_VIBRATE_PATTERN = { 0, 250, 250, 250 };

    final NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, settings.getChannelName(), CHANNEL_IMPORTANCE);
    notificationChannel.setDescription(settings.getChannelDescription());
    notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
    notificationChannel.enableVibration(true);
    notificationChannel.setVibrationPattern(DEFAULT_VIBRATE_PATTERN);
    return notificationChannel;
  }

  private PendingIntent getPendingIntent(Context context, int notificationId, String action, String callId, long timestamp) {
    Intent intent = new Intent(context, PhoneCallPushNotificationActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    intent.putExtra("notificationId", notificationId);
    intent.putExtra("timestamp", timestamp);
    intent.putExtra("callId", callId);
    intent.setAction(action);

    return PendingIntent.getActivity(
      context,
      0,
      intent,
      PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
    );
  }

  private int getIconResId(String name) {
    int resId = getIconResId(name, "mipmap");

    if (resId == 0) {
      resId = getIconResId(name, "drawable");
    }

    if (resId == 0) {
      resId = getIconResId("icon", "mipmap");
    }

    if (resId == 0) {
      resId = getIconResId("icon", "drawable");
    }

    return resId;
  }

  private int getIconResId(String icon, String type) {
    Resources res = getResources();
    String pkgName = getPackageName();

    return res.getIdentifier(icon, type, pkgName);
  }

  private static void removeNotificationAfterDelay(Context context, int notificationId, long delay) {
    dispatchOnUIThreadDelayed(() -> {
      NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      if (notificationManager != null) {
        notificationManager.cancel(notificationId);
      }
    }, delay);
  }

  private static void dispatchOnUIThread(Runnable r) {
    sHandler.post(r);
  }

  private static void dispatchOnUIThreadDelayed(Runnable r, long delay) {
    sHandler.postDelayed(r, delay);
  }

  private String remoteMessageToString(RemoteMessage remoteMessage) {
    StringBuilder builder = new StringBuilder();
    builder.append("From [");
    builder.append(remoteMessage.getFrom());
    builder.append("], Message Id [");
    builder.append(remoteMessage.getMessageId());
    builder.append("], TTL [");
    builder.append(remoteMessage.getTtl());
    builder.append("], Original Priority [");
    builder.append(remoteMessage.getOriginalPriority());
    builder.append("], Received Priority [");
    builder.append(remoteMessage.getPriority());
    builder.append("], Sent Time [");
    builder.append(remoteMessage.getSentTime());
    builder.append("], Data [");
    builder.append(new JSONObject(remoteMessage.getData()));
    builder.append("]");
    return builder.toString();
  }
}
