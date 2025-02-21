# @anuradev/capacitor-phone-call-push-notification

Capacitor plugin for phone call push notifications

## Install

```bash
npm install @anuradev/capacitor-phone-call-push-notification
npx cap sync
```

## API

<docgen-index>

* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
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

### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### getData()

```typescript
getData() => Promise<{ response: string; callId: string; }>
```

**Returns:** <code>Promise&lt;{ response: string; callId: string; }&gt;</code>

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


#### NotificationSettings

| Prop                     | Type                |
| ------------------------ | ------------------- |
| **`duration`**           | <code>number</code> |
| **`icon`**               | <code>string</code> |
| **`declineButtonText`**  | <code>string</code> |
| **`declineButtonColor`** | <code>string</code> |
| **`answerButtonText`**   | <code>string</code> |
| **`answerButtonColor`**  | <code>string</code> |
| **`color`**              | <code>string</code> |
| **`channelName`**        | <code>string</code> |
| **`channelDescription`** | <code>string</code> |


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


### Type Aliases


#### Partial

Make all properties in T optional

<code>{ [P in keyof T]?: T[P]; }</code>


#### Record

Construct a type with a set of properties K of type T

<code>{ [P in K]: T; }</code>

</docgen-api>
