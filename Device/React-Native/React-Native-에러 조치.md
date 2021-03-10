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
> "scripts": {
> "preandroid": "npx jetify",<-- 추가
>"android": "react-native run-android",
>   "ios": "react-native run-ios",
> "start": "react-native start",
>  "test": "jest",
> "lint": "eslint ."
>  }
> ```
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



## styled-compoments createGlobalStyle 사용 문제

React 에서 잘만 사용되던 createGlobalStyle function이 정작 React Native에서는 사용이 안된다.

공식문서를 참조해보니 Web에서만 동작한다는 것을 확인했다. (즉 react만 사용된다는 것)

createGlobalStyle을 사용하려는 이유는 공통 Style을 잡기 위함인데, 이를 대체할 수 있는 것이 무엇일지 찾아보았다.









## Gradle 7.0 에러

Android Application을 assembleRelease 하는 과정에서 발생한 에러다.

Gradle 7.0 에러는 상당히 많은 영역을 포함하다보니 해결하는데 골을 썩였다.



### 해결법

> Gradle 7.0은 에러 범위가 넓어 시도했던 것은 전부 작성해두었다.

1. android/gradle/wrapper/gradle-wrapper.properties 수정
   1. distributionUrl의 gradle Version을 6.3.0으로 변경한다.



- ㅋㅋㅋㅋ expo-updates 모듈 없어서 그랬음...

App Release 중 Expo-Permissions 에러



## Gradle 7.0 Error - expo-updates

가장 당황스러웠다. Expo는 참 사람을 고생시키는데 능력이 있다.

Android를 gradlew clean 하는 과정에서 발생했다. 

어지간해선 clean을 시도하는데 Error 가 발생하는 일이 없어서, log를 쓱 읽어보니(log가 상당히 길게 나와서 놓치고 20분이나 소모해서 발견했다.)

expo-updates 를 찾지 못했다고 나온 것을 확인했다. 

대체 react-native로 run 시킬 때는 에러가 안뜨더니... clean 과정에서 react-native-unimodules을 정리하는 과정이 존재하는데 이떄 **expo-updates** modules 을 찾지 못해 발생한듯하다.

### 해결법

```
npm i install expo-updates
```



## react-native-unimodules/modules/expo-permissions Error

Android Application를 Release 하는 과정에서 발생한 에러다.

- 2021년03월10일 기준

- minSdk 21
- compileSdkVersion & targetSdkVersion 29
- buildToolVersion = "29.0.2"
- gradle 6.3



Error 메시지에 expo-permissions 관련 로그가 나와 조사해보니 Version 호환에 대한 문제였다.

분명 이를 해결하기 위해 **expo-permissions@9.1.0** 을 설치해두어 괜찮을 줄 알았는데 아니였다.

**addUnimodulesDependencies**로 excute 도 해보고 별 방법을 써봤지만 전부 소용없었다.



### 해결법

**react-native-unimodules** 의 package.json을 확인해보면 이미 내부에서 expo-permissions@12.0.0 을 종속하고 있다.

이를 해결하기 위해 9.1.0으로 수정 후 다시 빌드하여 해결되었다.