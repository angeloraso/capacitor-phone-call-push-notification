export interface PhoneCallPushNotificationPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
