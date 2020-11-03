# Dao?

> Data Access Object 의 줄임말로, DB를 사용해 데이터를 조회 및 조작하는 객체이다.
>
> Service의 DB 직접 접근을 막기 위한 연결관계로 SQL를 사용해 DB에 접근한 후 적절한 CRUD API를 제공한다.



```java
@Repository("characterDao")
public class CharacterDao {
	public List<Character> characterList() {
		return
	}
}
```
