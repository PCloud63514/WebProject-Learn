> 이 페이지를 읽기 이전에, Spring MVC와 Controller을 처음 학습한다면 아래의 링크를 읽고 진행해주세요.
>
> - [Spring MVC](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC.md)
>
> - [Controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Controller.md)

# Service?

> Spring MVC에서 M(Model)에 속해있으며, 비즈니스 로직을 수행하는 역할을 Service 라 합니다.
>
> Service는 Interface 파일로, ServiceImpl이라는 구현체 class가 필요합니다.



# ServiceImpl?

>Service의 구현체를 ServiceImpl 이라 합니다. 자바에선 인터페이스의 구현 클래스 뒤에 Impl을 붙힙니다.
>
>Controller에서 사용자의 요청을 받았다면,  그 요청을 알맞게 처리하는 것이 목표입니다. 예로 사용자 정보 요청, 정보 수정 등이 있습니다.
>
>Java 프로그래밍을 어느정도 해보았으면, 귀찮은 인터페이스를 구현해야하는가? Spring MVC는 인터페이스가 없으면 동작하지 않는가? 
>
>라는 질문을 갖을 수  있습니다. 이를 검색해보면 이와 비슷한 고민을한 개발자들의 글을 볼 수 있습니다. 
>
>Java에서 Interface를 사용하는 이유는 OOP의 추상화, 캡슐화, 상속을 명확히 하고 interface만 보더라도 어떤 기능이 구현되어 있는지 유추할 수 있기 
>
>때문입니다.  동일하게 비즈니스 로직을 구현한 ServiceImpl의 경우 순수 Java 객체로 구현되어 이를 지키기 위해 구현한다 생각하면 됩니다.
>
> 
>
>단 Service 와 ServiceImpl이 1:1 구조라면 당연히 구현의 필요성이 떨어지게(귀찮은 이유!) 됩니다.
>
>서비스의 크기가 크면 자연스레 1:N 구조가 만들어 질 것이고 이 때 Interface를 생성하여 관리하는 것이 좋습니다.



## 구현 목표

>목표는 사용자(User)의 계정 찾기, 회원정보 요청 및 회원정보 수정을 처리하는 것입니다. 
>
>이를 정리하면 다음과 같습니다.
>
>- 서비스 대상 user
>- 계정 찾기:FindID(name, phoneNum) : 이름과 전화번호를 통해 ID 검색
>- 회원정보 요청 : GetInfo(id): id를 이용해 User 정보 가져오기
>- 회원정보 수정: SetInfo(id, age): id를 이용해 회원정보 age 수정하기



## 프로젝트 위치

>프로젝트 위치는 Service 이름(대상)을 기준으로 나누어 집니다.
>
>src/main/java/com.cname.service.service
>
>여기선 com.pcloud.user.service 디렉토리를 생성하겠습니다.



## 구현

```java
package com.pcloud.user.service;

@Service
public class UserService {
    @Autowired
    UserDao dao;
    
    public String FindID(String name, String phoneNum) {
        System.out.println("UserService: FindID Call");
        System.out.println("name:" + name);
        System.out.println("phoneNum:" + phoneNum);
        
        String id = dao.userSelect(name, phoneNum);
        return id;
    }
    
    public User GetInfo(String name) {
        System.out.println("UserService: GetInfo Call");
        System.out.println("name:" + name);
        
        User user = dao.userSelect(name);
        return user;
    }
    
    public String SetInfo(String name, int age) {
        System.out.println("UserService: SetInfo Call");
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        
        dao.userUpdate(name, age);
    }
}

```



### 설명

> @Autowired Anotation은 Controller에서 설명했으므로, 생략합니다.
>
> 위의 코드처럼 Service는 비즈니스 로직 코드를 작성하며, Dao 즉 DB에 접근하는 객체를 호출할 수 있습니다.



#### @Service

- Spring MVC에서 Service 객체 임을 명시해주는 Anotation 입니다.