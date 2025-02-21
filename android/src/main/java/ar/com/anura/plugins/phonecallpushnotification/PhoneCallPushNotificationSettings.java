package ar.com.anura.plugins.phonecallpushnotification;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class PhoneCallPushNotificationSettings {
    public static final String PREFERENCES_KEY = "PhoneCallPushNotificationSettingsKey";

    private String icon = "answer";
    private String declineButtonText = "Decline";
    private String declineButtonColor = "#e76565";
    private String answerButtonText = "Answer";
    private String answerButtonColor = "#65bf6c";
    private String color = "#55335A";
    private int duration = 20000;
    private String channelName = "phone-call-push-notification";
    private String channelDescription = "Phone call push notification";

    public PhoneCallPushNotificationSettings(String icon, String declineButtonText, String declineButtonColor, String answerButtonText, String answerButtonColor, String color, int duration, String channelName, String channelDescription) {
        this.icon = icon;
        this.declineButtonText = declineButtonText;
        this.declineButtonColor = declineButtonColor;
        this.answerButtonText = answerButtonText;
        this.answerButtonColor = answerButtonColor;
        this.color = color;
        this.duration = duration;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDeclineButtonText() {
        return declineButtonText;
    }

    public void setDeclineButtonText(String declineButtonText) {
        this.declineButtonText = declineButtonText;
    }

    public String getDeclineButtonColor() {
        return declineButtonColor;
    }

    public void setDeclineButtonColor(String declineButtonColor) {
        this.declineButtonColor = declineButtonColor;
    }

    public String getAnswerButtonText() {
        return answerButtonText;
    }

    public void setAnswerButtonText(String answerButtonText) {
        this.answerButtonText = answerButtonText;
    }

    public String getAnswerButtonColor() {
        return answerButtonColor;
    }

    public void setAnswerButtonColor(String answerButtonColor) {
        this.answerButtonColor = answerButtonColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public static void saveSettings(Context context, PhoneCallPushNotificationSettings settings) {
        SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("icon", Objects.requireNonNullElse(settings.getIcon(), "answer"));
        editor.putString("declineButtonText", Objects.requireNonNullElse(settings.getDeclineButtonText(), "Decline"));
        editor.putString("declineButtonColor", Objects.requireNonNullElse(settings.getDeclineButtonColor(), "#e76565"));
        editor.putString("answerButtonText", Objects.requireNonNullElse(settings.getAnswerButtonText(), "Answer"));
        editor.putString("answerButtonColor", Objects.requireNonNullElse(settings.getAnswerButtonColor(), "#65bf6c"));
        editor.putString("color", Objects.requireNonNullElse(settings.getColor(), "#55335A"));
        editor.putInt("duration", Objects.requireNonNullElse(settings.getDuration(), 20000));
        editor.putString("channelName", Objects.requireNonNullElse(settings.getChannelName(), "phone-call-push-notification"));
        editor.putString("channelDescription", Objects.requireNonNullElse(settings.getChannelDescription(), "Phone call push notifications"));
        editor.apply();
    }

    public static PhoneCallPushNotificationSettings getSettings(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);

        String icon = preferences.getString("icon", "answer");
        String declineButtonText = preferences.getString("declineButtonText", "Decline");
        String declineButtonColor = preferences.getString("declineButtonColor", "#e76565");
        String answerButtonText = preferences.getString("answerButtonText", "Answer");
        String answerButtonColor = preferences.getString("answerButtonColor", "#65bf6c");
        String color = preferences.getString("color", "#55335A");
        int duration = preferences.getInt("duration", 20000);
        String channelName = preferences.getString("channelName", "phone-call-push-notification");
        String channelDescription = preferences.getString("channelDescription", "Phone call push notification");

        return new PhoneCallPushNotificationSettings(icon, declineButtonText, declineButtonColor, answerButtonText, answerButtonColor, color, duration, channelName, channelDescription);
    }

    public static void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("icon", "answer");
        editor.putString("declineButtonText", "Decline");
        editor.putString("declineButtonColor", "#e76565");
        editor.putString("answerButtonText", "Answer");
        editor.putString("answerButtonColor", "#65bf6c");
        editor.putString("color", "#55335A");
        editor.putInt("duration", 20000);
        editor.putString("channelName", "phone-call-push-notification");
        editor.putString("channelDescription", "Phone call push notifications");
        editor.apply();
    }
}
