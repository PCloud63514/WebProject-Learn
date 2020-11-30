# SpringMVC Json 형식 반환

> 매번 jsp를 만들어 결과를 확인하는 과정은 번거롭고 Spring MVC에서 배우고자 하는 목표가 아닙니다.
>
> 이를 해결하기 위해 사용자의 요청에 대한 결과를 json으로 확인할 수 있도록 변경하려합니다.



## 1. 과정

> 우선 [2.Spring MVC-호출](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring 예제/2.Spring MVC-호출)  과정을 진행한 것을 기준으로 하겠습니다.

​     

### 1.1 pom.xml 수정하기

> pom.xml에 내용을 추가 후 빌드해줍니다.
>
> *[진행하다보면 생기는오류](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/TinySpring 예제/진행하다보면 생기는 오류.md)의 6,7 번을 읽어보면 jackson 버전에 의한 에러를 설명해줍니다. 혹 500error 등이 발생하면 참고해주세요.

```xml
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.12.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.0</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>2.12.0</version>
        </dependency>
```

​     

### 1.2 web.xml 수정하기

> web.xml 에서 jsp를 연결하는  ViewResolver를 제거 후 새로운 Converter를 추가합니다.

```xml
    <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"> <!-- 제거 -->
        <beans:property name="prefix" value="/WEB-INF/views/"/>
        <beans:property name="suffix" value=".jsp"/>
    </beans:bean>

==========================  <!-- 추가 -->
    <annotation-driven/>
```

​        

### 1.3 Controller 수정하기

> Spring 3.x 부터 사용 가능한 @ResponseBody, Spring 4.x 부터 사용 가능한 @RestController
>
> 둘 중 하나를 사용하여 결과를 json 형식으로 보여줄 것입니다.

​    

#### @RestController 예시

```java
@RestController
public class HomeController {
    @RequestMapping(value="/call", method= RequestMethod.GET)
    public Map<Object, String> call() {
        Map<Object, String> testMap = new HashMap<Object, String>();
        testMap.put("Test", "Test");
        return testMap;
    }
}
```

​     

#### @ResponseBody 예시

```java
@Controller
public class HomeController {
	@ResponseBody
    @RequestMapping(value="/call", method= RequestMethod.GET)
    public Map<Object, String> call() {
        Map<Object, String> testMap = new HashMap<Object, String>();
        testMap.put("Test", "Test");
        return testMap;
    }
}
```

​      

#### 결과

![image](https://user-images.githubusercontent.com/22608825/100642794-96915400-337c-11eb-9ee8-74a14cce1942.png)