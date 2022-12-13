# Spring MVC-slf4j & Log4j2 적용

> 이 페이지는 [2.Spring MVC-호출]() 를 진행한 것을 기준으로 합니다.





## Logging System

Runtime 중 발생하는 Event나 통신 과정의 Message 등을 기록한 파일 즉 **로그**를 자동으로 제공하는 시스템을 의미합니다. 

Java에서 기본으로 제공하는 Log 기능은 심각함(SEVERE), 경고(WARNING), 정보(INFO) 등 을 나타내며, Log의 수준을 레벨 별로 설정이 가능합니다.

   

## slf4j

Java Logging System을 쉽게 적용하기 위해 제작된 Library 입니다. Facade Pattern 및 추상화를 통한 로깅 기능을 제공합니다.

slf4j의 자체 Log 기능을 사용하는 목적보다, 유지보수를 목적으로 적용하는 Library 입니다.

slf4j 는 Java의 Logging Framework들의 추상체(facade) 역할을 담당합니다. Java의 Interface 역할을 떠올리면 편하며, 이 이점을 통해

적용 중인 Logging Framework를 다른 Logging으로 변경하더라도 단순히 maven이나 gradle의 의존성만 변경하면 손쉽게 변경되며

소스 코드의 변화도 필요하지 않습니다.

- Logging System 용 Library
- 다양한 Java Logging Framework의 추상체 역할을 담당함.
- 다른 Logging Framework 로 변경하더라도 유지보수 비용이 적다.

  

### slf4j 적용

#### pom.xml

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <!-- version 별로 서버에 적용이 재대로 이루어 지지 않을 수 있습니다. jdk 버전 등을 고려해서 적용해주세요. -->
    <version>1.7.25</version>    
</dependency>
```

  

## log4j2

Apache에서 제작한 Java Logging System 입니다. log4j 도 존재하지만 2015년 개발 중단 발표와 이후 나온 log4j2 입니다.

Logging System의 목표는 전부 같기 때문에 추가적인 설명은 제외하고 적용하는 방법에 대해 설명하겠습니다.



