package ar.com.anura.plugins.phonecallpushnotification;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.getcapacitor.Bridge;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginHandle;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

@CapacitorPlugin(
        name = "PhoneCallPushNotification",
        permissions = @Permission(
                strings = { Manifest.permission.POST_NOTIFICATIONS },
                alias = PhoneCallPushNotificationPlugin.PHONE_CALL_PUSH_NOTIFICATIONS_PERMISSION
        )
)

public class PhoneCallPushNotificationPlugin extends Plugin {
    public static Bridge staticBridge = null;

    private static boolean isAppInForeground = false;

    static final String PHONE_CALL_PUSH_NOTIFICATIONS_PERMISSION = "notifications";

    public void load() {
        staticBridge = this.bridge;
    }

    private void onPushNotificationTokenEvent(String token) {
        JSObject res = new JSObject();
        res.put("value", token);
        bridge.triggerWindowJSEvent("onNewToken");
        notifyListeners("onNewToken", res);
    }

    private void onPushNotificationDataEvent(Map<String, String> data) {
        JSObject res = new JSObject();
        res.put("data", data);
        bridge.triggerWindowJSEvent("onNewData");
        notifyListeners("onNewData", res);
    }

    public static void onNewToken(String token) {
        PhoneCallPushNotificationPlugin pushPlugin = PhoneCallPushNotificationPlugin.getPhoneCallNotificationInstance();
        if (pushPlugin != null) {
            pushPlugin.onPushNotificationTokenEvent(token);
        }
    }

    public static void onMessageReceived(RemoteMessage remoteMessage) {
        PhoneCallPushNotificationPlugin pushPlugin = PhoneCallPushNotificationPlugin.getPhoneCallNotificationInstance();
        if (pushPlugin != null ) {
            pushPlugin.onPushNotificationDataEvent(remoteMessage.getData());
        }
    }

    public static PhoneCallPushNotificationPlugin getPhoneCallNotificationInstance() {
        if (staticBridge != null && staticBridge.getWebView() != null) {
            PluginHandle handle = staticBridge.getPlugin("PhoneCallPushNotification");
            if (handle == null) {
                return null;
            }
            return (PhoneCallPushNotificationPlugin) handle.getInstance();
        }
        return null;
    }

    @PluginMethod
    public void checkNotificationsPermission(PluginCall call) {
      notificationPermissionCallback(call);
    }

    @PluginMethod
    public void requestNotificationsPermission(PluginCall call) {
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || getPermissionState(PHONE_CALL_PUSH_NOTIFICATIONS_PERMISSION) == PermissionState.GRANTED) {
        notificationPermissionCallback(call);
      } else {
        requestPermissionForAlias(PHONE_CALL_PUSH_NOTIFICATIONS_PERMISSION, call, "notificationPermissionCallback");
      }
    }

    @PermissionCallback
    private void notificationPermissionCallback(PluginCall call) {
      JSObject permissionsResultJSON = new JSObject();
      permissionsResultJSON.put(PHONE_CALL_PUSH_NOTIFICATIONS_PERMISSION, getPermissionText(PhoneCallPushNotification.areNotificationsEnabled(getContext())));
      call.resolve(permissionsResultJSON);
    }

    private String getPermissionText(boolean enabled) {
      if (enabled) {
        return "granted";
      } else {
        return "denied";
      }
    }

    @PluginMethod
    public void register(PluginCall call) {
        try {
            if (getActivity().isFinishing()) {
                call.reject("Phone call push notification plugin error: App is finishing");
                return;
            }

            PhoneCallPushNotification.registerPushNotifications(getContext(), getSettings(call), this::onPushNotificationTokenEvent);
            call.resolve();
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }

    @PluginMethod
    public void unregister(PluginCall call) {
        try {
            if (getActivity().isFinishing()) {
                call.reject("Phone call notification plugin error: App is finishing");
                return;
            }

            PhoneCallPushNotification.unregisterPushNotifications(getContext());
            call.resolve();
        } catch (Exception exception) {
            call.reject(exception.getMessage());
        }
    }

    @PluginMethod
    public void getData(PluginCall call) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(PhoneCallPushNotificationActivity.PREFERENCES_KEY, Context.MODE_PRIVATE);
        String callId = sharedPreferences.getString("callId", "");
        long timestamp = sharedPreferences.getLong("timestamp", 0);
        String response = sharedPreferences.getString("response", "");

        JSObject res = new JSObject();
        res.put("callId", callId);
        res.put("timestamp", timestamp);
        res.put("response", response);

        call.resolve(res);
    }

    public static boolean isAppInForeground() {
        return isAppInForeground;
    }

    private PhoneCallPushNotificationSettings getSettings(PluginCall call) {
        String icon = call.getString("icon");
        String picture = call.getString("picture");
        String declineButtonText = call.getString("declineButtonText");
        String declineButtonColor = call.getString("declineButtonColor");
        String answerButtonText = call.getString("answerButtonText");
        String answerButtonColor = call.getString("answerButtonColor");
        String color = call.getString("color");
        String channelName = call.getString("channelName");
        String channelDescription = call.getString("channelDescription");
        String callingNameKey = call.getString("callingNameKey");
        String callingNumberKey = call.getString("callingNumberKey");
        String typeKey = call.getString("typeKey");
        String incomingSessionTypeValue = call.getString("incomingSessionTypeValue");
        String notifyTypeValue = call.getString("notifyTypeValue");
        String registrationTypeValue = call.getString("registrationTypeValue");

        return new PhoneCallPushNotificationSettings(icon, picture, declineButtonText, declineButtonColor, answerButtonText, answerButtonColor, color, channelName, channelDescription, callingNameKey, callingNumberKey, typeKey, incomingSessionTypeValue, notifyTypeValue, registrationTypeValue);
    }

    /**
     * Called when the activity will start interacting with the user.
     */
    @Override
    public void handleOnResume() {
        isAppInForeground = true;
    }

    @Override
    public void handleOnPause() {
        isAppInForeground = false;
    }

    /**
     * Called when the activity will be destroyed.
     */
    @Override
    public void handleOnDestroy() {
        isAppInForeground = false;
    }
}
