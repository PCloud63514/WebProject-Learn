# Spring MVC 소개

SpringMVC 란 Spring의 웹 애플리케이션 개발을 위한 Spring Framework 의 MVC 모듈이다.

MVC, DTO, DAO는 링크 걸어서 상세 설명

## Spring 학습 전 용어

#### [MVC (model view controller)]()

MVC 패턴은 UI코드와 비즈니스 코드를 분리하여, 종속성을 줄이고 유지보수와 재사용성을 높이기 위한 패턴이다.

- ##### Model

  - 어플리케이션의 상태(데이터)와 비즈니스 로직을 담는 객체 JavaBeans

- ##### View

  - Model의 정보를 사용자에게 보여주는 UI 로직.  JSP 파일로 나타낸다.

- ##### Controller

  - Model과 View의 인터페이스 역할. 사용자의 요청을 수신하고 Model에 변경된 데이터를 적용하고, View를 선택하여 전달한다.



#### DispatcherServlet

#### JavaBeans

- 데이터를 표현하기 위해 자바로 작성된 소프트웨어 컴포넌트.
- JavaBean 규격에 따라 작성된 Java Class 를 JavaBeans 라고 한다.
- JvaBeans는 별다른 로직 없이 속성과 속성을 가져올 접근 가능한 프로퍼티를 갖고 있으면 된다.

```java
public class Character {
	private String cid;
	public String getCid() {
		return this.cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}
```



#### DI(Dependencty Injection)

#### AOP(Aspect Orientend Programming)

#### [Anotation(@)]()

- 클래스 또는 클래스 내부(속성 및 메서드) 상단에 '@'와 함께 작성된 문법을 어노테이션 이라 한다.

- Java5 부터 추가된 문법요소이다.  사전 의미는 '주석'을 의미하며, Java Code에 주석처럼 작성하여 특수한 의미를 부여하는 키워드다.
- Compile  또는 Runtime 에 동작한다. 



#### [DAO(Data Access Object)]()

- DB에 접근하여, 데이터를 조회 및 조작 하는 객체
- Service와 DB를 연결하는 관계로 SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공한다.



#### [DTO(Data Transfer Object)]()

- 계층간(Controller, Service, Dao 등) 데이터 교환을 위한 객체([java Beans](#javabeans) 이다.) VO 라고 표현한다.
- [java Beans](#javabeans) 이기 때문에 JavaBeans 를 이해 했다면 별 문제가 없다.



## Spring 특징

Spring을 시작할 때 알아야하는 특징과 단어가 있다.











[참고문헌]

[갓대희의 작은공간 String](https://goddaehee.tistory.com/156)

[곰돌이 놀이터 스프링 어노테이션](https://helloworld-88.tistory.com/147)