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
| <servlet>         | 사용할 DispatcherServlet을 지정합니다. <br />web/WEB-INF 디렉토리에 <servlet-name>-servlet.xml 파일을 Spring 설정 파일로 사용합니다. |
| <servlet-mapping> | 요청 url의 pattern을 지정하는 방법 입니다. ex)xxxx.do 로 요청 시 *.do |





## dispatcher-servlet.xml

> Eclipse(STS)에서는 servlet-context.xml이라는 이름으로 생성되는 이 파일은,
>
> web application의 사용자 요청을 받기 위한 entity point로 써 servlet의 context 설정이다.

- 
- servlet-context.xml







## applicationContext.xml

- root-context.xml



