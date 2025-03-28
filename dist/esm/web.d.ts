import { WebPlugin } from '@capacitor/core';
import type { PhoneCallPushNotificationPlugin } from './definitions';
export declare class PhoneCallPushNotificationWeb extends WebPlugin implements PhoneCallPushNotificationPlugin {
    checkPermissions(): Promise<PermissionStatus>;
    requestPermissions(): Promise<PermissionStatus>;
    getData(): Promise<{
        response: string;
        callId: string;
        timestamp: number;
    }>;
    register(): Promise<void>;
    unregister(): Promise<void>;
}
