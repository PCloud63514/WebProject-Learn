# DTO & VO

> DTO(Data Transfer Object)는 계층간(Controller, Service, Dao 등) 데이터 교환을 위한 객체([java Beans](#javabeans)) 입니다.
>
> DTO와 유사한 개념이 있는데, 이를 VO(Value Object)라고 합니다.
>
> 유사하지만 차이는 분명히 존재합니다.
>
> DTO VO 둘 모두 서버에서 계층간 전달되는 데이터를 의미하며 가장 큰 차이는 VO는 Read-Only 속성의 객체이고, DTO는 getter setter를 보유한 객체를 의미합니다.
>
> 
>
> DTO와 VO 둘 모두 서버 개발 시 어떤 역할을 부여할지 정하냐에 따라 조금씩 용도가 달라지므로, 미리 정하는게 좋습니다.
>
> 아래의 내용은 예시일 뿐 규칙이 아닙니다. 개발팀 내의 규정을 정하여 사용하는 것이 좋습니다.
>
> * DTO: 외부 시스템과 데이터 통신을 할 경우 사용하며, 비즈니스 로직을 포함할 수 있습니다.
> * VO: 값 변경이 없는 Read-Only 객체를 표현하며 데이터 그 자체로 의미가 있는 것을 담고 있어야합니다.



## JavaBeans

- 데이터를 표현하기 위해 자바로 작성된 소프트웨어 컴포넌트.
- JavaBean 규격에 따라 작성된 Java Class 를 JavaBeans 라고 합니다.
- JvaBeans는 별다른 로직 없이 속성과 속성을 가져올 접근 가능한 프로퍼티를 갖고 있으면 됩니다.



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

