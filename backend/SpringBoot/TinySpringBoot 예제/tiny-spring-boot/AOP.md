# AOP(Aspect Oriented Programming)



- 모든 메소드의 호출 시간을 측정하고 싶다.
- 공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern) 분리
- 회원 가입 시간, 회원 조회 시간을 측정하고 싶다.



모든 메소드 처리에 대한 결과시간을 확인하고 싶다.

메소드는 비즈니스 로직이며, 서비스의 핵심 관심 사항이다.

처리에 대한 결과시간은 서비스 동작에 있어 핵심 사항은 아니지만, 모든 비즈니스 로직의 공통 관심 사항이다.



이렇게 두 가지의 클라이언트 및 목표를 바라보는 기능이 한 메소드 안에 포함되어 있으면

단일책임 원칙에 위배된다.(SOLID)

이는 곧 유지보수의 어려움으로 다가오며, 분리해야할 사항이다.





### 동작 방식

Spring만 적용된 상태에서는 SpringContainer 내부에서 MemberController가 MemberService를 의존하고 있다.

AOP가 적용되었다면 SpringContainer 내부에서 

의존 대상인 MemberService에 대한 ProxyMemberService를 만들고 대신 의존한다.

ProxyMemberService는 joinPoint.proceed()를 통해 실제 MemberService를 호출하고,

MemberController는 ProxyMemberService를 의존하게 된다.



* 이를 실제로 확인하고 싶으면 Proxy 대상 클래스를 getClass()로 찍어보면 알 수 있다.

물론 위의 예시는 Service만 AOP 대상인 경우이며, Controller, Service, Repository 전부 AOP 대상이라면 전부 동일하게 Proxy 객체가 생성된다.