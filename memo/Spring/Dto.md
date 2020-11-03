# Dto?

> 계층간(Controller, Service, Dao 등) 데이터 교환을 위한 객체([java Beans](#javabeans) 이다.)이며, VO 라고 표현한다.
>
> Java Beans를 생각하면 된다.



## JavaBeans

- 데이터를 표현하기 위해 자바로 작성된 소프트웨어 컴포넌트.
- JavaBean 규격에 따라 작성된 Java Class 를 JavaBeans 라고 한다.
- JvaBeans는 별다른 로직 없이 속성과 속성을 가져올 접근 가능한 프로퍼티를 갖고 있으면 된다.



```java
public class Character {
	private String cid;
	public String getCid() {
		return this.cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
}
```

