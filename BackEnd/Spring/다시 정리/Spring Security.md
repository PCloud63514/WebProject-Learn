# Spring Security

Spring 기반 Application의 보안 기능(인증, 인가)을 제공하는 프레임워크.



##### 기본 키워드

- 인증(Authentication): 해당 사용자가 적합한 사용자인지 확인하는 절차
- 인가(Authorization): 인증된 사용자가 요청한 자원에 접근 가능한지 결정하는 절차.
- 접근 주체(Pricipal):
- 비밀번호(Credential):



## HTTP 요청 과정

1. 서버에 요청이 발생
2. DispatcherServlet에 진입 후 요청을 처리할 Controller 검색
3. Controller에서 요청을 처리 후 Model과 View Name을 DispatcherServlet에 전달
4. DispatcherServlet에서 전달받은 ViewName을 ViewResolver에게 전달
5. ViewResolver에서 View탐색 후 Model을 View에게 전달
6. Model이 반영된 View를 DispatcherServlet에 전달
7. 전달받은 View를 DispatcherServlet이 네트워크 응답.



## HTTP 요청 과정 (Security)

1. Request 발생
2. AuthenticationFilter 에 접근
3. AuthenticationFilter는 Chain형태로 연결된 Filter들의 doFilter 메소드를 순서대로 호출
   1. 이때 호출되는 Filter가 대략 10가지가 넘으므로 핵심적으로 기억해야하는 Filter만 설명.
   2. SecurityContextPersistenceFilter : Security Context에 접근하는 역할.
   3. UsernamePasswordAuthenticationFilter: 사용자인증에 필요한 요청을 확인하고 인증을 진행하는 역할.
   4. FilterSecurityInterceptor: 권한부여와 권한
4. 인증처리를 담당하는 UsernamePasswordAuthenticationFilter 호출
   1. Custom 과정을 통해 SuccessHandler, FailureHandler를 설정 및 호출할 수 있다.
   2. 인증이 성공했다면 리턴값 UsernamePasswordAuthentication Token를 세션에 저장



지금 궁금한거

뭐부터 호출되고 어디를 설정하고 뭘 구현해야하고 상속받아야는지 정확히 알고 싶음.

CustomFilter (구현체 UsernamePasswordAuthenticationFilter) -> 

AuthenticationManager (ProviderManager) << 직접 구현하지 않아도 됨 -> 

AuthenticationProvider (인터페이스 AuthenticationProvider) or  (추상구현체 AbstractUserDetailsAuthenticationProvider) -> (UserDetailsService 주입 받음)

UserDetailsService -> Repository를 구현해서 UserDetails 반환



##### CustomAuthenticationFilter 

> UsernamePasswordAuthenticationFilter을 상속받은 클래스.
>
> HttpServletRequest에서 전달받은 정보를 이용하여 UsernamePasswordAuthenticationToken을 생성.
>
> 이때 생성한 Token은 아직 인증처리가 되지 않았으므로, 인증처리가 필요하다.
>
> 인증처리를 하기 위해서는 AuthenticationManager에서 인증처리할 AuthenticationProvider 구현체를 호출해야한다.
>
> AuthenticationProvider는 authenticate 함수에 인증처리 로직을 구현했으므로 이 함수에 위에서 생성한 Token을 전달하면 인증 과정을 수행할 수 있다.
>
> 이 과정에서 반환된 Authentication 객체를 반환하면 된다.
>
> return this.getAuthenticationManager().authenticate(token);

##### AuthenticationManager

>Interface이며 구현체는 ProviderManager.
>
>ProviderManager는 인증처리 로직이 포함된 AuthenticationProvider 인터페이스 구현체를 찾아 실행한다.

##### AuthenticationProvider

> 실제 인증로직이 동작하는 클래스.
>
> 구현한 AuthenticationProvider를 Manager에 등록하기 위해선 WebSecurityConfigurerAdapter를 상속받은 Config의 configure(AuthenticationManagerBuilder ab) 에 등록하면 된다.
>
> - WebSecurityConfigurerAdapter의 상위클래스에 AuthenticationManager가 존재하기 떄문에 등록이 가능하다.



## 호출 순서

Configure에서 아래를 설정

	- 접근 URL 제한
	- 로그인 form 추가
	- Filter가 동작 전에 동작시킬 Filter 추가

설정시킨 CustomFilter 동작 -> 

UsernamePasswordAuthenticationToken을 발급 ->

AuthenticationManager 에게 전달 ->

AuthenticationManager는 전달받은 요청을 처리하기 위한  AuthenticationProvider 구현체를 찾아 호출 -> 

전달받은 UsernamePasswordAuthenticationToken을 이용해 AuthenticationProvider에서 실제 인증처리 로직 호출. -> 







## Spring Security 모듈

##### SecurityContextHolder

> SecurityContext를 관리하는 객체

##### SecurityContext

> Authentication을 보관하는 객체

##### Authentication

> 접근하는 주체의 정보와 권한을 담은 인터페이스
>
> Authentication 인터페이스와 CredentialsContainer 인터페이스를 구현한 추상클래스인 AbstractAuthenticationToken을 사용한다.

##### UsernamePasswordAuthenticationToken

> AbstractAuthenticationToken을 상속한 클래스.
>
> Object 타입의 principal과 credentials를 생성자로 전달받으며, 이 둘은 일반적으로 ID와 Password의 역할을 담당한다.
>
> 인증 완료 전의 생성자와 후의 생성자 총 2개를 구현해야한다.

##### AuthenticationProvider

> 인증 전의 Authentication 객체를 받아 인증 로직을 처리한 후 인증이 완료된 객체를 반환하는 인터페이스
>
> AuthenticationManager에 등록되어있으며, Custom 구현할 경우 별도로 등록해야한다.

##### Authentication Manager

> 인증에 대한 관리를 담당하는 인터페이스
>
> Authentication Manager에 등록된 AuthenticationProvider를 통해 인증 로직을 처리한다.
>
> 이때 인증 상태를 유지하기 위해 세션에 보관하고, 실패에 대해선 AuthenticationException을 발생시킨다.

##### UserDetails

##### UserDetailsService

> 

##### Password Encoding

> 패스워드 암호화에 사용될 PasswordEncoder 구현체를 지정할 수 있다.

##### GratendAuthority

> 현재 사용자(principal)가 가지고 있는 권한을 의미한다.

