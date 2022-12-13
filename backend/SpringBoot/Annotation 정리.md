##### @PostConstruct

>Spring 동작 준비가 끝났을 때 자동 호출 시켜주는 어노테이션.
>
>Transactional 과 같이 변경사항이 적용이 안되는 경우가 있으므로, 별도의 bean객체를 호출하는 편이 좋다.

##### @Autowired

> Spring Container에서 객체를 찾아서 주입함
>
> 보통 생성자에 작성하는 것이 일반적이고, 변수에 직접 작성할 수도 있긴하다.
>
> 생성자에 작성할 때, 받는 인자가 1개라면 @Autowired를 생략할 수 있다.



##### @ComponentScan

> Spring의 DI를 위한 객체 생성 방식 중 1가지
>
> @Component 등의 Annotation이 붙으면 ComponentScan 대상이 되며 이는 Spring Container에 bean객체로 등록된다.
>
> @Component, @Service, @Repository, @Controller, @Configuration이 붙은 클래스 Bean을 검색하여 Context에 bean 등록을 해주는 Annotation.
>
> ComponentScan은 @SpringBootApplication에서 사용되고 있으며, Spring이 실행 시 @SpringBootApplication을 사용 중인 Application이 있는 경로를 시작으로 @Component를 bean객체로 등록시킨다.
>
> 즉 Application 보다 상위 경로에 있는 @Component는 bean객체로 등록되지 않는다.



##### @EnableAutoConfiguration

> Spring Application Context를 만들 때 자동으로 설정하는 기능을 활성화한다.
>
> classpath의 내용에 기반해서 자동 생성한다.
>
> 예로 tomcat-embed-core.jar가 존재하면 톰캣 서버가 setting 된다.

##### Configuration

> Configuration을 클래스에 적용하고 @Bean을 해당 클래스의 메소드에 적용하면 @Autowired로 Bean을 호출할 수 있다.

##### Required

> Setter 메서드에 적용 시 Bean 생성에 필수 Property 임을 알려준다.



##### @Qualifier

> @Autowired와 같이 쓰이며, 같은 타입의 Bean 객체가 있을 때 해당 아이디를 적어 원하는 Bean이 주입될 수 있도록 한다.



##### @Resource

> @Autowired와 마찬가지로 Bean객체를 주입한다.
>
> 단 Autowired는 Type 기반, Resource는 Name 기반으로 주입한다.



##### @PostConstruct, @PreConstruct

> 의존객체를 생성한 후 초기화 작업을 위해 객체 생성 전/후에 실행해야할 메소드를 지정한다.



##### @RequestMapping

> 요청 URL을 어떤 메서드가 처리할지 mapping한다.
>
> Controller 및 Controller Method에 적용한다.
>
> @RequestMapping("/list"), @RequestMapping("/home", "/about") 등



##### @PathVariable

> 요청 경로에서 값을 가져온다.
>
> ex) /home/{data}

##### @RequestParam

> 요청의 data
>
> ex) /home?name=AAA

##### @RequestBody

> 요청 data 형태가 json 형태일 경우 자동으로 Object 변수에 주입해준다.
>
> 설정한다면 json 외의 형태도 가능하다.

##### @ResponseBody

> 요청 결과를Json 형태로 반환한다.



##### @Controller

> View를 반환하는 Controller

#####  @RestController

> SpringMVC Controller에 @ResponseBody가 추가된 형식
>
> 요청에 대한 response를 Json형태로 객체 데이터를 반환할 수 있다.





@RequestMapping

##### @GetMapping

>@RequestMapping(Method=RequestMethod.GET)과 동일하다
>
>@PostMapping, @PutMapping, @PatchMapping, @DeleteMapping 유추 가능



@Api

@ApiOperation



@ApiParam



##### @Configuration

> Spring 동작시 동작할 설정파일을 지정하는 Annotation.

##### @Bean

> SpringContainer에 등록될 Bean객체임을 알려주는 Annotation.
>
> 보통 @Component를 통해 Bean객체를 등록하지만
>
> @Configuration을 통하거나 직접 객체를 생성해야할 때 사용된다.
>
> 단 Contoller는 ~~ 해서 ComponentScan되도록 냅둔다.



##### @SpringBootTest

> TestCode에서 SpringContainer를 사용할 수 있도록 하는 어노테이션.

##### @Transactional

> DB Transaction을 시작 및 관리하는 어노테이션.
>
> 일반적인 서비스에선 위에 설명된 대로 동작하지만, Test Code에서 사용되었을 경우 테스트 종료 후 요청 커밋을 rollback 하기 때문에 실제 DB의 변경이 이루어지지 않는다.
>
> 각 테스트(메서드) 별로 Transaction 시작 및 롤백을 반복한다.
>
> 즉 테스트가 6번이면 6번 반복된다.

##### @Commit

> DB 요청의 결과를 무조건 Commit 하도록 하는 어노테이션



---

### Domain, Repository에 사용되는 Annotation.

##### @Entity

> 테이블과 링크될 클래스를 지정.
>
> 로우카멜 네이밍의 Entity클래스를 로우스네이크 네이밍으로 테이블의 이름을 매칭
>
> ex) HelloWorld -> hello_world

##### @Id

> 해당 테이블의 primary key 필드임을 명시.



##### @GeneratedValue

>PK의 생성 규칙을 지정
>
>DB에서자동으로 값을 생성해줄 때 사용되는 Annotation.
>
>@Id @GeneratedValue(strategy = GenerationType.IDENTITY)

##### @Column

> db컬럼 명이 domain 변수명과 다를 경우 매핑해주는 용도.
>
> @Column(name="username")

##### @JoinColumn

##### @Enumerated(EnumType.STRING or ORDINAL)

##### OneToOne(fetch=FetchType.LAZY or EAGER) : 1대 1관계

##### ManyToOne(fetch=FetchType.LAZY or EAGER) : 다대 1 관계

##### OneToMany(fetch=FetchType.LAZY or EAGER) : 1대다 관계

> 기본적으로 LAZY 이다.

##### ManyToMany(fetch=FetchType.LAZY or EAGER): 다대다 관계

> 위의 fecth 방식 중 하나인 LAZY는 지연 로딩이라한다.
>
> 지연로딩은 DB로부터 직접적으로 Data를 갖고 오는 것이 아닌 ~ 하는 것이다.
>
> 이 때 Data가 삽입 되기 전에는 hibernate가  Proxy객체를 생성하여 null을 방지한다.
>
> 단 이 상태에서  json  형태로 response 할 경우 에러가 발생한다.
>
> Hibernate5Module를 설치하여 해결할 수도 있지만 일반적으로 이런 문제는 Entity가 외부에 노출되어 생기는 문제로
>
> 설치말고도 해결법은 많다.
>
> EAGER를 사용하지 않는 이유는 jpql이 사용될 때 성능 최적화가 되지 않는다.(복잡한 쿼리에는 성능최적화가 되지 않는다.)
>
> n+1 문제는 Lazy와 동일하게 발생한다.



##### @PersistenceContext

> EntityManager 를 자동 생성 및 주입

---

### AOP

##### @Aspect

##### @Around: 관심사항이 무엇인지 지정하는 어노테이션.



---

@Embeddable

---

### javax.Validation (org.springframework.boot:spring-boot-starter-validation)

##### @Valid : 유효성을 체크를 내타내는 어노테이션.

##### @NotEmpty : Null을 허용하지 않는 어노테이션.

##### @NotNull: null 값이 아닐 경우 허용

##### @AssertFalse : false 값만 허용

##### @AssertTrue: true 값만 허용

##### @DecimalMax(value=) : 지정된 값 이하의 실수만 허용

##### @DecimalMin(value=): 지정된 값 이상의 실수만 허용

##### @Digits(integer=, fraction=): 대상 수가 지정된 정수와 소수 자리수보다 적을 경우 허용

##### @Future: 대상 날짜가 현재보다 미래일 경우만 허용

##### @Fast: 대상 날짜가 현재보다 과거일 경우만 혀용

##### @Max(value): 지정된 값보다 아래일 경우만 허용

##### @Min(value): 지정된 값보다 이상일 경우만 허용

##### @Null: Null만 허용

##### Pattern(regex=, flag=): 해당 정규식을 만족할 경우만 허용

##### Size(min=,max=): 문자열 또는 배열이 지정된 값 사이일 경우 허용

---

### Jackson (com.fasterxml.jackson.annotation)

##### @JsonIgnore : Json형태의 Response에서 제외시킨다.



---

## Security

##### @EnableWebSecurity

> 내부에 Configuration 존재함

## Lombok

##### @Getter

##### Setter 

##### @Slf4j: Logger 사용

>Logger log = LoggerFactory.getLogger(getClass()); 코드와 동일하다.

##### @AllArgsConstructor

>모든 멤버필드의 변수를 자동으로 Autowired 가 적용된 Constructor를 생성한다.

##### @RequiredArgsConstructor

> 초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 대해 생성자를 생성해준다.
>
> 의존성 주입을 편하게 하기 위해 사용된다.
>
> 즉 @Autowired 없이도 의존성 주입이 가능하다



[스프링부트 애노테이션 정리, annotation 간단 요약 (자세한 내용은 검색해서 확인하고 용도 파악하기) (tistory.com)](https://jeong-pro.tistory.com/151)