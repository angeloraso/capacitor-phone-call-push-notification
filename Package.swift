// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AnuradevCapacitorPhoneCallPushNotification",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "AnuradevCapacitorPhoneCallPushNotification",
            targets: ["PhoneCallPushNotificationPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "PhoneCallPushNotificationPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/PhoneCallPushNotificationPlugin"),
        .testTarget(
            name: "PhoneCallPushNotificationPluginTests",
            dependencies: ["PhoneCallPushNotificationPlugin"],
            path: "ios/Tests/PhoneCallPushNotificationPluginTests")
    ]
)