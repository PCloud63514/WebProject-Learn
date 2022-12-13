# 3. Spring MVC-DB 연동

> - 이전 페이지:[Spring MVC-호출](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/2.Spring%20MVC-%ED%98%B8%EC%B6%9C)
>
> 이번 과정에서는 mysql을 연동하여 Connection 과정을 진행하겠습니다.
>
> 이 페이지는 기존의 xml에서 DI 요소를 작성하는 기술을 최소화 하고 Anotation을 사용하여 작업하는 것을 목표하고 있습니다.
>
>   
>
> 이 과정을 진행하기 전 db 셋팅 및 화면 설정을 위해 아래의 페이지를 진행 후 시작해주세요.
>
> - [MySQL 세팅하기](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/DB/MySQL%20%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0.mdhttps://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/DB/MySQL%20%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0.md)
> - [SpringMVC Json 형식 반환](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/%EC%A7%84%ED%96%89%ED%95%98%EB%8B%A4%EB%B3%B4%EB%A9%B4%20%EC%83%9D%EA%B8%B0%EB%8A%94%20%EC%98%A4%EB%A5%98.md)
>
> **주의** 이번 페이지는 과정을 진행하다보며 에러의 발생이 많습니다.
>
> [Spring MVC 진행하다보면 생기는 오류](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/%EC%A7%84%ED%96%89%ED%95%98%EB%8B%A4%EB%B3%B4%EB%A9%B4%20%EC%83%9D%EA%B8%B0%EB%8A%94%20%EC%98%A4%EB%A5%98.md) 8~11 번 Error 를 확인하며 진행해주세요.





### JDBC

> SpringMVC에서 DB와 연결하기 위해선 Java에서 기본적으로 제공하는 JDBC(Java Database Connectivity)에 대해 알아야합니다.

- Java에서 DB에 접속할 수 있도록 하는 Java API.
- JDBC는 DB에서 자료를 쿼리하거나 업데이트 하는 방법을 제공합니다.
- mysql, mariaDB, oracleDB 등이 설치되어 있어야합니다.



## 1. JDBC Connection

> JUnit Test를 통해 JDBC 연결을 확인하겠습니다.

  

### pom.xml 수정

> mysql connector, jdbc, mybatis를 추가 시켜야합니다. 추가로 Junit 테스트를 위해 Junit 버전을 4.1.2로 변경합니다.
>
> 추가 후 Project Structure 에서 Artifacts 를 수정하는 것을 잊지 말아주세요.

**pom.xml**

```xml
        <!-- Mysql Connector -->
	    <!-- MySQL Libray -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.22</version>
        </dependency>
        <!-- Spring JDBC -->
	    <!-- JDBC Library -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
        <!-- common-dbcp -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.7.0</version>
        </dependency>
        <!-- Spring Test -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
```



### Properties 작성

> 동작과 별개로 xml에서 필요 속성의 값을 분리하여 관리할 수 있도록 Properties 파일을 작성합니다.
>
> src/main/resources 폴더에 Properties 파일을 생성합니다.
>
> url 작성만 주의하면 됩니다. MySQL Workbench를 켜보면 아이디와 포트를 확인할 수 있고 127.0.0.1:3306/DB 이름 형식으로 작성하면 됩니다.
>
> password 는 직접 작성해주세요.(업로드 된 예제에도 password는 제외되었습니다.)

   

#### src/main/resources/mysql.properties

```properties
<!-- Oracle db를 사용하면 com.Oracle.jdbc.Driver -->
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/tinydb?characterEncoding=UTF-8
username=root
password=******
```



### applicationContext 수정

> dispatcher servlet이 사용자의 요청을 처리하기 위해 controller, ViewResolver 등을 처리한다면 
> applicationContext 는 web이 아닌 Services와 Repositories(DAO, VO 등)를 관리합니다. 위에서 작성한 Properties를 함께 사용합시다.

​    

#### WEB-INF/applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
             xmlns:mvc="http://www.springframework.org/schema/mvc"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xmlns:context="http://www.springframework.org/schema/context"
             xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd
	    http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

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

</beans>
```



### MySQL 연결 확인

> 위에서 설정한 bean을 자동 주입하기 위해 @Inject를 사용할 것입니다. @Autowired를 사용해도 괜찮습니다.
>
> 둘은 의존성 차이로 Inject는 javax   Autowired는 spring에 의존성을 두고 있습니다.
>
> 만약 @Inject 가 존재하지 않는다면 아래의 라이브러리를 pom.xml에 추가해주세요.

   

#### src/test/java/MySQLConnection.java

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MySQLConnection {
    @Inject
    private DataSource dataSource;

    @Test
    public void testGetUserList() throws Exception {
        Connection conn = dataSource.getConnection();
        String query = "select * from User";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()) {
            String id = resultSet.getString("name");
            String passwd = resultSet.getString("passwd");
            String name = resultSet.getString("name");
            int genderId = resultSet.getInt("genderId");

            System.out.println("=========================================");
            System.out.println("id:" + id);
            System.out.println("passwd:" + passwd);
            System.out.println("name:" + name);
            System.out.println("genderId:" + genderId);
        }

        resultSet.close();
        statement.close();
        conn.close();
    }
}
```

**결과:**

```
com.mysql.cj.jdbc.ConnectionImpl@560cbf1a
=========================================
id:Kim
passwd:test
name:Kim
genderId:1
=========================================
id:Yun
passwd:test
name:Yun
genderId:2
=========================================
id:Song
passwd:test
name:Song
genderId:3
```



#### @Inject 가 안되는 경우

```xml
		<dependency>
			<groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>
```


