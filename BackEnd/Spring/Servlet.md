# Servlet?

Web 통신 과정에서 Client(사용자)의 Request(요청)을 처리하고 Response(응답)하는 기술.

쉽게 HTML의 GET POST 방식을 Java를 이용하여 구현한 방법을 의미한다.



**특징**

- Client의 요청에 대해 동적으로 작동하는 Web Application Component(구성요소)
- html을 사용하여 요청에 응답한다.
- Java Thread를 통해 동작한다.
- MVC 패턴 중 Controller로 이용된다.
- HTTP 프로토콜서비스를 지원하는 javax.servlet.http.HttpServlet 클래스를 상속받는다. 
- HTML 변경 시 Servlet을 다시 컴파일해야한다.



![image](https://user-images.githubusercontent.com/22608825/98032256-151ad480-1e57-11eb-8dec-722df9a23633.png)

​																																							이미지 출처: 망나니 개발자 [망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)]

**동작순서**

1. 사용자가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송한다.
2. HTTP Request를 전달받은 Servlet Container는 HttpServletRequest, HttpServletResponse 두 객체를 생성한다.
3. Web.xml 기반으로 사용자가 요청한 URL이 어느 Servlet에 대한 요청인지 찾는다.
4. 해당 Servlet에서 service Method를 호출한 후 Client의 요청 종류(GET, POST)에 따라 doGet() 혹은 doPost()를 호출
5. doGet(), doPost() Method는 동적 페이지를 생성한 후 HttpServletResponse 객체에 응답을 보낸다.
6. 응답이 끝나면 HttpServletRequest, HttpServletResponse 객체를 소멸한다.



## Servlet Container

Servlet을 관리하는 Container를 칭한다. 대표적으로 Tomcat이 있다.

Servlet Container는 Client의 Request를 받아주고 Response 할 수 있도록 Web Server와 Socket을 만들어 통신한다.



### 특징

* Web Server와의 통신 지원

  > 일반적인 소켓 통신과정의 listen, accept 등의 과정을 Servlet Container가 API로 제공하여 생략할 수 있다. 이를 통해 개발자는 Servlet의 비즈니스 로직에 대해 초점을 둘 수 있다.

* Servlet Life Cycle 관리

  > Container 라는 이름 답게 대상인 Servlet의 Life Cycle을 관리한다. 
  >
  > Servlet Class를 읽고 인스턴스화 및 init(초기화) 메서드를 호출하고, Request가 들어오면 적절한 Servlet Method를 호출한다.
  >
  > Garbage Collection 기능이 있어, Servlet의 수명이 다하면 자동으로 수집된다.

* Multi Thread 지원 및 관리

  >Servlet Container에게 Request가 올 때마다 새로운 Thread를 하나 생성한다. 
  >
  >이 Thread를 통해 HTTP Service Method를 실행하고, 자동으로 죽는다.
  >
  >서버에서 생성하는 Multi Thread 에 대해 알고 싶다면 Thread Pool 을 확인해보는 것이 좋다.

* 보안 관리

  >보안관리는 XML 배포 서술자에 작성되므로, 수정하더라도 다시 컴파일 할 필요가 없다.
  >
  >다시 컴파일을 해야하는 경우는 Java Source Code를 수정했을 때다.



## Servlet LifeCycle

![image](https://user-images.githubusercontent.com/22608825/98035635-2ca88c00-1e5c-11eb-997e-cdaff71fd086.png)

​																																							이미지 출처: 망나니 개발자 [망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)]



###### [참고문헌]

[망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)

[kohen's 기록 [Spring]Servlet이란?](https://kohen.tistory.com/29)