# Controller?

> Controller는 MVC에서 Model과 View의 인터페이스 역할을 담당합니다.
>
> Client의 Request를 수신하고, Model에게 변경된 데이터를 적용하고, View를 선택하여 전달합니다.





## 구현 방법

> 우선 서비스 시나리오를 생각해봅시다. 목표는 사용자(User)의 회원정보 요청 및 회원정보 수정을 처리하는 것입니다. 
>
> - 서비스 대상 user
> - 로그인 : Login
> - 회원정보 요청 : GetUserInfo



### 프로젝트 위치

> 프로젝트 위치는 서비스 명을 기준으로 나누어 집니다.
>
> src/main/java/com.cname.service.controller
>
> 여기선 com.pcloud.user.controller 디렉토리를 생성하겠습니다.



<img src="https://user-images.githubusercontent.com/22608825/98464587-4b65a480-2207-11eb-9557-2fc91480fec1.png" alt="image" style="zoom:150%;" />



### 구현

>방금 생성한 디렉토리에 Controller 클래스를 하나 생성해 줄 것입니다.
>
>서비스 이름에 맞춰 UserController.java 파일을 생성하겠습니다.



```java
package com.pcloud.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    
    @RequestMapping(value="/getuserinfo", method=RequestMethod.GET)
    public String GetUserInfo(@RequestParam String id) {
        return "";
    }
    
    @RequestMapping(value="setuserinfo/{name}/{age}", method=RequestMethod.POST)
    public void SetUserInfo(@PathVariable("name") String name, @PathVariable("age"), String age) {
        
    }
}
```



우선 [Anotation](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Anotation%20%EC%A2%85%EB%A5%98.md)부터 확인하겠습니다. 



#### @Controller

- Spring MVC에서 Controller 객체 임을 명시해주는 Anotation 입니다.
-  사용자의 요청을 처리할 비즈니스 로직 이후 변경된 모델(데이터) 및 이동할 View의 정보를 DispatherServlet에 반환합니다.
- Bean으로 등록합니다.



#### @RequestMapping(value, RequestMethod[])

- 사용자의 요청에 대해 어떤 Controller, 어떤 Method가 처리할지 Mapping 하기 위한 Anotation.
- @RequestMapping의 value는 URL 값이고, RequestMethod[]는 HTTP Request Method(GET, POST, HEAD, DELETE 등) 값 입니다.
- viewName 생략 시 @RequestMapping의 path로 설정한 URL이 default viewName.



### @RequestParam

- 사용자가 요청과 함께 전달한 값을 Mapping 하기 위한 Anotation.
- 생략이 가능하다. 요청할 때 입력된 key 의 이름과 Controller 내의 Method의 매개변수 이름을 비교해서 값이 적용된다.



### @PathVariable

- url 경로를 변수로 써 사용할 수 있습니다.

#### 해설

>코드 펜스를 기준으로 설명하면,  사용자는 http://tiny-spring.com 사이트에서 사용자의 정보를 갖고 오고 싶다면, http://tiny-spring.com/getuserinfo 주소로 요청을 하게 됩니다.
>
>getuserinfo 로 접근할 경우 @RequestMapping의 value를 통해 동작할 Method를 찾아냅니다.
>
>
>
>
>
>

## 참고자료

[작성자: effortDev | Spring 개발 구성 및 파일 디렉토리 구조](https://shlee0882.tistory.com/127)