import { PhoneCallPushNotification } from '@anuradev/capacitor-phone-call-push-notification';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    PhoneCallPushNotification.echo({ value: inputValue })
}
