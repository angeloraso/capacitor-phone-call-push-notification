'use strict';

var core = require('@capacitor/core');

const PhoneCallPushNotification = core.registerPlugin('PhoneCallPushNotification', {
    web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.PhoneCallPushNotificationWeb()),
});

class PhoneCallPushNotificationWeb extends core.WebPlugin {
    async checkNotificationsPermission() {
        throw this.unimplemented('Not implemented on web.');
    }
    async requestNotificationsPermission() {
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

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    PhoneCallPushNotificationWeb: PhoneCallPushNotificationWeb
});

exports.PhoneCallPushNotification = PhoneCallPushNotification;
//# sourceMappingURL=plugin.cjs.js.map
