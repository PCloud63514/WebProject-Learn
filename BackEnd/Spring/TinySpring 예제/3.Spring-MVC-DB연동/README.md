# 3. Spring MVC-DB 연동

> - 이전 페이지:[Spring MVC-호출](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/2.Spring%20MVC-%ED%98%B8%EC%B6%9C)
>
> 이번 과정에서는 mysql을 연동하여 Connection 성공 까지 알아보겠습니다.
>
> 이 과정을 진행하기 전 db 셋팅 및 화면 설정을 위해 아래의 페이지를 진행 후 시작해주세요.
>
> - [MySQL 세팅하기](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/DB/MySQL%20%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0.mdhttps://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/DB/MySQL%20%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0.md)
> - [SpringMVC Json 형식 반환](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/%EC%A7%84%ED%96%89%ED%95%98%EB%8B%A4%EB%B3%B4%EB%A9%B4%20%EC%83%9D%EA%B8%B0%EB%8A%94%20%EC%98%A4%EB%A5%98.md)
>
> **주의** 이번 페이지는 과정을 진행하다보며 에러의 발생이 많습니다.
>
> [Spring MVC 진행하다보면 생기는 오류](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C/%EC%A7%84%ED%96%89%ED%95%98%EB%8B%A4%EB%B3%B4%EB%A9%B4%20%EC%83%9D%EA%B8%B0%EB%8A%94%20%EC%98%A4%EB%A5%98.md) 8~11 번 Error 를 확인하며 진행해주세요.





## 1. JDBC & Mybatis

> SpringMVC에서 DB와 연결하기 위해선 Java에서 기본적으로 제공하는 JDBC(Java Database Connectivity)와 이를 보조해줄 Mybatis에 대해 알아야합니다.



### JDBC

- Java에서 DB에 접속할 수 있도록 하는 Java API.
- JDBC는 DB에서 자료를 쿼리하거나 업데이트 하는 방법을 제공한다.
- mysql, mariaDB, oracleDB 등이 설치되어 있어야한다.

   

### Mybatis

- Java에서 기본적으로 제공하는 JDBC를 보다 쉽게 사용할 수 있도록 도와주는 Framework.
- SQL 쿼리문을 코드 내에 작성하지 않고 Dao를 대신할 Mapper 라는 파일에 관리하여 코드와 SQL 쿼리를 분리.
- 유지보수를 높이고 작업량을 줄이기 위해 사용.



## 2. pom.xml 수정

> mysql connector, jdbc, mybatis를 추가 시켜야합니다. 추가로 Junit 테스트를 위해 Junit 버전을 4.1.2로 변경해주세요.
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
        <!-- Mybatis -->
	    <!-- Mybatis Framework -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.2.8</version>
        </dependency>
        <!-- Mybatis Spring -->
	    <!-- Mybatis와 Spring 연결 Library -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.2</version>
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
	    <!-- Spring와 Mybatis가 정상적으로 연동되는지 확인을 위한 Library. -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
```



## 2. Properties 작성

> 동작과 별개로 xml에서 필요 속성의 값을 분리하여 관리할 수 있도록 Properties 파일을 작성합니다.
>
> src/main/resources 폴더에 Properties 파일을 생성해주세요.
>
> url 작성만 주의하면 됩니다. MySQL Workbench를 켜보면 아이디와 포트를 확인할 수 있고 127.0.0.1:3306/DB 이름 형식으로 작성하면 됩니다.
>
> password 는 직접 작성해주세요.(업로드 된 예제에도 password는 제외되었습니다.)

   

#### src/main/resources/mysql.properties

```properties
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/tinydb?characterEncoding=UTF-8
username=root
password=******
```



## 3. applicationContext 수정

> dispatcher servlet이 사용자의 요청을 처리하기 위해 controller, ViewResolver 등을 처리한다면 
> applicationContext 는 web이 아닌 Services와 Repositories(DAO, VO 등)를 관리합니다. 위에서 작성한 Properties를 함께 사용합시다.

​    

#### WEB-INF/applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
	
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
    <!-- mybatis SqlSessionFactoryBean -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```



## DB 연결 테스트

> 재대로 추가가 되었는지 확인하기 위한 DB 연결을 테스트 작업을 진행할 것입니다. 



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
    public void testConnection() throws Exception {
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
```

**결과:**

```
 com.mysql.cj.jdbc.ConnectionImpl@4eeea57d
```



#### @Inject 가 안되는 경우

```xml
		 <dependency>
            <groupId>javax.inject</groupId>
            <artifactId>javax.inject</artifactId>
            <version>1</version>
        </dependency>
```



#### src/test/main/MyBatisTest.java

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MyBatisTest {
    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testFactory() {
        System.out.println("sqlSessionFactory:" + sqlSessionFactory);

        SqlSession session = sqlSessionFactory.openSession();
        System.out.println("session:" + session);
        session.close();
    }
}
```

결과:

```
sqlSessionFactory:org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@35f26e72
session:org.apache.ibatis.session.defaults.DefaultSqlSession@49d3c823
```



## 참고문헌

[알라딘: 마이바티스 프로그래밍 원리와 활용 -심익찬 지음](https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=90228495)

[절차대로 생각하고 객체로 코딩하기| Spring, Mybatis 연동](https://codevang.tistory.com/249)