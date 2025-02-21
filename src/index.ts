import { registerPlugin } from '@capacitor/core';

import type { PhoneCallPushNotificationPlugin } from './definitions';

const PhoneCallPushNotification = registerPlugin<PhoneCallPushNotificationPlugin>('PhoneCallPushNotification', {
  web: () => import('./web').then((m) => new m.PhoneCallPushNotificationWeb()),
});

export * from './definitions';
export { PhoneCallPushNotification };
