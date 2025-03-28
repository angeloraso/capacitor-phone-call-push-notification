var capacitorPhoneCallPushNotification = (function (exports, core) {
    'use strict';

    const PhoneCallPushNotification = core.registerPlugin('PhoneCallPushNotification', {
        web: () => Promise.resolve().then(function () { return web; }).then((m) => new m.PhoneCallPushNotificationWeb()),
    });

    class PhoneCallPushNotificationWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        PhoneCallPushNotificationWeb: PhoneCallPushNotificationWeb
    });

    exports.PhoneCallPushNotification = PhoneCallPushNotification;

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
