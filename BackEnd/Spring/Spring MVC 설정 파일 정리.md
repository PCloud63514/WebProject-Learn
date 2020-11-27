# Spring MVC 설정 파일 정리

> Spring MVC를 동작하기 위해서 초기에 필수로 작성해야하는 파일에 대해 정리한 페이지 입니다.
>
> 작성은 IntelliJ 기준 입니다.





## pom.xml

> [maven](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/1.Maven%20%EC%86%8C%EA%B0%9C.md) 설정 파일이며, 라이브러리 의존성 관리와 패키징 등의 관련 설정을 구성하는 XML 파일 입니다.
>
> maven의 pom 을 정리해두었습니다. 읽어주세요.

  

## web.xml

> Web Application의 실행 환경에 관련된 각종 설정 정보가 기록된 파일입니다.  Bean 참조, 필터 처리, 인코딩 처리 등 다양한 역할을 수행합니다.
>
> DispatcherServlet 을 통해 브라우저의 요청을 가장 먼저 처리합니다.

   

#### web/WEB-INF/web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- Servlet 에서 사용할 자원 설정 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <!-- Servlet과 Filter가 공유하는 Spring Container -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- Dispatcher Servlet -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name> 
        <url-pattern>/*</url-pattern> <!-- */from 을 /*로 변경-->
    </servlet-mapping>
</web-app>
```

​    

| Element           | 설명                                                         |
| ----------------- | ------------------------------------------------------------ |
| context-param     | 모든 Servlet과 Filter들이 공유하는 Context.<br />Listener Element 가 있어야 작동. |
| <servlet>         | 사용할 DispatcherServlet을 지정합니다. <br />web/WEB-INF 디렉토리에 <servlet-name>-servlet.xml 파일을 Spring 설정 파일로 사용합니다. |
| <servlet-mapping> | 요청 url의 pattern을 지정하는 방법 입니다. ex)xxxx.do 로 요청 시 *.do |



### Servlet Element

> DispatcherServlet을 지정하는 것 외에도 추가적인 설정이 가능합니다.

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
    </servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            /WEB-INF/main.xml
            /WEB-INF/bbs.xml
            classpath:/common.xml
        </param-value>
    </init-param>
</servlet>
```

   

| Element    | 설명                                                         |
| ---------- | ------------------------------------------------------------ |
| init-param | 한 개 이상의 설정파일을 사용하거나, 이름이 ***-servlet.xml 형식이 아닌 파일을 사용하기 위해 지정합니다.<br />contextConfigLocation 초기화 파라미터로 설정 파일 목록을 지정할 수 있습니다.<br />contextConfigLocation 초기화 파라미터는 설정 파일의 목록을 값으로 갖는데<br /> 이 때 각 설정 파일은 콤마, 공백, 탭, 줄 바꿈, 세미클론을 이용해 구분합니다.<br />각 설정 파일의 결로는 web application root directory를 기준으로 하며, <br />"file:" 이나 "classpath:"접두어를 이용해 Local File이나 ClassPath에 위치한 파일을 사용할 수 있습니다. |

   



![image](https://user-images.githubusercontent.com/22608825/99663427-f3059100-2aa9-11eb-9d49-d5106711b310.png)





## applicationContext.xml

> 위 이미지의 Root WebApplicationContext에 해당하는 파일 입니다.
>
> Web Application 최상단에 위치하고 있는 Context로써, web.xml의 context-param 태그에서 확인할 수 있습니다.
>
> Spring에서 Application Context는 BeanFactory를 상속받고 있는 Context라는 의미로, 아래의 특징을 갖고 있습니다.

​    

- Eclipse(STS)에선 root-context.xml 으로 생성됩니다.
- 특정 Servlet 설정과 관계없는 bean, Service, Repository(dao), DB 등 비즈니스 로직과 관련된 설정을 담당합니다.
- 최상단 Context이기 때문에, 여러 Servlet에서 공통적으로 사용할 수 있는 Bean을 정의합니다.
- 최상단 Context이기 때문에, 하위 Servlet(***-servlet.xml)에 정의된 Bean을 사용할 수 없습니다.



## dispatcher-servlet.xml

> 브라우저의 요청을 받았을 때, 어떤 Controller로 처리할지 정의하는 파일 입니다.
>
> Servlet 단위로 생성되는 Context로써, Application Context를 자신의 부모 Context로 지정합니다.
>
> web.xml에서 별도의 설정을 하지 않았다면 dispatcher-servlet을 사용하게 됩니다.

​    

- Eclipse(STS)에선 servlet-context.xml 으로 생성됩니다.
- url과 관련된 Controller, @(Anotation), ViewResolver, Interceptor, MultiparResolver, View, bean 등의 설정이 가능합니다.
- ApplicationContext의 자식 context이기 때문에, Application Context와 ServletContext에 같은 id의 Bean이 등록되었을 경우, Servlet Context에 선언된 Bean을 사용합니다.



**Bean 찾는 순서: Servlet Context 조사 -> 없다면 Application Context 조사.**





## 참고자료

[Carrey's Application-Context와 Servlet-Context](https://jaehun2841.github.io/2018/10/21/2018-10-21-spring-context/#web-application-context)