# ScrollView padding 시 아래가 안보이던 문제

Container 역할로 ScrollView를 사용 후 padding을 적용했더니 padding 크기 만큼 아래가 잘려나갔다....

margin으로 바꾸면 모습은 보이지만 ScrollBar의 위치가 margin 만큼 안으로 들어가 있어서 영 보기 불편하다.

Container 트릭 용으로 View를 상단에 적용하고 하더라도 결과는 마찬가지...



좀 더 찾아보니 ScrollView에 style 적용이 아닌 **contentContainerStyle** 을 적용해보라는 글을 읽었다.

padding을 그대로 적용하고 style을 contentCOntainerStyle로 적용하니 모습이 이래졌다.



### contentContainerStyle 적용 후

![image](https://user-images.githubusercontent.com/22608825/103972617-ab99a800-51b0-11eb-9e6d-26b7c60cb158.png)



생각했던거랑 다르게 압축되어 버린 모습.. style에 flex가 있어서 그런듯 싶다.

flex를 제외하거나 lfexGrow로 변경하니 정상 작동!

### 정상작동

![image](https://user-images.githubusercontent.com/22608825/103972715-e6034500-51b0-11eb-8287-84f3c2c79323.png)