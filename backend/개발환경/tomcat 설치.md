# Tomcat 설치

> 웹개발을 위해 톰캣 설치해볼 것
>
> 우선 JDK 설치가 완료 되어 있어야합니다. 이 페이지에선 jdk8을 설치를 기준으로 합니다.





## Tomcat?

Linux Server 나 Web Server를 개발하고 싶어 검색하면 가장 우선적으로 나오는 것이 바로 Apache Tomcat 입니다.



우선 Apache와 Tomcat에 대해 알 필요가 있습니다.

### Apache

> Web Server로 불리며, 클라이언트의 요청이 왔을 때만 응답하는 정적 웹페이지에 사용됩니다.
>
> - 클라이언트 요청(Post, Get 등) 이 왔을 때만 응답
> - 정적 데이터만 처리(HTML, CSS, Image 등)

### Tomcat

>동적인 웹을 만들기 위한 웹 컨테이너, Servlet Container 라고 불리며, Web Server에서 정적으로 처리해야할 데이터를 제외한
>
>JSP, ASP, PHP 등을 Tomcat이 처리합니다.
>
>- WAS(Web Application Server)
>- container, web container, servlet container 라고 부름
>- JSP, servlet 처리, HTTP 요청 수신 및 응답
>- Apache만 사용하면 정적 웹페이지만 처리하기 때문에 처리속도가 빠르고 안정적.



## 설치 방법

1. **[http://tomcat.apache.org](http://tomcat.apache.org/)** 해당 경로 접속.
2. 좌측 Download 탭에서 Tomcat 8 클릭
3. 32-bit/64-bit Windows Service Installer 클릭



다운로드가 완료되었다면 아래의 이미지를 따라 설치하면 됩니다.


![tomcat 설치](https://user-images.githubusercontent.com/22608825/98204751-f82af200-1f79-11eb-8961-c43c4920411e.gif)





참고문헌

[NoonGam 아파치 톰캣이란?](https://wodonggun.github.io/wodonggun.github.io/study/%EC%95%84%ED%8C%8C%EC%B9%98-%ED%86%B0%EC%BA%A3-%EC%B0%A8%EC%9D%B4.html)

