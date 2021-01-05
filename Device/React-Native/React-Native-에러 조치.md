# React-Native 에러 조치



## Error: Command failed: gradlew.bat app:installDebug -PreactNativeDevServerPort=8081



## SDK location not found. Define location with an ANDROID_SDK_ROOT ...

> SDK 경로를 찾지 못해서 발생한 에러.
>
> 프로젝트 내 android 폴더 내에 local.properties 파일 생성
>
> sdk.dir = {sdk location}
>
> #### 예시
>
> ```
> sdk.dir = C\:\\Users\\dream\\AppData\\Local\\Android\\Sdk
> ```



## androidX 를 지원할 경우 생기는 문제.

> ```
>npx jetify
> ```
>
> ```json
> "preandroid": "npx jetify" <-- 추가
> ===================================================
>"scripts": {
>   "preandroid": "npx jetify",
> "android": "react-native run-android",
>  "ios": "react-native run-ios",
> "start": "react-native start",
>  "test": "jest",
> "lint": "eslint ."
> }
>```
> 
> 



## 최신 JDK 사용 문제

JDK 8 이외 높은 버전을 사용할 경우 Gradle 버전에 문제가 발생한다.

### 해결법

android/gradle/wrapper/gradle-wrapper.properties 열기

distributionUrl 속성의 gradle 버전 변경 -> [Gradle | Releases](https://gradle.org/releases/) 버전 맞춰서 설정

- [OpenJDK project (github.com)](https://github.com/ojdkbuild/ojdkbuild) 에서 jdk 8 받고 path 설정 다시하는게 편하긴하다.

```
distributionUrl=https\://services.gradle.org/distributions/gradle-6.7.1-all.zip
```