# Mapping Type

이전 페이지의  [ElasticSearch-추상화 개념](https://github.com/PCloud63514/WebProject-Learn/blob/master/BackEnd/ELK/ElasticSearch/ElasticSearch-%EC%B6%94%EC%83%81%ED%99%94%20%EA%B0%9C%EB%85%90.md) 예제 중 /message/_doc/1 색인을 진행하였다면, 모르는 사이 자동으로 Mapping이 생성되었을 것입니다.

**Mapping**은 Index에 삽입될 Field 의 Data Type을 정의하는 것을 말합니다.

Mapping을 하지 않았지만 GET /message/_doc/1 을 진행할 수 있던 이유는 Elastic Search에서 Index와 Mapping에 대한 정보를 자동으로 생성했기 때문입니다.

즉, 무작정 index 정의 없이 색인을 하더라도 Elastic Search는 자동으로 문서의 내용을 확인하고 최선의DataType을 지정(Mapping) 해주기 때문에 과정을 생략할 수 있습니다.



## 예시

우선 아래의 예제를 입력해보겠습니다.

#### 예제

```json
PUT /message/_doc/2
{
  "name" : "Go Gil-dong",
  "message" : "아아 이 서늘하고도 묵직한 감각",
  "targetName": "Dooly",
  "date" : "2020-12-28T17:17:25"
}
```

기존에 입력한 /catalog/product/1 의 Field이 한 가지 추가되었습니다. (targetName)

   

#### 예제 결과

> GET 을 통해 방금 색인한 데이터를 확인하겠습니다.

```json
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "2",
  "_version" : 6,
  "_seq_no" : 6,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "Go Gil-dong",
    "message" : "아아 이 서늘하고도 묵직한 감각",
    "targetName" : "Dooly",
    "date" : "2020-12-28T17:17:25"
  }
}
```

 

공통으로 들어간 Field 인 name, message, date를 Common Field 라합니다. 이는 개별 Document를 message로 식별할 수 있도록 해줍니다.

위 처럼 Elastic Search에서는 RDBMS와 달리 각 Document를 미리 정의하지 않아도 정상 작동하며, message 라고 지정한 index도 미리 생성할 필요가 없습니다.



#### mapping 정보 확인하기

> <span style="color:black">GET /message/_mapping  요청을 통해 확인하겠습니다.</span>

```json
{
  "message" : {
    "mappings" : {
      "properties" : {
        "date" : {
          "type" : "date"
        },
        "message" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "targetName" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        }
      }
    }
  }
}
```



이를 통해 직접 index 및 mapping을 정의하지 않더라도 얼추 원하는 Data type이 적용된 것을 확인할 수 있습니다.



## 직접 mapping 하기

Elastic Search가 자동으로 Mapping 을 정의하여 편한 점은 있지만 한 가지 문제가 발생합니다.

한번 정의된 mapping은 변경되지 않는 점입니다.

새로운 Field를 추가하거나 새로운 Type의 Mapping을정의할 수 있지만 **기존에 정의한 내용을 변경하거나 되돌릴 수 없습니다.**

또한 원하는 format, filter 등을 지정하기 위해선 직접 Index와 Mapping을 정의해야합니다. (서로 다른 것입니다.)

- Mapping 정의는 mappings 작성을 의미합니다. 
- 인덱스 작성(선언) 은 PUT message  {"settings": {...}, "filter":{...}} 같은 작성을 의미합니다.



#### 예제

> <span style="color:black">예제 진행을 위해 기존 message Index를 삭제하고 진행해주세요.</span>
>
> <span style="color:black">delete /message</span>

```json
PUT message
{
  "mappings": {
    "properties": {
      "name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "targetName" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
      "date": {
        "type": "date",
        "format": "yyyy-MM-dd HH:mm:ss||yyyy/MM/dd||epoch_millis"
      }
    }
  }
}
```

  

#### 예제 결과

```json
{
  "message" : {
    "mappings" : {
      "properties" : {
        "date" : {
          "type" : "date",
          "format" : "yyyy-MM-dd HH:mm:ss||yyyy/MM/dd||epoch_millis"
        },
        "name" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "targetName" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        }
      }
    }
  }
}
```



#### 데이터 삽입

```json
PUT /message/_doc/1
{
  "name" : "Go Gil-dong",
  "message" : "아아 이 서늘하고도 묵직한 감각",
  "targetName": "Dooly",
  "date" : "2020-12-28 17:17:25"
}
```



#### 결과 (GET)

```json
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "_seq_no" : 0,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "Go Gil-dong",
    "message" : "아아 이 서늘하고도 묵직한 감각",
    "targetName" : "Dooly",
    "date" : "2020-12-28 17:17:25"
  }
}
```