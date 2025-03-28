import { registerPlugin } from '@capacitor/core';
const PhoneCallPushNotification = registerPlugin('PhoneCallPushNotification', {
    web: () => import('./web').then((m) => new m.PhoneCallPushNotificationWeb()),
});
export * from './definitions';
export { PhoneCallPushNotification };
//# sourceMappingURL=index.js.map