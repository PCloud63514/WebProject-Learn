# TinyAlertDialog

<Alert/> 형식의 render 종속 Component에서 벗어나기 위해 작성한 문서입니다.

- 주의! 결국 reder에 삽입되어 있는 형태이며, 트릭형태의 코드입니다.

제 기준으로 WPF, Winform, Android 등 Alert 관련 Dialog 를 생성하는 과정에서 UI 코드 내에 미리 Dialog를 삽입해두는 경우는 없었습니다.

보통 Dialog.show 또는 alert("Hello") 같은 function 형태의 호출방식 이였습니다.

그러다보니 계속해서 Dialog Component를 render에 미리 삽입해두고 호출하는 방식은 불편했습니다.

이번 문서의 목표는 위와 같은 불편함을 해결하기 위해 작성했습니다.



### 전제

- Modal 창을 동시에 2개 이상 띄울 수 없다.
- UI Component의 render 부분에 <Alert/> 와 같은 삽입 과정을 회피할 수 있다.
- 단 실제로 최상위 Component에 Component를 숨겨두는 방식이라 완벽히 render 종속을 피하지 못했다.





## InSide Function Component



## InSide Class Component

