# 2. Spring MVC-호출

>- 이전 페이지: [Spring MVC 시작하기]()
>
>이번 과정에서는 .web.xml 수정, Controller 작성 후 controller에 접근하여 원하는 jsp를 화면에 출력하는 것을 목표로 하겠습니다.

 

## 과정

### 1.pom.xml 수정

> add framework suport.. 전에 먼저 수정할 경우 재대로 프로젝트 구성이 이루어지지 않을 수 있습니다.
>
> 이전 페이지의 과정을 전부 마친 후에 진행해주세요.
>
> @RequestMapping 같은 어노테이션은 Spring.web에 존재하기 때문에 아래의 의존성 파일을 추가해야합니다.(spring-webmvc 등)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.pcloud</groupId>
    <artifactId>TinySpring</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <org.springframework-version>5.2.3.RELEASE</org.springframework-version>
    </properties>

    <dependencies>
        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
        <!-- JUnit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```



### 2. Package 생성

> 이전 페이지에서 프로젝트 구조에 대해 설명했습니다. 이에 따라 src/main/java에 원하는 코드를 작성할 것 입니다.
>
> 목표하고자 하는 서비스를 구분하기 위해 Package를 작성할 것입니다. 
>
> **/src/main/java/com.pcloud.home** 패키지를 생성해주세요.

   

### 3.Controller 생성

> 사용자의 요청을 받고 처리하기 위해 [Controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring MVC Link/Controller.md)를 생성해야합니다. 
>
> 우선 예제를 위해 HomeController.java 를 작성하겠습니다.



#### com.pcloud.home/HomeController.java

```java
package com.pcloud.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value="/call", method= RequestMethod.GET)
    public String home() {
        return "home";
    }
}
```



### 4.Jsp 작성

> 보여주기 위한 용도의 페이지를 작성하겠습니다. web/WEB-INF에 views 디렉토리를 생성 후 home.jsp 를 추가해주세요.

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    Welcome Home
</body>
</html>
```



### 5. web.xml 수정

> Web Application의 실행 환경에 관련된 각종 설정 정보가 기록된 파일입니다.  Bean 참조, 필터 처리, 인코딩 처리 등 다양한 역할을 수행합니다.
>
> [Spring MVC 설정파일 정리](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC%20%EC%84%A4%EC%A0%95%20%ED%8C%8C%EC%9D%BC%20%EC%A0%95%EB%A6%AC.md) 에 설명이 작성되어 있습니다. 보면서 진행 해주세요.



- <servlet-mapping> 의 urlpattern의 값을 */from 에서 /*로 변경해줍니다.
- <servlet-mapping> 이란 사용자의 요청 URL 패턴을 지정하는 것 입니다.  xxxx.do 로 처리하고 싶다면 *.do 로 지정할 수 있습니다.



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
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name> 
        <url-pattern>/home/*</url-pattern>
    </servlet-mapping>
</web-app>
```



### 6.dispatcher-servlet.xml 수정

> 위의 Library 를 추가시켰다면 web/WEB-INF 디렉토리에 dispatcher-servlet.xml 파일을 확인할 수 있습니다.(없다면 새로고침)
>
> dispatcher-servlet은 사용자의 요청을 처리할 Controller를 이어주는 작업을 합니다.
>
> HandlerMapping, HandlerAdapter 작업을 하는 것 입니다.



#### 알아야할 내용

**<context:component-scan base-package="com.spring.example"/>**

- @Controller, @Repository, @Service, @Component Anotation이 사용된 클래스를 자동으로 스캔하여 Bean으로 등록해주는 태그 입니다.

**<anotation-driven/>**

- URL mapping을 허용하는 태그 입니다. @RequestMapping을 사용할 수 있게되고, 지정된 URL로 사용자의 요청 URL이 매핑되도록 한다.

<context:component-scan> controller, service, dao 등이 존재하는 패키지를 명시하는 태그 입니다. 추가 설정을 할 경우 controller 만 인식하도록 할 수 있습니다.

- controller 의 패키지 위치가 다르다면, 직접 변경하기



**web/WEB-INF/dispatcher-servlet.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/mvc"
             xmlns:beans="http://www.springframework.org/schema/beans"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
	    http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <annotation-driven/>
    <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <beans:property name="prefix" value="/WEB-INF/views/"/>
        <beans:property name="suffix" value=".jsp"/>
    </beans:bean>
    <context:component-scan base-package="com.pcloud.home"/> <!-- Component Scan -->
</beans:beans>
```



### 7.동작해보기

> 모든 내용을 진행했다면 다음과 같은 화면을 확인할 수 있습니다.

![image](https://user-images.githubusercontent.com/22608825/100445159-f921f000-30ef-11eb-99a9-9800119e4e52.png)



## 진행하다보면 생기는 오류

4. Error:java: Source option 5 is no longer supported. Use 6 or later.

5. No mapping for GET

## 참고자료

jsphttps://all-record.tistory.com/165)