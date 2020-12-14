# Application Class 란

Android 예제를 참고하다보면 Application 클래스를 상속받은 MyApplication 클래스를 한 두번씩 확인하게 됩니다.

Application Class는 Application의 각 Component에서 공유 가능한 전역 Class 입니다.



Android Manigest.xml 에 Application으로 써 등록을 하면 getApplicationContext() 함수를 통해 접근이 가능합니다.

또한 Application 상속 Class는 MainActivity의 onCreate 함수 호출보다 호출된다는 특징이 있습니다.

(정확히는 ActivityThread 동작 전에 최우선으로 호출)



### MyApplication.kt

```kotlin
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
    override fun onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    override fun onLowMemory() {
        super.onLowMemory()
    }
}
```



| Method                                           | 설명                                                         |
| ------------------------------------------------ | ------------------------------------------------------------ |
| onCreate                                         | App 생성 시 호출. <br />앱 시작 및 동작에 필요한 변수, 리소스 초기화 로직을 구현 및 관리합니다. |
| onConfigurationChanged(Configuration new Config) | Component가 실행되는 동한 Device의 설정 정보가 변경될 때 System에서 호출합니다. |
| onLowMemory                                      | System의 리소스가 부족할 때 발생합니다.                      |



### AndroidManifest.xml

> android:name=".MyApplication" 을 추가합니다.

```xml
 ///
 <application
        android:allowBackup="true"
        android:name=".MyApplication"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        ...
```

