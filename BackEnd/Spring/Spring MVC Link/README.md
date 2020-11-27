# Spring 학습서

SpringMVC 란 Spring의 웹 애플리케이션 개발을 위한 Spring Framework 의 MVC 모듈입니다.

이 페이지의 목표는 Spring MVC를 직접 만들어 보기 전에, 필요한 지식을 학습하는 것입니다..



*디렉토리에 다른 .md 파일이 많지만, 이 페이지 내에 링크를 전부 걸어두었기 때문에 별도로 찾을 수고를 줄였습니다.

## Spring 학습 전 용어

> Spring 을 사용하여 Web Service를 구현하기 전, 필수적으로 알고 있어야하는 지식을 모아 작성하였습니다.
>
> 한번에 전부 외우기 어렵다면, [TinySpring](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C)을 직접 만들어보며 확인하는 것도 좋은 방법입니다. 
>
> (최소 한번은 읽은 상태로 [TinySpring](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C)을 만드는 것을 권장합니다)



#### [MVC (model view controller)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC.md)

- MVC 패턴은 UI코드와 비즈니스 코드를 분리하여, 종속성을 줄이고 유지보수와 재사용성을 높이기 위한 패턴입니다.
- 처음 학습한다면, 이 페이지를 읽을 때 [MVC 페이지](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring%20MVC.md)를 옆에 띄워두고 하는 것이 좋습니다.



#### [IoC(Inversion of Control)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/IoC.md)

- 사전적 의미는 제어의 역전입니다.
- Container 라는 개념을 이해하기 위해선 숙지해야합니다.



#### [DI(Dependency Injection)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/DI.md)

- IoC 프로그래밍 모델을 구현하는 방식 중 하나
- Spring에서 IoC 라는 범용적인 의미를 넘어 명확한 의미를 부여하기 위한 이름.
- 컨테이너 차원에서 수많은 객체들의 의존관계를 파악하고 런타임 시점에도 동적으로 객체를 주입한 유연성 있는 프로그래밍 패턴



#### [AOP(Aspect Oriented  Programming)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/AOP.md)

- 관점 지향 프로그래밍



#### [Servlet](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Servlet.md)

- Client의 Request(요청)을 처리하고 처리 결과를 다시 Response(응답, 회신) 하는 기술.
- 사용자와 서버간의 통신



#### [DAO(Data Access Object)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dao.md)

- DB에 접근하여, 데이터를 조회 및 조작 하는 객체
- Service와 DB를 연결하는 관계로 SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공합니다.



#### [DTO(Data Transfer Object)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Dto.md)

- 계층간(Controller, Service, Dao 등) 데이터 교환을 위한 객체(java Beans 이다.) VO 라고 표현합니다.



#### [Anotation(@)](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Anotation%20%EC%A2%85%EB%A5%98.md)

- 클래스 또는 클래스 내부(속성 및 메서드) 상단에 '@'와 함께 작성된 문법을 어노테이션 이라 칭합니다..

- Java5 부터 추가된 문법요소이다.  '주석'을 의미하며, Java Code에 주석처럼 작성하여 특수한 의미를 부여하는 키워드입니다.
- Compile  또는 Runtime 에 동작합니다. 



## 위의 글을 전부 읽었다면..

> 웹을 처음 학습한다면 바로 이해가 되진 않을 것입니다.  구현해야하는 건 무엇이고, 프로젝트 구조는 어떻게 해야하고 다른 자료에서는 
>
> 로깅이나 롬복 등 사용하는데 이런건 어떻게 해야하나? 라는 질문이 있을 수 있습니다.
>
>  계속 진행하다보면 자연스레 해결될 것이 생각합니다. 이를 돕기 위해 글을 한번 씩 읽었다는 전제로 직접 따라해보는 TinySpring 예제를 준비했습니다.
>
> [TinySpring 예제](https://github.com/PCloud63514/WebProject-Learn/tree/master/BackEnd/Spring/TinySpring%20%EC%98%88%EC%A0%9C)의 글과 함께 위에 작성된 글도 함께 읽으면서 진행하면 수월할 것입니다.



> 정말 구현이 급하다면 SpringMVC(Controller, Model, Service, DAO, DTO, View)와 Anotation 만 읽어도 TinySpring을 진행에 문제는 없을 것 입니다.



###### [참고문헌]

[갓대희의 작은공간 String](https://goddaehee.tistory.com/156)

[곰돌이 놀이터 스프링 어노테이션](https://helloworld-88.tistory.com/147)