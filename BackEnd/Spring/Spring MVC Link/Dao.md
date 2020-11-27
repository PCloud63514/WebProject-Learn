# Dao?

> Data Access Object 의 줄임말로, Database의 data에 접근을 위한 객체 입니다.
>
> Service의 DB 직접 접근을 막기 위한 연결관계로 SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공합니다.
>
> 
>
> 클래스 명명규칙의 경우 DAO, dao, Repository 가 있습니다.
>
> ex) CharterDAO, CharacterDao, CharterRepository



```java
package com.pcloud.user.dao;

import com.pcloud.user.dto.UserDto;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao {

    public UserDto userSelect(String name, String phoneNUm) {
		//db 데이터 접근
        return null;
    }

    public UserDto userSelect(String name) {
		//db 데이터 접근
        return null;
    }

    public void userUpdate(String name, int age) {
		//db 데이터 접근
    }
}
```



## Dao 를 사용하는 이유

> 비즈니스 로직과 DB를 분리하고자 하는 것도 있지만,  그 외에도 사용자의 요청을 효과적으로 처리하기 위함에 사용됩니다.
>
> Web Server는 사용자의 요청마다 DB에 접근하는 Connection 객체 생성을 방지하기 위해 Connection을 일정량 미리 만들어 두는 Connection Pool을 사용합니다. 하지만 Connection Pool의 경우 미리 만들어진 것을 재활용하는 것이라 사용자가 db에 접근하는 많은 요청이 발생할 경우 요청을 처리하기 위해
>
> Connection을 추가로 생성하는 오버헤드가 발생합니다.
>
> 예로 사용자가 게시판의 목록을 조회하고 게시글 하나를 보고자 하면 발생하는 요청이 다양합니다.
>
> - 게시판 목록 조회
> - 게시글 상세 조회
> - 방문수 증가
> - 댓글 조회
>
> 이 요청마다 Connection 추가 생성으로 발생하는 오버헤드를 감소 시키기 위해, DB에 접근하기 위한 전용 객체 바로 DAO가 만들어졌습니다.
>
> 커넥션 하나를 통해 DAO는 모든 페이지에서 호출되어 DB에 접근하도록 합니다.
>
> DB 접근을 DAO 하나로 관리하여 다수의 원격호출을 통한 오버헤드를 VO 나 DTO를 통해 감소 시킬 수 있고 다수의 DB 호출 문제를 해결하게 됩니다.



## 참고문헌

[Jungwoon Blog DAO vs DTO 개념 알아보기](https://jungwoon.github.io/common%20sense/2017/11/16/DAO-VO-DTO/)