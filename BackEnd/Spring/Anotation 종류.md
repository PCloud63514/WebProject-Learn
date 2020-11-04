# Anotation 이란

> 클래스 또는 클래스 내부(속성 및 메서드) 상단에 '@'와 함께 작성된 문법을 어노테이션 이라 합니다.
>
> Java5 부터 추가된 문법요소이다.   '주석'을 의미하며, Java Code에 주석처럼 작성하여 특수한 의미를 부여하는 키워드입니다.
>
> Compile  또는 Runtime 에 동작합니다. 



## Anotation List

| Anotation          | Description                                           | Location            |
| ------------------ | :---------------------------------------------------- | :------------------ |
| @Controller        | Spring MVC의 Controller 객체 임을 명시                | Class               |
| @RequestMapping    | 특정 URI에 매칭되는 Class나 Method임을 명시           | Class, Method       |
| @RequestParam      | 요청(request)에서 특정한 파라미터 값을 찾아낼 때 사용 | Parameter           |
| @RequestHeader     | 요청(request)에서 특정 HTTP 헤더 정보를 추출          | Parameter           |
| @PathVariable      | 현재의 URI에서 원하는 정보를 추출                     | Parameter           |
| @CookieValue       | 쿠키의 이름을 이용해 쿠키의 값 추출                   | Parameter           |
| @ModelAttribute    | 자동으로 해당 객체를 뷰까지 전달                      | Method, Parameter   |
| @InitBinder        | 파라미터를 수집해서 객체로 만들 경우 커스터마이징     | Method              |
| @ResponseBody      | Return Type이 HTTP의 응답 메시지로 전송               | Method, Return Type |
| @RequestBody       | 요청(request)문자열이 그대로 파라미터로 전달          | Parameter           |
| @repository        | DAO 객체                                              | Class               |
| @Service           | Service 객체                                          | Class               |
| @SesstionAttribute | 세션에서 모델의 정보를 유지하고 싶을 경우             | Class               |



[참고문헌]

[곰돌이 놀이터 어노테이션](https://helloworld-88.tistory.com/147)