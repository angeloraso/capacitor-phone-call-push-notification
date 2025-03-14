package ar.com.anura.plugins.phonecallpushnotification;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Objects;

public class PhoneCallPushNotificationSettings {
    public static final String PREFERENCES_KEY = "PhoneCallPushNotificationSettingsKey";

    public static final String DEFAULT_ICON = "answer";
    public static final String DEFAULT_PICTURE = "picture";
    public static final String DEFAULT_DECLINE_BUTTON_TEXT = "Decline";
    public static final String DEFAULT_DECLINE_BUTTON_COLOR = "#E76565";
    public static final String DEFAULT_ANSWER_BUTTON_TEXT = "Answer";
    public static final String DEFAULT_ANSWER_BUTTON_COLOR = "#65BF6C";
    public static final String DEFAULT_COLOR = "#55335A";
    public static final String DEFAULT_CHANNEL_NAME = "phone-call-push-notification";
    public static final String DEFAULT_CHANNEL_DESCRIPTION = "Phone call push notifications";


    private String icon;
    private String picture;
    private String declineButtonText;
    private String declineButtonColor;
    private String answerButtonText;
    private String answerButtonColor;
    private String color;
    private String channelName;
    private String channelDescription;

    public PhoneCallPushNotificationSettings(String icon, String picture, String declineButtonText, String declineButtonColor, String answerButtonText, String answerButtonColor, String color, String channelName, String channelDescription) {
        this.icon = icon;
        this.picture = picture;
        this.declineButtonText = declineButtonText;
        this.declineButtonColor = declineButtonColor;
        this.answerButtonText = answerButtonText;
        this.answerButtonColor = answerButtonColor;
        this.color = color;
        this.channelName = channelName;
        this.channelDescription = channelDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

        editor.putString("icon", Objects.requireNonNullElse(settings.getIcon(), DEFAULT_ICON));
        editor.putString("picture", Objects.requireNonNullElse(settings.getPicture(), DEFAULT_PICTURE));
        editor.putString("declineButtonText", Objects.requireNonNullElse(settings.getDeclineButtonText(), DEFAULT_DECLINE_BUTTON_TEXT));
        editor.putString("declineButtonColor", Objects.requireNonNullElse(settings.getDeclineButtonColor(), DEFAULT_DECLINE_BUTTON_COLOR));
        editor.putString("answerButtonText", Objects.requireNonNullElse(settings.getAnswerButtonText(), DEFAULT_ANSWER_BUTTON_TEXT));
        editor.putString("answerButtonColor", Objects.requireNonNullElse(settings.getAnswerButtonColor(), DEFAULT_ANSWER_BUTTON_COLOR));
        editor.putString("color", Objects.requireNonNullElse(settings.getColor(), DEFAULT_COLOR));
        editor.putString("channelName", Objects.requireNonNullElse(settings.getChannelName(), DEFAULT_CHANNEL_NAME));
        editor.putString("channelDescription", Objects.requireNonNullElse(settings.getChannelDescription(), DEFAULT_CHANNEL_DESCRIPTION));
        editor.apply();
    }

    public static PhoneCallPushNotificationSettings getSettings(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);

        String icon = preferences.getString("icon", DEFAULT_ICON);
        String picture = preferences.getString("picture", DEFAULT_PICTURE);
        String declineButtonText = preferences.getString("declineButtonText", DEFAULT_DECLINE_BUTTON_TEXT);
        String declineButtonColor = preferences.getString("declineButtonColor", DEFAULT_DECLINE_BUTTON_COLOR);
        String answerButtonText = preferences.getString("answerButtonText", DEFAULT_ANSWER_BUTTON_TEXT);
        String answerButtonColor = preferences.getString("answerButtonColor", DEFAULT_ANSWER_BUTTON_COLOR);
        String color = preferences.getString("color", DEFAULT_COLOR);
        String channelName = preferences.getString("channelName", DEFAULT_CHANNEL_NAME);
        String channelDescription = preferences.getString("channelDescription", DEFAULT_CHANNEL_DESCRIPTION);

        return new PhoneCallPushNotificationSettings(icon, picture, declineButtonText, declineButtonColor, answerButtonText, answerButtonColor, color, channelName, channelDescription);
    }

    public static void clear(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("icon", DEFAULT_ICON);
        editor.putString("picture", DEFAULT_PICTURE);
        editor.putString("declineButtonText", DEFAULT_DECLINE_BUTTON_TEXT);
        editor.putString("declineButtonColor", DEFAULT_DECLINE_BUTTON_COLOR);
        editor.putString("answerButtonText", DEFAULT_ANSWER_BUTTON_TEXT);
        editor.putString("answerButtonColor", DEFAULT_ANSWER_BUTTON_COLOR);
        editor.putString("color", DEFAULT_COLOR);
        editor.putString("channelName", DEFAULT_CHANNEL_NAME);
        editor.putString("channelDescription", DEFAULT_CHANNEL_DESCRIPTION);
        editor.apply();
    }
}
