# Spring 학습서

SpringMVC 란 Spring의 웹 애플리케이션 개발을 위한 Spring Framework 의 MVC 모듈이다.

이 페이지의 목표는 Spring MVC를 직접 만들어 보기 전에, 필요한 지식을 학습하는 것이다.



*디렉토리에 다른 .md 파일이 많지만, 이 페이지 내에 링크를 전부 걸어두었기 때문에 별도로 찾을 수고를 줄였습니다.

## Spring 학습 전 용어

> Spring 을 사용하여 Web Service를 구현하기 전, 필수적으로 알고 있어야하는 지식을 모아 작성하였다.
>
> 한번에 전부 외우기 어렵다면, [TinySpring](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C)을 직접 만들어보며 확인하는 것도 좋은 방법이다. 
>
> (최소 한번은 읽은 상태로 [TinySpring](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C)을 만드는 것을 권장합니다)



#### [MVC (model view controller)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC.md)

- MVC 패턴은 UI코드와 비즈니스 코드를 분리하여, 종속성을 줄이고 유지보수와 재사용성을 높이기 위한 패턴이다.
- 처음 학습한다면, 이 페이지를 읽을 때 [MVC 페이지](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC.md)를 옆에 띄워두고 하는 것이 좋다.



#### Servlet

- Client의 Request(요청)을 처리하고 처리 결과를 다시 Response(응답, 회신) 하는 기술.



#### Container

- 

#### DI(Dependencty Injection)

- Spring Container 가 지원하는 핵심 개념



#### AOP(Aspect Orientend Programming)

- 



#### [DAO(Data Access Object)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dao.md)

- DB에 접근하여, 데이터를 조회 및 조작 하는 객체
- Service와 DB를 연결하는 관계로 SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공한다.



#### [DTO(Data Transfer Object)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dto.md)

- 계층간(Controller, Service, Dao 등) 데이터 교환을 위한 객체(java Beans 이다.) VO 라고 표현한다.

#### DispatcherServlet



#### [Anotation(@)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Anotation%20%EC%A2%85%EB%A5%98.md)

- 클래스 또는 클래스 내부(속성 및 메서드) 상단에 '@'와 함께 작성된 문법을 어노테이션 이라 한다.

- Java5 부터 추가된 문법요소이다.  사전 의미는 '주석'을 의미하며, Java Code에 주석처럼 작성하여 특수한 의미를 부여하는 키워드다.
- Compile  또는 Runtime 에 동작한다. 





## Spring 특징

Spring을 시작할 때 알아야하는 특징과 단어가 있다.











###### [참고문헌]

[갓대희의 작은공간 String](https://goddaehee.tistory.com/156)

[곰돌이 놀이터 스프링 어노테이션](https://helloworld-88.tistory.com/147)