# Servlet?

Web 통신 과정에서 Client(사용자)의 Request(요청)을 처리하고 Response(응답)하는 기술.

쉽게 HTML의 GET POST 방식을 Java를 이용하여 구현한 방법을 의미합니다.

만약 사용자가 특정 정보를 **요청**하면 그에 대한 정보를 수집하고 **응답**하는 과정이 필요한데 이 통신과정을 담당하는 것이 Servlet 입니다.



**특징**

- Client의 요청에 대해 동적으로 작동하는 Web Application Component(구성요소)
- html을 사용하여 요청에 응답합니다.
- Java Thread를 통해 동작합니다.
- MVC 패턴 중 Controller로 이용됩니다.
- HTTP 프로토콜서비스를 지원하는 javax.servlet.http.HttpServlet 클래스를 상속받습니다. 
- HTML 변경 시 Servlet을 다시 컴파일해야합니다.



![image](https://user-images.githubusercontent.com/22608825/98032256-151ad480-1e57-11eb-8dec-722df9a23633.png)

​																																							이미지 출처: 망나니 개발자 [망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)]

**동작순서**

1. 사용자가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송합니다.
2. HTTP Request를 전달받은 Servlet Container는 HttpServletRequest, HttpServletResponse 두 객체를 생성합니다.
3. Web.xml 기반으로 사용자가 요청한 URL이 어느 Servlet에 대한 요청인지 찾습니다.
4. 해당 Servlet에서 service Method를 호출한 후 Client의 요청 종류(GET, POST)에 따라 doGet() 혹은 doPost()를 호출
5. doGet(), doPost() Method는 동적 페이지를 생성한 후 HttpServletResponse 객체에 응답을 보냅니다.
6. 응답이 끝나면 HttpServletRequest, HttpServletResponse 객체를 소멸시킵니다.



## Servlet Container

Servlet을 관리하는 Container를 칭한다. 대표적으로 Tomcat이 있습니다.

Servlet Container는 Client의 Request를 받아주고 Response 할 수 있도록 Web Server와 Socket을 만들어 통신합니다.



### 특징

* Web Server와의 통신 지원

  > 일반적인 소켓 통신과정의 listen, accept 등의 과정을 Servlet Container가 API로 제공하여 생략할 수 있다. 이를 통해 개발자는 Servlet의 비즈니스 로직에 대해 초점을 둘 수 있습니다.

* Servlet Life Cycle 관리

  > Container 라는 이름 답게 대상인 Servlet의 Life Cycle을 관리합니다. 
  >
  > Servlet Class를 읽고 인스턴스화 및 init(초기화) 메서드를 호출하고, Request가 들어오면 적절한 Servlet Method를 호출합니다.
  >
  > Garbage Collection 기능이 있어, Servlet의 수명이 다하면 자동으로 수집됩니다.

* Multi Thread 지원 및 관리

  >Servlet Container에게 Request가 올 때마다 새로운 Thread를 하나 생성합니다. 
  >
  >이 Thread를 통해 HTTP Service Method를 실행하고, 자동으로 죽습니다.
  >
  >서버에서 생성하는 Multi Thread 에 대해 알고 싶다면 Thread Pool 을 확인해보는 것이 좋습니다.

* 보안 관리

  >보안관리는 XML 배포 서술자에 작성되므로, 수정하더라도 다시 컴파일 할 필요가 없습니다.
  >
  >다시 컴파일을 해야하는 경우는 Java Source Code를 수정했을 때 입니다.



## Servlet LifeCycle

![image](https://user-images.githubusercontent.com/22608825/98035635-2ca88c00-1e5c-11eb-997e-cdaff71fd086.png)

​																																							이미지 출처: 망나니 개발자 [망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)]

1. 클라이언트의 요청이 들어오면 컨테이너는 해당 Servlet이 메모리에 있는지 확인하고, 없을 경우 init() 메서드를 호출하여 적재합니다.
2. init() 이 호출된 후 클라이언트의 요청에 따라 service() 메서드를 통해 요청에 대한 응답인 doGet() or doPost() 을 진행합니다. 이때 ServletContainer가 클라이언트의 요청 때 생긴 HttpServletRequest, HttpServletResponse에 의해 request와 response 객체가 제공됩니다.
3. 컨테이너가 Servlet에 종료 요청을 하면 destroy() 메서드가 호출됩니다.

ex) init()과 destroy() 같은 생성과 종료를 담당하는 메서드는 당연하게 한번만 호출됩니다. Servlet이 생성될 때 필요한 자원이나 종료될 때 함께 없애야하는 자원은 init()과 destroy() 메서드를 override 하여 직접 구현하면 됩니다.



###### [참고문헌]

[망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)

[kohen's 기록 [Spring]Servlet이란?](https://kohen.tistory.com/29)

[길은 가면, 뒤에 있다 [Servlet] 서블릿 컨테이너와 스프링 컨테이너](https://12bme.tistory.com/555)