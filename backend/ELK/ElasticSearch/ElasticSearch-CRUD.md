# Elastic Search CRUD

드디어 이론에서 벗어나 가장 기본적인 CRUD(Create, Read, Update, Delete) 연산에 대해 학습하겠습니다.

Elastic Search는 REST API를 준수하며, CRUD 연산은 Document를 대상으로 수행할 수 있습니다.

  

## Index API(색인 API)

#### ID 명시 Document 색인

> 만약 기존에 존재하는 Document ID를 입력한다면 Update 과정이 나타납니다.
>
> 이를 허용하지 않기 위해선 _doc 대신 _create를 사용해야합니다.

```
PUT /{index}/_doc/{id}
PUT /{index}/_create/{id}
```

```json
PUT /message/_doc/1
{
  "name" : "Go Gil-dong",
  "message" : "아아 이 서늘하고도 묵직한 감각",
  "targetName": "Dooly",
  "date" : "2020-12-28 17:17:25"
}
```

​    

#### ID 암시적 Document 색인

ID를 지정하지 않으면 Hash 값을 ID로 자동 지정됩니다.

```
POST /{index}/_doc
```

```
POST /message/_doc
{
  "name" : "Dooly",
  "message" : "호잇!",
  "targetName": "Go Gil-dong",
  "date" : "2020-12-29 18:17:25"
}
```

​    

## 조회 API

#### Document ID를 이용한 조회

```
GET /{index}/_doc/{ID}
```

```json
GET /message/_doc/guI8qnYB0JiWEysZgiqX

============================================
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "guI8qnYB0JiWEysZgiqX",
  "_version" : 1,
  "_seq_no" : 1,
  "_primary_term" : 1,
  "found" : true,
  "_source" : {
    "name" : "Dooly",
    "message" : "호잇!",
    "targetName" : "Go Gil-dong",
    "date" : "2020-12-29 18:17:25"
  }
}
```

​    

#### _search 를 이용한 조회

```
GET /{index}/_search?q={Field Key}:{Field Value}
```

```json
GET /message/_search?q=name:"Dooly"

==============================================
{
  "took" : 1,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 1,
      "relation" : "eq"
    },
    "max_score" : 0.9530773,
    "hits" : [
      {
        "_index" : "message",
        "_type" : "_doc",
        "_id" : "guI8qnYB0JiWEysZgiqX",
        "_score" : 0.9530773,
        "_source" : {
          "name" : "Dooly",
          "message" : "호잇!",
          "targetName" : "Go Gil-dong",
          "date" : "2020-12-29 18:17:25"
        }
      }
    ]
  }
}
```

   

## 업데이트 API

#### PUT Update

> Document 가 없다면 새로 만들며, 기존 Document있다면 삭제되고 새로운 Document로 덮어씌워지게 됩니다.
>
> 이 때 결과는 Created 가 아닌 Updated로 나타나며, Meta Field의 _version의 값이 1 증가합니다.

```
PUT /{index}/_doc/{id}
```

```json
PUT /message/_doc/1
{
  "name" : "Swordmaster Go Gil-dong",
  "message" : "아아 이 서늘하고도 묵직한 감각",
  "targetName": "Dooly",
  "date" : "2020-12-28 17:17:25"
}
```

   

#### POST Update

> 오로지 Update 만 수행하기 위해선 POST 와 _update를 사용해야합니다.

```json
POST /{index}/_update/{id}
{
	"doc": {
		"key":"value"
	}
}
```

```json
POST /message/_update/1
{
  "doc": {
    "name" : "Swordmaster Go Gil-dong",
    "message" : "아아 이 서늘하고도 묵직한 감각",
    "targetName": "Dooly",
    "date" : "2020-12-28 17:17:25"  
  }
}

=====================================================
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 2,
  "_primary_term" : 2
}
```

 

## 삭제 API

```
DELETE /{index}/_doc/{ID}
```

```json
DELETE /message/_doc/1
=================================
{
  "_index" : "message",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 3,
  "result" : "deleted",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 3,
  "_primary_term" : 3
}
```