# SpringMVC 동작 순서 및 컴포넌트

> [Spring MVC]()에서 모델의 구성과 위치를 확인하였다면, 이번엔 좀 더 상세하게
>
> 웹 브라우저에서 요청이 들어오면 무엇이 처리하고, 응답하는 과정을 거치는지 좀 더 상세히 확인하겠습니다.



![image](https://user-images.githubusercontent.com/22608825/98907962-03929600-2503-11eb-9fd0-42394fded5c7.png)

[이미지 출처: [minjara 스프링 MVC 기본 흐름과 주요 컴포넌트](http://blog.naver.com/PostView.nhn?blogId=roropoly1&logNo=221163257713&parentCategoryNo=&categoryNo=32&viewDate=&isShowPopularPosts=false&from=postView)]



### 구성요소 역할

| 구성요소                                                     | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| DispatcherServlet                                            | Client의 요청을 전달받는다. Controller에게 Client의 요청을 전달하고, <br />Controller가 Return한 결과 값을 View에 전달하여 필요한 응답을 생성합니다. |
| HandlerMapping                                               | Client의 요청 URL을 어떤 Controller가 처리할지 결정합니다.   |
| HandlerAdapter                                               | DispatcherServlet의 처리 요청을 변환하여 Controller에게 전달하고, <br />Controller의 응답 결과를 DispatcherServlet이 요구하는 형식으로 변환합니다.<br />브라우저의 캐시 등의 설정도 함께 담당합니다. |
| [Controller](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/Spring/Spring MVC Link/Controller.md) | Client의 요청을 처리한 뒤 결과를 Return한다. 응답 결과에서 보여줄 데이터를 Model에 담아 전달합니다. |
| ModelAndView                                                 | Controller가 처리한 결과 정보 및 View 선택에 필요한 정보를 담습니다. |
| ViewResolver                                                 | Controller의 처리 결과를 보여줄 View를 선택합니다.           |
| [View]()                                                     | Controler의 처리 결과 화면을 생성한다. JSP나 Velocity 템플릿 파일 등을 이용해서 Client에 응답 결과를 전송합니다. |



## 참고문헌

[minjara 스프링 MVC 기본 흐름과 주요 컴포넌트](http://blog.naver.com/PostView.nhn?blogId=roropoly1&logNo=221163257713&parentCategoryNo=&categoryNo=32&viewDate=&isShowPopularPosts=false&from=postView)