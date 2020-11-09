# Controller?

> Controller는 MVC에서 Model과 View의 인터페이스 역할을 담당합니다.
>
> Client의 Request를 수신하고, Model에게 변경된 데이터를 적용하고, View를 선택하여 전달합니다.





## 구현 목표

> 우선 서비스 아주 간단한 시나리오를 생각해봅시다. 목표는 사용자(User)의 계정 찾기, 회원정보 요청 및 회원정보 수정을 처리하는 것입니다. 
>
> 이를 정리하면 다음과 같습니다.
>
> - 서비스 대상 user
> - 계정 찾기:FindID(name, phoneNum) : 이름과 전화번호를 통해 ID 검색
> - 회원정보 요청 : GetInfo(id): id를 이용해 User 정보 가져오기
> - 회원정보 수정: SetInfo(id, age): id를 이용해 회원정보 age 수정하기



### 프로젝트 위치

> 프로젝트 위치는 Service 이름(대상)을 기준으로 나누어 집니다.
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
    @Autowired
    private UserService service;
    
    //접근 url: /findid?name=HEON&phoneNum=010-000-0000
    @RequestMapping(value="/findId", method=RequestMethod.GET)
    public String findId(HttpServletRequest request) {
        String name = request.getParameter("name");
        String phoneNum = request.getParameter("phoneNum");
        
        String id = service.FindID();
        return id;
    }
    //접근 url: /getinfo?id=qwe123
    @RequestMapping(value="/getInfo", method=RequestMethod.GET)
    public String getInfo(@RequestParam String id) {
        
        
        return "";
    }
    //접근 url:/setinfo/qwe123/25
    @RequestMapping(value="setInfo/{name}/{age}", method=RequestMethod.POST)
    public void setInfo(@PathVariable("name") String name, @PathVariable("age") int age) {
        service.SetInfo(name, age)
    }
}
```



## 설명

> 우선 [Anotation](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Anotation%20%EC%A2%85%EB%A5%98.md)부터 확인하겠습니다. 



#### @Autowired

- 자동으로 객체를 주입해주는 Anotation 입니다.
- 기존은 @Bean과 XML 정의를 통해 IoC를 동작시켰지만 간단하게 @Autowired가 대신해줍니다.
- 즉 Controller의 생성자에서 직접 인스턴스를 생성할 필요가 없어집니다.



#### @Controller

- Spring MVC에서 Controller 객체 임을 명시해주는 Anotation 입니다.
-  사용자의 요청을 처리할 비즈니스 로직 이후 변경된 모델(데이터) 및 이동할 View의 정보를 DispatherServlet에 반환합니다.
- Bean으로 등록합니다.



#### @RequestMapping(value, RequestMethod[])

- 사용자의 요청에 대해 어떤 Controller, 어떤 Method가 처리할지 Mapping 하기 위한 Anotation.
- @RequestMapping의 value는 URL 값이고, RequestMethod[]는 HTTP Request Method(GET, POST, HEAD, DELETE 등) 값 입니다.
- viewName 생략 시 @RequestMapping의 path로 설정한 URL이 default viewName.
- **Spring 4.3** 이후 **RequestMethod**에 따라 **RequestMapping**를 **GetMapping(value="")** 와 **PostMapping(value="")** 로 대체할 수 있습니다.



### @RequestParam

- 사용자가 요청과 함께 전달한 값을 Mapping 하기 위한 Anotation.
- 생략이 가능합니다. 요청할 때 입력된 key 의 이름과 Controller 내의 Method의 매개변수 이름을 비교해서 값이 적용됩니다.



### @PathVariable

- url 경로를 변수로 써 사용할 수 있습니다.

#### 해설

>코드 펜스를 기준으로 설명하면,  사용자는 http://tiny-spring.com 사이트에서 사용자의 정보를 갖고 오고 싶다면, http://tiny-spring.com/getuserinfo 주소로 요청을 하게 됩니다.
>
>getuserinfo 로 접근할 경우 @RequestMapping의 value를 통해 동작할 Method를 찾아냅니다.



## 참고자료

[작성자: effortDev | Spring 개발 구성 및 파일 디렉토리 구조](https://shlee0882.tistory.com/127)