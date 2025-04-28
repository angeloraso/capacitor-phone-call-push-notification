import { WebPlugin } from '@capacitor/core';

import type { NotificationPermissionStatus, PhoneCallPushNotificationPlugin } from './definitions';

export class PhoneCallPushNotificationWeb extends WebPlugin implements PhoneCallPushNotificationPlugin {
  async checkNotificationsPermission(): Promise<NotificationPermissionStatus> {
    throw this.unimplemented('Not implemented on web.');
  }
  async requestNotificationsPermission(): Promise<NotificationPermissionStatus> {
    throw this.unimplemented('Not implemented on web.');
  }

  async getData(): Promise<{response: string, callId: string, timestamp: number}> {
    throw this.unimplemented('Not implemented on web.');
  }

  async register(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }

  async unregister(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
}
