import type { PluginListenerHandle } from '@capacitor/core';
export interface NotificationSettings {
    icon: string;
    picture: string;
    declineButtonText: string;
    declineButtonColor: string;
    answerButtonText: string;
    answerButtonColor: string;
    color: string;
    channelName: string;
    channelDescription: string;
    callingNameKey: string;
    callingNumberKey: string;
    typeKey: string;
    incomingSessionTypeValue: string;
    notifyTypeValue: string;
    registrationTypeValue: string;
}
export interface PhoneCallPushNotificationPlugin {
    checkPermissions(): Promise<PermissionStatus>;
    requestPermissions(): Promise<PermissionStatus>;
    getData(): Promise<{
        response: string;
        callId: string;
        timestamp: number;
    }>;
    register(data?: Partial<NotificationSettings>): Promise<void>;
    unregister(): Promise<void>;
    addListener(eventName: 'onNewToken', listenerFunc: (data: {
        value: string;
    }) => void): Promise<PluginListenerHandle>;
    addListener(eventName: 'onNewData', listenerFunc: (data: {
        data: Record<string, string>;
    }) => void): Promise<PluginListenerHandle>;
}
