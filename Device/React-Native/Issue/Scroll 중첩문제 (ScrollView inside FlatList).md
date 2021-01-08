# Scroll 중첩문제 (ScrollView inside FlatList)

ScrollView 안에 FlatList를 적용했더니 중첩 Scroll로 인해 FlatList에 이벤트가 적용이 되지 않는다.



## 해결법

IOS는 기본적으로 중첩 Scroll이 해결되어 있어서 별도로 처리할 필요는 없다.

문제는 Android.

해결법은 간단하다. FlatListView에 **nestedScrollEnabled** 요소를 추가하면 된다.

```jsx
 <FlatList
                nestedScrollEnabled
                style={[styles.flatList, propStyle?.flatList]}
                data={data}
                renderItem={ ({item}) => <Text>{item}</Text>}
                windowSize={2}
            
            />
```



단 애매한 문제가 남는다.

이건 뭐 기본적으로 Android도 마찬가지라 비슷한 문제인 듯 싶은데 FlatList의 Scroll 범위 이상으로 터치를 지속할 경우 ScrollView에 적용이 된다.

이런 문제는 해결하려면 고냥 새로 만드는 수준이라 큰 이슈가 되지 않으면 넘어간다.

* 다시 해보니 FlatList 내의 Item 끝에 도달하면 특유의 끝에 도달했다는 이펙트가 보이는게 아니라 ScrollView의 스크롤이 적용된다. (FlatList 내의 스크롤인데도 이렇다.) Scroll이 아주 짧게 있다면 불편할 것.



## 2차 해결법

예전 자료 찾아보니 react-native-swipe-gestures 를 썼던 것을 기억했다.

그렇다고 이거 하나 해결하자고 dependency 추가하는건 애매하다.

(완벽히 해결되는거도 아니고 threshold 추가해서 편법으로 해결하는거라 결국 문제가 될 것이다.)

