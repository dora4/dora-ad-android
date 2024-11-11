dora-ad-android
![Release](https://jitpack.io/v/dora4/dora-ad-android.svg)
--------------------------------

#### Gradle依赖配置

添加以下代码到项目根目录下的settings.gradle.kts
```kotlin
dependencyResolutionManagement {
    repositories {
        maven { setUrl("https://jitpack.io") }
    }
}
```
添加以下代码到app模块的build.gradle.kts
```kotlin
dependencies {
    // 扩展包必须在有主框架dora的情况下使用
    implementation("com.github.dora4:dora:1.2.29")
    implementation("com.github.dora4:dcache-android:2.5.20")
    implementation("com.github.dora4:dora-ad-android:1.14")
    implementation("com.github.dora4:dview-titlebar:1.37")
    implementation("io.github.youth5201314:banner:2.2.2")
}
```

#### 使用方式
在Application中添加AdService的配置
```kotlin
RetrofitManager.initConfig {
    mappingBaseUrl(AdService::class.java, "http://dorachat.com:9091")
}
```
将layout_banner_ad.xml添加到布局。
如
```xml
<include layout="@layout/layout_banner_ad"/>
```
加载横幅广告
```kotlin
BannerAd.loadBannerAds(this, "app_name", binding.banner as Banner<String, BannerAd.ImageAdapter>)
```
