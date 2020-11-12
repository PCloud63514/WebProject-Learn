# Spring MVC 시작하기

> 이 페이지에선 Spring MVC 프로젝트를 직접 생성하는 과정을 진행할 것입니다.
>
> 프로젝트 준비물의 Version이 다르더라도 충분히 진행할 수 있습니다.

**프로젝트 준비물**

- IDE IntelliJ or eclipse
- [Tomcat 8.5](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD/tomcat%20%EC%84%A4%EC%B9%98.md) 
- jdk 11
- maven



## New Project

##### 1. Maven 선택하기

> Create from archetype 을 해제 하고 Next를 클릭.
>
> (Spring MVC를 선택해도 괜찮습니다. 단 4번의 선택지가 바뀝니다.)

##### 2. GroupId & ArtifactId

> GroupId 와 ArtifactId를 입력합니다.  ([Maven](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/1.Maven%20%EC%86%8C%EA%B0%9C.md)에서 설명)
>
> groupId: 프로젝트를 생성하는 조직의 고유 ID
>
> artifactId: 프로젝트 build 시 파일 대표 이름 (프로젝트 이름이라 생각하면 편합니다.)

##### 3. Project Name & Project Location

> artifactId 와 동일한 이름을 사용하는 것을 권장합니다.



**전체 과정**

![Spring MVC 프로젝트 생성](https://user-images.githubusercontent.com/22608825/98330834-9bddd600-203e-11eb-8abe-ec0b76ce1adc.gif)



##### 4. Project 구조 화면 및 AddFramework Support...

> 아직 Spring MVC를 추가하지 않은 상태입니다. 
>
> 최상단의 TinySpring을 오른쪽 클릭하여,  Add Framework Support... 를 클릭.

![image](https://user-images.githubusercontent.com/22608825/97992665-5941b100-1e26-11eb-98be-1b7267f87a23.png)

> 좌측의 Spring MVC를 찾아서 선택한 후 적용하면 자동으로 필요 Library가 설치됩니다.

<img src="https://user-images.githubusercontent.com/22608825/97993084-ec7ae680-1e26-11eb-9ba3-f8be838a23cc.png" alt="image" style="zoom:80%;" />



> 완료된 화면.

![image](https://user-images.githubusercontent.com/22608825/97993254-2815b080-1e27-11eb-8af7-fd234018ea73.png)



### 5. Spring MVC Library 추가하기

> Spring MVC 프로젝트로 실행시켰지만, 아직 Library가 추가된 것이 아닙니다.

1. 좌측 상단의 File -> Project Structure 클릭

![1](https://user-images.githubusercontent.com/22608825/98331381-cc723f80-203f-11eb-866d-d5fcd854aaf3.gif)



## 프로젝트 구조

> 우선 이 페이지는 IntelliJ를 사용한다는 전제로 작성되었습니다. 단 eclipse를 사용하지 않더라도 다른 블로그를 참고하며 하다보면 조금 다른 점을 찾을 수 있을 것입니다. 프로젝트 폴더의 web이 아니라 webapp 이라던지, pom.xml 위치가 다르거나, dispatcher-servlet.xml을 찾을 수 없고 servlet-context나 root-context.xml을 확인할 수 있을 것입니다. 이 페이지의 예제와 다른 이유는 eclipse의 STS를 사용해 프로젝트를 만들어서 차이를 보입니다.
>
> IntelliJ 또한 같은 구조의 프로젝트를 생성할 수 있습니다. 단 TinySpring에선 조금 씩 변경하며 진행하도록 하겠습니다.

  

#### src/main/java

> .java 파일이 작성되는 곳. Spring MVC에서 Servlet 구조를 만들어 주기 때문에, 별도의 Servlet을 만들 필요 없이 비즈니스에 맞춰 작성하면 됩니다.
>
> **[Controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Controller.md), [Service](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Service.md), [DAO](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dao.md)** 파일이 위치하는 곳 입니다.

  

#### src/main/resources

> java 코드에서 사용할 리소스를 관리할 디렉토립니다.
>
> mapper 나 sql 쿼리 등을 의미합니다.

  

#### src/test/java

> Unit Test 코드를 작성할 디렉토리 입니다.

  

**web**

> 웹과 관련된 파일들이 위치해 있는 디렉토리 입니다. html, css, js, jsp 파일 등이 위치하고 있습니다.
>
> Web Application 구동에 필요한 XML 설정파일 또한 함께 위치하고 있습니다.
>
> - Eclipse(STS)의 경우 web 이 아닌 webapp 입니다.
> - web 디렉토리는 외부의 접근을 허용합니다.
> - 단 하위 폴더인 WEB-INF는 외부 접근이 불가능하며, [controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Controller.md)를 통해 접근이 가능합니다.
> - 이는 compile된 class와 spring의 환경설정이 포함하여 보안의 목적을 위해 접근을 막는 것입니다.
>
> **Eclipse(STS)**의 경우 아래의 디렉토리를 포함하고 있습니다.
>
> - WEB-INF/class: 컴파일된 클래스
> - WEB-INF/resources: js, css, image 등을 관리할 디렉토리
> - WEB-INF/spring: spring 환경설정 파일
> - WEB-INF/views: html, jsp 파일 등을 관리할 디렉토리

  

## 파일 살펴보기

> Spring MVC를 진행하며 계속해서 기억하고, 수정해야하는 파일 목록들 입니다. 각자 Tomcat의 설정, Bean 설정, 의존성 라이브러리 설정 등 다양한 역할을 합니다. 아래 적힌 내용은 간단하게 설명해둔 내용이고 TinySpring을 진행할 때 모르거나 외워지지 않는다면 [Spring MVC 설정 파일 정리]() 를 봐주세요.

  

### web.xml

> WAS(Web Application Server, Tomcat)에 필요한 설정을 구성하는 XML 파일

  

### applicationContext.xml

>web application 에 공통으로 사용되는 [bean](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dto.md)을 설정하는 XML 파일.
>
>- eclipse(STS)는 **root-context.xml** 입니다.

  

### dispatcher-servlet.xml

>필요한 상황에 맞춰 사용되는 bean을 설정하는 XML 파일.
>
>applicationContext.xml에 작성된 bean이 있을 경우 dispatcher-servlet.xml에 작성된 bean이 우선적으로 적용됩니다.
>
>- eclipse(STS)는 **servlet-context.xml** 입니다.

  

### pom.xml

> [maven](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/1.Maven%20%EC%86%8C%EA%B0%9C.md) 설정 파일이며, 라이브러리 의존성 관리와 패키징 등의 관련 설정을 구성하는 XML 파일

  

## 동작시켜보기

> 이제 만들어진 TinySpring을 실행해볼 것입니다.
>
> 만약 Tomcat을 설치하면서 서버를 실행시켰다면 Tomcat 서버를 종료해주세요.
>
> - 관리자권한 cmd 실행
> - netstat -ano | findstr 8080 입력
> - taskkill /F /pid 포트번호 입력

  

### 1. Add Configuration 클릭

> 우측 상단의 Add Configuration.. 을 클릭합니다.

  

### 2. Image를 따라하기

> 글로 하나하나 쓰기에는 길기 때문에 gif를 첨부합니다.

![Spring MVC 프로젝트 Run](https://user-images.githubusercontent.com/22608825/98331606-3f7bb600-2040-11eb-8200-3fa5a03735f3.gif)

  

### 3. Shift + F10 을 눌러 서버 실행시키기



![image](https://user-images.githubusercontent.com/22608825/98332910-fc6f1200-2042-11eb-84e8-e92c0e75d1d3.png)

[결과화면]

  

## 이 페이지를 따라하며 생길 수 있는 Error 목록

[진행하다보면 생기는 오류](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/%EC%A7%84%ED%96%89%ED%95%98%EB%8B%A4%EB%B3%B4%EB%A9%B4%20%EC%83%9D%EA%B8%B0%EB%8A%94%20%EC%98%A4%EB%A5%98.md) 페이지

- 1. Error running 'index.jsp': port out of range:-1
- 2. Error MS949

  

## 참고자료

[작성자: WHITEPAEK | 스프링 프로젝트 구성 설정](https://whitepaek.tistory.com/56)