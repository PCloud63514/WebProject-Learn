# React-Native 개발환경 구축

Windows 기준 React-Native 개발환경 구축 문서입니다.

- Python 2.7
- Node.js 12
- openJDK 8
- Android Studio & SDK API 29 Install
- Path 설정



## 설치

#### Python 2.7

[Python 2.7.0 Release | Python.org](https://www.python.org/download/releases/2.7/)

#### Node.js 12

[Node v12.13.0 (LTS) | Node.js](https://nodejs.org/en/blog/release/v12.13.0/)

#### OpenJDK 8

[OpenJDK project (github.com)](https://github.com/ojdkbuild/ojdkbuild)

#### Android Studio

[Download Android Studio and SDK tools  | Android 스튜디오](https://developer.android.com/studio)



## Path 설정

환경 변수 추가 (기본 사항이라 쓸 것이 없다.)

따로 변수를 만들어 관리하거나 하여 Path에 추가.

#### Python

```
PATHON_PATH = {Python 경로}
```

#### Android

```
ANDROID_PLATFORM_TOOLS = ... \Android\Sdk\platform-tools
```

```
ANDROID_SDK = \Android\Sdk
```

#### OpenJDK

```
OPEN_JDK = ...\bin
```



cmd에 버전 확인같은 명령어로 설정이 완료됬는지 확인

#### ex)

- java -version

- javac -version 
- adb devices



## 프로젝트 생성

npx 사용 예정

1. 프로젝트를 생성할 폴더로 이동

2. npx react-native init <Project Name>

   1. Type script 로 하기 위해선 --template react-native-template-typescript 추가

3. 생성 후 cd 명령어를 사용해 폴더 이동

4. android 폴더 내에 local.properties 파일 생성 및 SDK 경로 입력

   1. ```
      sdk.dir = C\:\\Users\\{name}\\AppData\\Local\\Android\\Sdk
      ```

5. jetify 추가

   1. ```
      npx jetify
      
      루트 디렉토리의 package.json 수정
      
      scripts에 아래 내용 추가
      "preandroid":"npx jetify"
      ```

6. ADB Manager에서 API Level 29로 하나 Device 생성 및 실행

7. npx react-native run-android



## 끝

![image](https://user-images.githubusercontent.com/22608825/103620454-d7c9e480-4f76-11eb-94e6-68daf278603c.png)





error Failed to install the app. Make sure you have the Android development environment t set up: https://reactnative.dev/docs/environment-setup. Run CLI with --verbose flag r 
for more details.
Error: Command failed: gradlew.bat app:installDebug -PreactNativeDevServerPort=8081   







## 참고문헌

### [Setting up the development environment · React Native](https://reactnative.dev/docs/environment-setup)