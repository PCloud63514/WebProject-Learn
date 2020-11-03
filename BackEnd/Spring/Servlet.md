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



**동작순서**

1. 사용자가 URL을 클릭하면 HTTP Request를 서블릿 컨테이너로 전송한다.
2. HTTP Request를 전달받은 Servlet Container는 HttpServletRequest, HttpServletResponse 두 객체를 생성한다.



## Servlet Container



[망나니개발자 [JSP]서블릿(Servlet)이란?](https://mangkyu.tistory.com/14)

[kohen's 기록 [Spring]Servlet이란?](https://kohen.tistory.com/29)