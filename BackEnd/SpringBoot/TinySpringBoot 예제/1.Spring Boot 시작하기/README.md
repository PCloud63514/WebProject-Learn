# Spring Boot 시작하기

>* Spring MVC 이해를 전제로 작성한 페이지입니다.
>
>
>
>



[Spring Initializr](https://start.spring.io/)

<img src="https://user-images.githubusercontent.com/22608825/101628745-9b809280-3a63-11eb-8e98-648bf4cd19ef.png" alt="image" style="zoom:80%;" />





![image](https://user-images.githubusercontent.com/22608825/101628929-e7cbd280-3a63-11eb-9ed4-d5ac1e97b0bd.png)





src/main/resources/application.properties

```properties
server.port=8888
```





src/main/java/com.pcloud.TinySpringBoot/TinySpringBootApplication.java

```java
package com.pcloud.TinySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinySpringBootApplication.class, args);
	}

}
```



![image](https://user-images.githubusercontent.com/22608825/101629188-4c872d00-3a64-11eb-8200-4454f94f2070.png)



![image](https://user-images.githubusercontent.com/22608825/101629260-6b85bf00-3a64-11eb-8532-21ba2ace2580.png)