import { WebPlugin } from '@capacitor/core';
import type { NotificationPermissionStatus, PhoneCallPushNotificationPlugin } from './definitions';
export declare class PhoneCallPushNotificationWeb extends WebPlugin implements PhoneCallPushNotificationPlugin {
    checkNotificationsPermission(): Promise<NotificationPermissionStatus>;
    requestNotificationsPermission(): Promise<NotificationPermissionStatus>;
    getData(): Promise<{
        response: string;
        callId: string;
        timestamp: number;
    }>;
    register(): Promise<void>;
    unregister(): Promise<void>;
}
