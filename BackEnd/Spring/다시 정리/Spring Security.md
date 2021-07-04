# Spring Security

Spring 기반 Application의 보안 기능을 제공하는 프레임워크.



## HTTP 요청 과정

1. 서버에 요청이 발생
2. DispatcherServlet에 진입 후 요청을 처리할 Controller 검색
3. Controller에서 요청을 처리 후 Model과 View Name을 DispatcherServlet에 전달
4. DispatcherServlet에서 전달받은 ViewName을 ViewResolver에게 전달
5. ViewResolver에서 View탐색 후 Model을 View에게 전달
6. Model이 반영된 View를 DispatcherServlet에 전달
7. 전달받은 View를 DispatcherServlet이 네트워크 응답.



## 처리 과정

일반적으로 Request가 발생하면 DispatcherServlet에 접근 후 처리할 Controller를 HandlerMapping에서 탐색합니다.

그 후 Controller가 요청을 처리하고 Model과 View Name을 DispatcherServlet에 전달합니다.

다시 DispatcherServlet은 전달받은 View이름을 ViewResolver에게 넘기고

View를 탐색합니다.

탐색되었을 경우 전달받은 Model을 View에게 전달하고 Model이 반영된 View를 다시 Dispatcher Servlet에게 전달합니다.

DispatcherServlet은 전달받은 View를 네트워크의 Response로 전달합니다.



