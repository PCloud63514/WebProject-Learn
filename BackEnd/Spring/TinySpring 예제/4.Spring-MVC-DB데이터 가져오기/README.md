# 4. Spring MVC-DB데이터 가져오기

> - 이전 페이지:[Spring MVC-호출](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/2.Spring%20MVC-%ED%98%B8%EC%B6%9C)
>
> 이번 과정에서는 Controller, Service, Dao, dto 을 구현하고 jdbcTemplate을 이용해 데이터를 가져오는 작업을 진행할 것입니다.
>
> 이 페이지는 Spring의 DI 작업 중 xml 작성을 최소화하고,  Anotation을 사용하여 작업하는 것을 목표하고 있습니다.
>



## 1. 시나리오 목표

우선 진행에 앞서 어떤 시나리오로 TinySpring을 구성할 것인지 결정해야합니다.

이해가 쉽도록 사용자의 정보 요청, 수정, 아이디 찾기를 기준으로 만들어보겠습니다.

**정리**

- 서비스(목적 또는 대상): user
- User 정보 목록 가져오기: User의 모든 정보를 포함한 목록을 가져오기
- User 정보 가져오기: id를 기준으로 User의 정보를 가져오기



## 2. web.xml 수정

> 시나리오 대상이 home 에서 User로 변경되었습니다. 이에 맞추어 package 추가 및 web.xml을 수정하려 합니다.
>
> src/main/java.com.pcloud.user package를 추가해주세요. 더 이상 home package를 사용하지 않습니다. 삭제해주세요.
>
> web.xml 의 dispatcher 내의 url-pattern을 /home/* 에서 /user/*로 변경합니다.



## 3. applicationContext.xml 수정

>xml에 bean을 작성합니다. 기존의 Spring 이라면, Dao, Service 등의 Class를 작성하고 bean을 작성해야합니다.
>
>이번엔 Anotation을 이용해 이런 xml의 bean 작성을 최소화 하고자 합니다.
>
>핵심은 **component-scan** 입니다.



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:mvc="http://www.springframework.org/schema/mvc"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
	    http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <mvc:annotation-driven/>
    <context:component-scan base-package="com.pcloud.user"></context:component-scan>

    <!-- properties 위치.  콤마(,) 를 이용해 다양한 properties를 동시에 추가시킬 수 있습니다. -->
    <context:property-placeholder location="classpath:mysql.properties"/>
    <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName">
            <value>${driverClassName}</value>
        </property>
        <property name="url">
            <value>${url}</value>
        </property>
        <property name="username">
            <value>${username}</value>
        </property>
        <property name="password">
            <value>${password}</value>
        </property>
    </bean>

    <bean name="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
```



## 4. VO 생성

> [MySQL 시작하기]() 를 진행한 기준으로 작성했습니다.
>
> 작성했던 User 테이블의 도메인에 맞춰 VO 클래스를 작성합니다.



### com.pcloud.user.vo.UserVO.java

```java
package com.pcloud.user.vo;

public class UserVO {
    private String id;
    private String passwd;
    private String name;
    private int genderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }
}
```



## 5. Dao 작성

> DB의 접근할 객체인 Dao를 작성합니다.
>
> [JdbcTemplte]() 설명입니다.



### com.pcloud.user.dao.UserDao.java

```java
package com.pcloud.user.dao;

import com.pcloud.user.vo.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    public List<UserVO> userSelectAll() {
        String query = "select * from User";
        List<UserVO> userList = jdbcTemplate.query(query,
                new RowMapper<UserVO>() {
                    @Override
                    public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                        String id = resultSet.getString("name");
                        String passwd = resultSet.getString("passwd");
                        String name = resultSet.getString("name");
                        int genderId = resultSet.getInt("genderId");

                        UserVO user = new UserVO();
                        user.setId(id);
                        user.setPasswd(passwd);
                        user.setName(name);
                        user.setGenderId(genderId);
                        return user;
                    }
                });

        return userList.isEmpty() ? null : userList;
    }

    public UserVO userSelect(String id) {
        String query = "select * from User where id = ? ";

        UserVO user = jdbcTemplate.queryForObject(query, new Object[]{id},
                new RowMapper<UserVO>() {
                    @Override
                    public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                        String id = resultSet.getString("name");
                        String passwd = resultSet.getString("passwd");
                        String name = resultSet.getString("name");
                        int genderId = resultSet.getInt("genderId");

                        UserVO user = new UserVO();
                        user.setId(id);
                        user.setPasswd(passwd);
                        user.setName(name);
                        user.setGenderId(genderId);
                        return user;
                    }
                });
        return user;
    }
}
```



## 6. Service 작성

> 비즈니스 로직 Service를 작성합니다.

 

### com.pcloud.user.service.UserService.java

```java
package com.pcloud.user.service;

import com.pcloud.user.dao.UserDao;
import com.pcloud.user.vo.UserVO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {

    @Inject
    UserDao userDao;

    public List<UserVO> getUserList() {
        List<UserVO> list = userDao.userSelectAll();
        return list;
    }

    public UserVO getUser(String id) {
        UserVO user = userDao.userSelect(id);
        return user;
    }
}
```



## 7. Controller 작성

> 마지막으로 Controller 를 작성합니다.

 

```java
package com.pcloud.user.controller;

import com.pcloud.user.vo.UserVO;
import com.pcloud.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class UserController {

    @Inject
    UserService userService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public List<UserVO> getUserList() {
        List<UserVO> list = userService.getUserList();
        return list;
    }

    @RequestMapping(value="/{id}/view", method=RequestMethod.GET)
    public UserVO getUser(@PathVariable("id") String id) {
        UserVO user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public UserVO getUser2(@RequestParam("id") String id) {
        UserVO user = userService.getUser(id);
        return user;
    }
}
```



## 8. 동작

> 모두 구성하였습니다. 재대로 동작하는지 확인해봅시다. 
>
> Server를 실행 시키고 아래의 url을 입력해봅시다.
>
> - /user/list
> - /user/test01/view
> - /user/test01



#### /user/list 결과 화면

![image](https://user-images.githubusercontent.com/22608825/101238490-00ff1700-3724-11eb-9e82-152bec8d2927.png)