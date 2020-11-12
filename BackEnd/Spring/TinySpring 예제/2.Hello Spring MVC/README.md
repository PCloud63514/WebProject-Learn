# Hello Spring MVC

>- 이전 페이지: [Spring MVC 시작하기]()
>
>이번 과정에서는 web.xml 수정, Controller, Service, Dao, JSP를 만들어 확인하는 것을 진행하겠습니다.

 

 

## 프로젝트 구조

> 완성될 프로젝트 구조 입니다.

![image](https://user-images.githubusercontent.com/22608825/98650685-96ed8f00-237c-11eb-9e56-6e0c5aa1c454.png)

 

## 목표

우선 진행에 앞서 어떤 시나리오로 TinySpring을 구성할 것인지 결정해야합니다.

이해가 쉽도록 사용자의 정보 요청, 수정, 아이디 찾기를 기준으로 만들어보겠습니다.

**정리**

- 서비스(목적 또는 대상): user
- 계정 찾기:FindID(name, phoneNum) : 이름과 전화번호를 통해 ID 검색
- 회원정보 요청 : GetInfo(id): id를 이용해 User 정보 가져오기
- 회원정보 수정: SetInfo(id, age): id를 이용해 회원정보 age 수정하기

 

## 과정

### 1. Service 기준의 Package 생성

> 이번 TinySpring 의 목표는 서비스 입니다.  controller, service, dao, dto 등을 서비스에 맞춰 저장하기 위해 src/main/java에 새로운 Package를 생성합니다.
>
> src/java/com.cname.servicename
>
> ex)com.pcloud.user
>
> 그 뒤 나머지 package도 생성합니다.
>
> - com.pcloud.user.Controller
> - com.pcloud.user.service
> - com.pcloud.user.dto
> - com.pcloud.user.dao

   

### 2. [Dto](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dto.md)생성

> 목적의 대상은 사용자(User) 입니다.  이를 User의 정보를 저장할 Class를 구현합니다.

 

#### com.pcloud.user.dto.UserDto.java

```java
package com.pcloud.user.dto;

public class UserDto {
    String id;
    String name;
    String phoneNum;
    int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```



### 3. [Dao](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dao.md) 생성

> db 접근을 위한 Dao class를 작성합니다. 이 페이지에선 db 설정을 진행하지 않을 것입니다.
>
>  그러므로 동작만 가능하도록 Class를 작성합니다.

 

#### com.pcloud.user.dao.UserDao.java

```java
package com.pcloud.user.dao;

import com.pcloud.user.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {

    public UserDto userSelect(String name, String phoneNum) {
        UserDto userDto = new UserDto();
        userDto.setId("test123");
        userDto.setName(name);
        userDto.setPhoneNum(phoneNum);
        userDto.setAge(99);

        return userDto;
    }

    public UserDto userSelect(String id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName("pcloud");
        userDto.setPhoneNum("000-0000-0000");
        userDto.setAge(99);

        return userDto;
    }

    public void userUpdate(String name, int age) {

    }
}
```

​    

### 4. [Service ](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Service.md)생성

>요청을 처리하기 위한 비즈니스 코드를 작성하기 위해 Service를 작성합니다.
>
>생성 위치는 com.pcloud.user.service 입니다.
>
>* 이 페이지에선 interface를 구현하고, Impl Class를 작성하도록 진행합니다. 자세한 내용은 Service 설명을 확인해주세요.

​    

#### com.pcloud.user.service.UserService.java

```java
package com.pcloud.user.service;

import com.pcloud.user.dto.UserDto;

public interface UserService {
    public String FindID(String name, String phoneNum);
    public UserDto GetInfo(String id);
    public void SetInfo(String name, int age);
}
```

​       

#### com.pcloud.user.service.impl.UserServiceImpl.java

```java
package com.pcloud.user.service.impl;

import com.pcloud.user.dao.UserDao;
import com.pcloud.user.dto.UserDto;
import com.pcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;

    @Override
    public String FindID(String name, String phoneNum) {
        System.out.println("UserService: FindID Call");
        System.out.println("name:" + name);
        System.out.println("phoneNum:" + phoneNum);

        UserDto userDto = dao.userSelect(name, phoneNum);

        return userDto.getId();
    }

    @Override
    public UserDto GetInfo(String id) {
        System.out.println("UserService: GetInfo Call");
        System.out.println("id:" + id);

        UserDto userDto = dao.userSelect(id);
        return userDto;
    }

    @Override
    public void SetInfo(String name, int age) {
        System.out.println("UserService: SetInfo Call");
        System.out.println("name:" + name);
        System.out.println("age:" + age);

        dao.userUpdate(name, age);
    }
}
```

​    

### 5. [Controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Controller.md) 생성

> Client의 요청을 수신하고 view를 전달할 Controller Class를 작성합니다.

​     

#### com.pcloud.user.controller.UserController.java

```java
package com.pcloud.user.controller;

import com.pcloud.user.dto.UserDto;
import com.pcloud.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    //접근 url: /findid?name=HEON&phoneNum=010-000-0000
    @RequestMapping(value="/findId", method= RequestMethod.GET)
    public String findId(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phoneNum = request.getParameter("phoneNum");

        String id = service.FindID(name, phoneNum);
        return id;
    }
    //접근 url: /getinfo?id=qwe123
    @RequestMapping(value="/getInfo", method=RequestMethod.GET)
    public String getInfo(@RequestParam String id) {
        UserDto userDto = service.GetInfo(id);

        return "";
    }
    //접근 url:/setinfo/qwe123/25
    @RequestMapping(value="setInfo/{name}/{age}", method=RequestMethod.POST)
    public void setInfo(@PathVariable("name") String name, @PathVariable("age") int age) {
        service.SetInfo(name, age);
    }
}
```

​      

### 6. web.xml 수정하기

> Web Application의 실행 환경에 관련된 각종 설정 정보가 기록된 파일입니다.  Bean 참조, 필터 처리, 인코딩 처리 등 다양한 역할을 수행합니다.
>
> - 6번부터 7번 과정은 [Spring MVC 설정파일 정리]() 에 설명이 작성되어 있습니다. 보면서 진행 해주세요.



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
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name> 
        <url-pattern>/*</url-pattern> <!-- */from 을 /*로 변경-->
    </servlet-mapping>
</web-app>
```



### 7.dispatcher-servlet.xml





### 4. Dispatcher-servlet.xml 수정

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
    <context:component-scan base-package="com.pcloud.user"></context:component-scan> <!-- Component Scan -->
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



## 참고자료

[작성자: 세상의 모든기록 | Spring MVC home.jsp 동작 원리](https://all-record.tistory.com/165)