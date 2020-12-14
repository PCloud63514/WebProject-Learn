# Koin-적용

Koin 에 대해 이해하였다면 이번엔 적용을 목표로 진행하겠습니다.

| 프로젝트 명             | [TinyKoin]()           |
| ----------------------- | ---------------------- |
| 목표                    | Android 에서 Koin 적용 |
| 언어                    | Kotlin                 |
| 번외 Library, Framework | ㅡ                     |





## 시작



### MyApplication 작성

> Application Class를 상속받은 [MyApplication]() Class 를 작성합니다.
>
> - Koin 2.X 기준



MyApplication.kt

> Koin을 추가한 기본 구성입니다.

```kotlin
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            androidFileProperties()
        }
    }
}
```



| 함수                            | 설명                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| startKoin                       | Application 단위의 Class                                     |
| startApplication                | Application에서 Koin을 시작합니다. 그 후, GlobalContext에 Koin을 등록합니다.<br />하나의 Application에서 하나의 Koin만 사용할 수 있습니다. |
| androidLogger()                 | Koin의 Android Logging System입니다.                         |
| androidContext(context Context) | Android Context를 넘겨줍니다. 필수입니다.                    |
| androidFileProperties()         | assets 및 koin.properties 파일에서 Property를 가져옵니다.    |



# 참고문헌

[Ready for Koin 2.0 🎉. It’s now time! Koin 2.0 stable version… | by Arnaud Giuliani | Koin developers | Medium](https://medium.com/koin-developers/ready-for-koin-2-0-2722ab59cac3)