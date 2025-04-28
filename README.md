# @anuradev/capacitor-phone-call-push-notification

Capacitor plugin for phone call push notifications

## Install

```bash
npm install @anuradev/capacitor-phone-call-push-notification
npx cap sync
```

## API

<docgen-index>

* [`checkNotificationsPermission()`](#checknotificationspermission)
* [`requestNotificationsPermission()`](#requestnotificationspermission)
* [`getData()`](#getdata)
* [`register(...)`](#register)
* [`unregister()`](#unregister)
* [`addListener('onNewToken', ...)`](#addlisteneronnewtoken-)
* [`addListener('onNewData', ...)`](#addlisteneronnewdata-)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### checkNotificationsPermission()

```typescript
checkNotificationsPermission() => Promise<NotificationPermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#notificationpermissionstatus">NotificationPermissionStatus</a>&gt;</code>

--------------------


### requestNotificationsPermission()

```typescript
requestNotificationsPermission() => Promise<NotificationPermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#notificationpermissionstatus">NotificationPermissionStatus</a>&gt;</code>

--------------------


### getData()

```typescript
getData() => Promise<{ response: string; callId: string; timestamp: number; }>
```

**Returns:** <code>Promise&lt;{ response: string; callId: string; timestamp: number; }&gt;</code>

--------------------


### register(...)

```typescript
register(data?: Partial<NotificationSettings> | undefined) => Promise<void>
```

| Param      | Type                                                                                                        |
| ---------- | ----------------------------------------------------------------------------------------------------------- |
| **`data`** | <code><a href="#partial">Partial</a>&lt;<a href="#notificationsettings">NotificationSettings</a>&gt;</code> |

--------------------


### unregister()

```typescript
unregister() => Promise<void>
```

--------------------


### addListener('onNewToken', ...)

```typescript
addListener(eventName: 'onNewToken', listenerFunc: (data: { value: string; }) => void) => Promise<PluginListenerHandle>
```

| Param              | Type                                               |
| ------------------ | -------------------------------------------------- |
| **`eventName`**    | <code>'onNewToken'</code>                          |
| **`listenerFunc`** | <code>(data: { value: string; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### addListener('onNewData', ...)

```typescript
addListener(eventName: 'onNewData', listenerFunc: (data: { data: Record<string, string>; }) => void) => Promise<PluginListenerHandle>
```

| Param              | Type                                                                                          |
| ------------------ | --------------------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'onNewData'</code>                                                                      |
| **`listenerFunc`** | <code>(data: { data: <a href="#record">Record</a>&lt;string, string&gt;; }) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt;</code>

--------------------


### Interfaces


#### NotificationPermissionStatus

| Prop                | Type                                                        |
| ------------------- | ----------------------------------------------------------- |
| **`notifications`** | <code><a href="#permissionstate">PermissionState</a></code> |


#### NotificationSettings

| Prop                           | Type                |
| ------------------------------ | ------------------- |
| **`icon`**                     | <code>string</code> |
| **`picture`**                  | <code>string</code> |
| **`declineButtonText`**        | <code>string</code> |
| **`declineButtonColor`**       | <code>string</code> |
| **`answerButtonText`**         | <code>string</code> |
| **`answerButtonColor`**        | <code>string</code> |
| **`color`**                    | <code>string</code> |
| **`channelName`**              | <code>string</code> |
| **`channelDescription`**       | <code>string</code> |
| **`callingNameKey`**           | <code>string</code> |
| **`callingNumberKey`**         | <code>string</code> |
| **`typeKey`**                  | <code>string</code> |
| **`incomingSessionTypeValue`** | <code>string</code> |
| **`notifyTypeValue`**          | <code>string</code> |
| **`registrationTypeValue`**    | <code>string</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>


#### Partial

Make all properties in T optional

<code>{ [P in keyof T]?: T[P]; }</code>


#### Record

Construct a type with a set of properties K of type T

<code>{ [P in K]: T; }</code>

</docgen-api>
