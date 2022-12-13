# Elastic Search 버전 별 차이점

Elastic Search 6.x 에서 7.x 로 변경되며 무엇이 변경되었고 필수로 인지해야하는 사항이 무엇인지에 대해 작성하였습니다.



## API

API 변경은 Elastic Search 버전을 업그레이드 하는 것에 있어 큰 이슈 사항입니다.

6.x 버전에서 Single Type으로 제한이 되었다면, 7.x 로 업그레이드 되며 Type 구조를 삭제하게 되었습니다. (Typeless 요청)

- _default_ 매핑 사용이 중단되었습니다.

내용을 확인하면 Type이 사라지고 Document API는 _doc으로 대체하여 적용하는 것을 볼 수 있습니다.

### Search

| API              | 6.x                               | 7.x                        |
| ---------------- | --------------------------------- | -------------------------- |
| search           | /{index}/{type}/_search           | {index}/_search            |
| msearch          | /{index}/{type}/_msearch          | /{index}/_msearch          |
| count            | /{index}/{type}/_count            | /{index}/_count            |
| explain          | /{index}/{type}/{id}/_explain     | /{index}/_explain/{id}     |
| search template  | /{index}/{type}/_search/template  | /{index}/_search/template  |
| msearch template | /{index}/{type}/_msearch/template | /{index}/_msearch/template |



### Document

| API          | 6.x                              | 7.x                       |
| ------------ | -------------------------------- | ------------------------- |
| index        | {index}/{type}/{id}              | /{index}/**_doc**/{id}    |
| delete       | /{index}/{type}/{id}             | /{index}/**_doc**/{id}    |
| get          | /{index}/{type}/{id}             | /{index}/**_doc**/{id}    |
| update       | /{index}/{type}/{id}/_update     | /{index}/_update/{id}     |
| get source   | /{index}/{type}/{id}/_source     | /{index}/_source/{id}     |
| bulk         | /{index}/{type}/_bulk            | /{index}/_bulk            |
| mget         | /{index}/{type}/_mget            | /{index}/_mget            |
| termvectors  | /{index}/{type}/{id}/_termvector | /{index}/_termvector/{id} |
| mtermvectors | /{index}/{type}/_mtermvectors    | /{index}/_mtermvectors    |

> <span style="color:black">Elastic Search 7에서 index  6.x 명령어를 사용하면 정상 작동은 합니다.</span>
>
> <span style="color:black">하지만 아래의 경고를 함께 확인할 수 있습니다.</span>
>
> <span style="color:black">#! Deprecation: [types removal] Specifying types in document get requests is deprecated, use the /{index}/_doc/{id} endpoint instead.</span>
>
> <span style="color:black">_doc 을 포함하여 작성하도록 경고가 확인되며 8.x 업그레이드 이후에는 정상적으로 작동하지 않을 것입니다.</span>



### Index

| API               | 6.x                                     | 7.x                              |
| :---------------- | :-------------------------------------- | :------------------------------- |
| create index      | /{index}                                | ㅡ                               |
| get mapping       | /{index}/_mapping/{type}                | /{index}/_mapping                |
| put mapping       | /{index}/_mapping/{type}                | /{index}/_mapping                |
| get field mapping | /{index}/{type}/_mapping/field/{fields} | /{index}/_mapping/field/{fields} |
| get template      | /_template/{template}                   | ㅡ                               |
| put template      | /_template/{template}                   | ㅡ                               |

><span style="color:black">Document API는 7.x에서도 6.x API 동작이 가능했지만, Index는 실패 결과를 확인할 수 있습니다.</span>
>
><span style="color:black">만약 기존의 API 형식을 이행하려면 **include_type_name=true** 형식을 추가해주세요. 인덱스 생성, 템플릿, 매핑 API 등 다양한 곳에 적용이 가능합니다.</span>
>
>- <span style="color:black">/{index}/_mapping/{type}?include_type_name=true</span>
>
><span style="color:black">물론 아래의 경고를 함께 확인하게 됩니다.</span>
>
><span style="color:black">#! Deprecation: [types removal] Using include_type_name in get mapping requests is deprecated. The parameter will be removed in the next major version.</span>

## 참고문헌

### [Elastic Stack 7.0 출시 밎 지금까지의 변경들 - Jongmin's blog (kimjmin.net)](http://kimjmin.net/2019/04/2019-04-elastic-stack-7-release/)

### [Elasticsearch 7.0에서 형식으로부터 무형식 API로 이행 | Elastic Blog](https://www.elastic.co/kr/blog/moving-from-types-to-typeless-apis-in-elasticsearch-7-0)

### [[Elasticsearch\] 핵심 개념 - 매핑 타입이 사라지면서 멀티 타입 인덱스를 싱글 타입 인덱스로 마이그래이션 :: gintire (tistory.com)](https://gintrie.tistory.com/48)