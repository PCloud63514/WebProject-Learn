# Security와 OAuth2를 활용한 카카오 로그인





### dependencies

- org.springframework.boot:spring-boot-starter-oauth2-client
- org.springframework.boot:spring-boot-starter-security
- org.springframework.boot:spring-boot-starter-web





## Security Config 정의하기

사용자의 특정 URL에 대한 접근을 제한하거나, 확인하기 위해 Config를 정의해야합니다.

```
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	}

```

##### @Configuration

> 애플리케이션 실행 시, Config 설정 클래스로 로드하기 위한 어노테이션.

##### @EnableWebSecurity

> Spring Security 설정을 활성화 합니다.
>
> 즉 모든 엔드포인트에 접근 제한이 걸리게 되며, 이 때 부터 모든 엔트포인트는 WebSecurityConfigurerAdapter.configure 콜백 함수에 정의된 형태로 동작하게 됩니다.

##### WebSecurityConfigurerAdapter

> 엔드포인트에 접근제한을 지정하기 위한 클래스

##### configure(WebSecurity web)

> 서비스 전체에 영향을 미치는 설정을 지정하는 콜백함수.
>
> 특정 요청 거부, css, js 등에 대한 보안절차 생략, 리소스에 대한 csrf를 방지절차를 무시할 수 있습니다.
>
> 디버그 모드, 방화벽 설정을 커스텀하여 특정 Request를 거부할 수 있으며, static Resource에 설정을 주로 작성합니다.

##### configure(HttpSecurity http)

> HttpSecurity를 이용해 특정 http 요청에 대한 웹 기반 보안을 구성할 수 있는 콜백함수.
>
> 일반적인 http 취약성에 대한 방어가 필요한 모든 엔드포인트에 대한 제한을 구성할 수 있습니다.
>
> HttpSecurity에서 지원하는 authorizeRequest 메서드는 URL 패턴을 통한 HttpServletRequest접근에 대한 제한권한을 설정할 수 있습니다.

##### configure(AuthenticationManagerBuilder auth)

>AuthenticationManager()를 흭득하기 위한 콜백 함수.
>
>직접 구현할 필요는 보통 없고, Override한 경우 AuthenticationManagerBuilder를 사용하여 AuthenticationManager를 지정해야합니다.
>
>비밀번호 암호화 등 AuthenticationManager에 대해 이해도를 높이고 사용해야합니다.



##### HttpSecurity 호출 가능 함수

authorizeRequest() - URL 패턴을 통한 HttpServletRequest 접근에 대한 제한권한을 획득하는 함수.

antMatcher()

authorizeRequest().antMatchers()

permitAll()

anyRequest()

authenticated()

and()

formLogin()

logout()

##### HttpSecurity.permitAll

>지정된 엔드포인트들은 별다른 권한 없이 Anonymous로 접근할 수 있습니다.



### Spring Security는 기본적으로 /login을 제공한다.

Spring Security 종속성을 추가하고 SecurityConfig만 추가 후 아무것도 추가하지 않는다면, 

원하는 엔드포인트로 도달하지 못하고 /login 경로로 이동하게됩니다.

기본적으로 /login경로의 로그인 페이지와 /login?error 경로의 로그인 실패 페이지를 HttpSecurity를 상속받은 FormLoginConfigurer에서 제공합니다.

FormLoginConfigurer의 loginform() 메서드를 override하여 로그인 페이지 접근 url을 명시하면 기본으로 제공되는 로그인 페이지는 사라지게 됩니다. 

당연히 error, success, redirect 등 다양한 경로를 커스텀할 수 있습니다.