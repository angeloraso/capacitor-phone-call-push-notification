import { WebPlugin } from '@capacitor/core';

import type { PhoneCallPushNotificationPlugin } from './definitions';

export class PhoneCallPushNotificationWeb extends WebPlugin implements PhoneCallPushNotificationPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
