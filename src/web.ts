import { WebPlugin } from '@capacitor/core';

import type { PhoneCallPushNotificationPlugin } from './definitions';

export class PhoneCallPushNotificationWeb extends WebPlugin implements PhoneCallPushNotificationPlugin {
  async checkPermissions(): Promise<PermissionStatus> {
    throw this.unimplemented('Not implemented on web.');
  }
  
  async requestPermissions(): Promise<PermissionStatus> {
    throw this.unimplemented('Not implemented on web.');
  }

  async getData(): Promise<{response: string, callId: string}> {
    throw this.unimplemented('Not implemented on web.');
  }

  async register(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }

  async unregister(): Promise<void> {
    throw this.unimplemented('Not implemented on web.');
  }
}
