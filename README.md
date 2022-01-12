[![](https://jitpack.io/v/shopgun/material-color-creator-android.svg)](https://jitpack.io/#shopgun/material-color-creator-android)

![mccl](material-color-creator-logo.png?raw=true "mccl")

A library for generating color palette shades and overlay colors. Based on Google's Material Design color palette examples:
https://www.google.com/design/spec/style/color.html#

The library simply generates a full palette of colors, from a single color which is interpreted as the `Shade500` for the `MaterialColor`. From there you can easily grab any `Shade` from the `MaterialColor` and even the text color which will contrast nicely to the color. Great right?

## Download 
Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    implementation 'com.github.shopgun:material-color-creator-android:2.0.0'
}
```

## Screenshots
![mccv](material-color-creator-video.gif "mccv")
