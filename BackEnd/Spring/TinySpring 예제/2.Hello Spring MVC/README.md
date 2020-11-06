# Hello Spring MVC

>- 이전 페이지: [Spring MVC 시작하기]()
>
>프로젝트 생성까지 완료하였다면, 이제 프로젝트를 설정하여 실행하는 과정을 진행하겠습니다.



## 과정

### 1. web/WEB-INF/web.xml 수정하기

- <servlet-mapping> 의 urlpattern의 값을 */from 에서 /로 변경해줍니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name> 
        <url-pattern>/</url-pattern> <!-- */from 을 /로 변경-->
    </servlet-mapping>
</web-app>
```



### 2. Library 추가하기

> Spring과 Spring MVC 라이브러리를 추가할 것입니다.

- File -> Project Structure -> Artifact 로 이동
- Output Layout의 Available Elements 의 Spring과 Spring MVC 더블클릭



![1](https://user-images.githubusercontent.com/22608825/98221763-6334f280-1f93-11eb-866d-be3df455d3ba.gif)



### 3. controller package 추가

> 개발에 필요한 controller 패키지를 추가합니다.
>
> 향후엔 필요 정보에 따라 도메인을 나누어 설정할 것이기 때문에 지금은 가볍게 지나가도 괜찮습니다.

- src/main/java 에 com.pcloud.tinyspring.controller 패키지 추가



![image](https://user-images.githubusercontent.com/22608825/98222767-9cba2d80-1f94-11eb-9da9-9bf5cead9899.png)



### 4. Dispatcher-servlet.xml 수정

- web/WEB-INF/dispatcher-servlet.xml 열기
- 아래의 코드블럭 붙혀넣기
- 단 spring 버전이 4라면 아래의 spring-context와 mvc 옆의 5를 4로 변경
- controller 의 패키지 위치가 다르다면, 직접 변경하기

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-5.0.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-5.0.xsd">
    <mvc:annotation-driven></mvc:annotation-driven>
    <context:component-scan base-package="com.pcloud.tinyspring.controller"></context:component-scan> <!-- Component Scan -->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/views/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
</beans>
```



### 5. Views Derectory 생성 및 index.jsp 파일 이동

- web/WEB-INF에 views Derectory 생성
- web/WEB-INF 에 위치한 index.jsp를 방금 생성한 views Derectory로 이동
- index.jsp 파일의 body 내용을 Hello Spring 으로 변경

```jsp
<html>
  <head>
    <title>Spring-MVC</title>
  </head>
  <body>
      Hello Spring
  </body>
</html>
```



### 6. HomeController 생성

> 테스트를 위한 HomeController를 생성해볼 것입니다. 3 단계에 생성한 controller package에 HomeController.java를 추가해주세요.

- java.com.pcloud.tinyspring package에 HomeController.java 파일 생성
- 코드펜스 내용 복사 및 붙여넣기

```java
package com.pcloud.tinyspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home() {
        return  "index";
    }
}
```



### 7. Tomcat server 추가 및 시작하기

- Add Configuration 또는 Run 버튼을 클릭
- 