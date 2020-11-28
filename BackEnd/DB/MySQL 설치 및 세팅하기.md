# MySQL 설치 및 세팅하기

> MySQL 설치와 기본적인 쿼리 및 테이블 생성을 진행해볼 것입니다.



## 1. Download

> 이 페이지는 Windows10을 기준으로 진행하고 있습니다.
>
> [MySQL :: Download MySQL Installer](https://dev.mysql.com/downloads/windows/installer/) 링크에서 설치 파일을 다운로드 받아주세요.
>
> 두 파일은 web 설치 버전과 프로그램 설치 파일 의 차이입니다. 차이는 없습니다.
>
> (필자는 Community - 8.0.22로 진행하겠습니다. 
> 참고로 Download를 클릭 시 로그인 및 가입 화면이 나옵니다. 아래 No thanks, just start my download. 를 클릭하여 과정을 생략합시다.)

<img src="https://user-images.githubusercontent.com/22608825/100519896-2056eb00-31de-11eb-82d3-fc6cc4c7cfc2.png" alt="image" style="zoom: 67%;" />



### 과정(gif)

> Execute 나 Next, finish 를 누르는 것이 반복되고 중간에 password 지정만 주의해주세요.

![mysql](https://user-images.githubusercontent.com/22608825/100522325-299b8400-31ed-11eb-805d-6534da010072.gif)

​    

​     

## 2. 환경변수 설정

> 시스템 환경 변수를 설정해줄 차례입니다. MySQL을 설치한 경로로 이동한 뒤 bin 폴더 까지의 경로를 복사해주세요.
>
> **C:\Program Files\MySQL\MySQL Server 8.0\bin**
>
> 위 경로를 시스템 변수의 Path에 추가해줍니다.

​      

![image](https://user-images.githubusercontent.com/22608825/100522623-ceb65c80-31ed-11eb-95d1-dacd1693d5a6.png)



## 3. 확인

>위 과정들이 재대로 이루어졌는지 확인하기 위해 cmd 에서 아래의 명령어를 입력해주세요.

```
mysql --version
```

​       

![image](https://user-images.githubusercontent.com/22608825/100522695-25239b00-31ee-11eb-97dc-b09f4ca7be68.png)



