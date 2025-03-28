import { WebPlugin } from '@capacitor/core';
export class PhoneCallPushNotificationWeb extends WebPlugin {
    async checkPermissions() {
        throw this.unimplemented('Not implemented on web.');
    }
    async requestPermissions() {
        throw this.unimplemented('Not implemented on web.');
    }
    async getData() {
        throw this.unimplemented('Not implemented on web.');
    }
    async register() {
        throw this.unimplemented('Not implemented on web.');
    }
    async unregister() {
        throw this.unimplemented('Not implemented on web.');
    }
}
//# sourceMappingURL=web.js.map