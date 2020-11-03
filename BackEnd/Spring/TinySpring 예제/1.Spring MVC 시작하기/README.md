# Spring MVC 시작하기

> 이 페이지에선 Spring MVC 프로젝트를 직접 생성하는 과정을 진행할 것입니다.

- IDE IntelliJ



## New Project

##### 1. Maven 선택하기

> Create from archetype 을 해제 하고 Next를 클릭.

![image](https://user-images.githubusercontent.com/22608825/97991188-6cec1800-1e24-11eb-9c6c-468868491e69.png)



##### 2. GroupId & ArtifactId

> GroupId 와 ArtifactId를 입력한다.  ([Maven]()에서 설명)
>
> groupId: 프로젝트를 생성하는 조직의 고유 ID
>
> artifactId: 프로젝트 build 시 파일 대표 이름 (프로젝트 이름이라 생각하면 편하다.)

![image](https://user-images.githubusercontent.com/22608825/97991523-eab02380-1e24-11eb-9c66-9b9cde3a93a5.png)



##### 3. Project Name & Project Location

> artifactId 와 동일한 이름을 사용하는 것을 권장한다.

![image](https://user-images.githubusercontent.com/22608825/97992013-7cb82c00-1e25-11eb-964a-734ae0ddc00c.png)



##### Project 구조 화면

> 아직 Spring MVC를 추가하지 않은 상태이다. 
>
> 최상단의 TinySpring을 오른쪽 클릭하여,  Add Framework Support... 를 클릭한다.

![image](https://user-images.githubusercontent.com/22608825/97992665-5941b100-1e26-11eb-98be-1b7267f87a23.png)

> 좌측의 Spring MVC를 찾아서 선택한 후 적용하면 자동으로 필요 Library를 설치한다.

<img src="https://user-images.githubusercontent.com/22608825/97993084-ec7ae680-1e26-11eb-9ba3-f8be838a23cc.png" alt="image" style="zoom:80%;" />



> 전부 설치된 화면이다.

![image](https://user-images.githubusercontent.com/22608825/97993254-2815b080-1e27-11eb-8af7-fd234018ea73.png)



## 프로젝트 구조

#### src/main/java

> .java 파일이 작성되는 곳. Spring MVC에서 Servlet 구조를 만들어 주기 때문에, 별도의 Servlet을 만들 필요 없이 비즈니스에 맞춰 작성하면 된다.

#### src/main/resources

> .java 

#### src/test/java

