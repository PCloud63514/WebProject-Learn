---
# Http와 Https 설명

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/d7225371-9270-4b25-9c79-4b8f41cdff01/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221215%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221215T143300Z&X-Amz-Expires=86400&X-Amz-Signature=54122f2b7e5879f5b730de1dac39de91dd1f164914e7bf9e0fea7934ec552b07&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

## HTTP (Hyper Text Transfer Protocol)

<aside>
📒 서버 / 클라이언트 모델을 따라 데이터를 주고받기 위한 프로토콜.

</aside>

> **Note:**
하이퍼텍스트를 교환하기 위해 만들어진 통신규약(프로토콜)입니다.
기본적으로 80번의 Port를 사용하며, 네트워크 계층 Layer-4에 위치합니다.
TCP/IP (Layer-3) 위에서 동작하지만, HTTP 자체는 비연결성이며 쿠키 및 세션을 통해 상태를 관리합니다.
즉 HTTP 자체는 상태를 갖고 있지 않은 State Less 프로토콜 입니다.
현재 HTTP 1.1은 설정한 시간만큼 TCP 연결을 유지하는 Keep-alive 기능을 지원하여 간헐적인 요청을 없앨 수 있게 되었습니다.
>

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/eb48cb50-90a0-48a8-9602-36d6217cafae/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221215%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221215T143327Z&X-Amz-Expires=86400&X-Amz-Signature=3dbfe274931fb93c8ec9698df960339f4008e28b36013ffb6daf1c3f4e4d0526&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

### 통신과정

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/33708bc7-530d-400f-92a9-6f20bbc8528f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221215%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221215T143345Z&X-Amz-Expires=86400&X-Amz-Signature=14ead41ad0a4098fe385eae0128953be8423b26e58775c0075aaf0fd89ec4352&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

해당 요청의 데이터는 평문이 전송되므로 서버와 클라이언트를 오가면서 가로채질 위험이 존재합니다.
이를 해결하기 위해 등장한 것이 HTTPS 입니다.

---

## HTTPS (Hyper Text Transfer Protocol Secure)

<aside>
📒 http 통신의 취약점을 보완한 프로토콜.

</aside>

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/cc79cb17-3bc5-4fde-b104-effb355099ab/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221215%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221215T143406Z&X-Amz-Expires=86400&X-Amz-Signature=2684e36d5a0405c39cf878190fd274b83a7f876925034472bb9e285eee95c81b&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

### Note:

HTTP 하단에 SSL(또는 TLS) 보안계층을 추가하여 암호화, 인증, 무결성 보장을 통해 통신을
안전하게 만들어주는 프로토콜입니다.
443 포트를 사용하며, 위에서 언급한 암호화의 경우 [대칭키 방식과 비대칭키 방식](https://www.notion.so/cd443e3bc99c4e0e9d83e6fff497c36c) 을 사용합니다.

### 동작 과정

Hand-Shaking 의 시작 과정에서 대칭키가 저장된 Session(이하 Session Key)을 교환한다.

대칭키의 특징은 빠른 암복화 연산속도이며, 이 특징을 활용하여 주고 받는 데이터를 암호화 및 복호화 한다.

단 Session Key를 무작정 교환하게 될 경우 대칭키가 탈취될 위험이 존재한다.

동일한 키로 암복화를 함으로 써 상대방이 정당한 요청대상으로 인지하는 대칭키는 위와 같은 사항은 위험한 일이다.

이를 해결하기 위해 Session Key를 공유하는 과정에서 비대칭키를 사용하게 된다.

1. Client가 Server로 연결 요청 (SYN)
2. Server는 공개키를 Client에게 반환 (SYN + ACK)
    1.
3. Client는 인증기관(CA / Certificate Authority)에게 공개키를 전달한다.
4. 인증기관은 등록된 공개키일 경우 암호화되어 있는 인증서를 발급한다.
    1. 단 인증되지 못했을 경우 브라우저에 No Secure 상태로 보여진다.
5. Client는 이미 인증기관의 공개키를 보유하고 있어서 이를 통해 암호화되어 있는 인증서를 복호화 한다.
6. Client는 인증서의 유효성을 검사(상호검증)하고 Session Key를 발급
7. Client는 Session Key를 보관하고 Server의 Public Key로 Session Key를 암호화하여 서버에게 전달
8. Server는 개인키로 암호화된 Session Key를 복호화하여 Client와 동일한 Session Key를 얻음
9. 위 과정을 통해 대칭키인 Session Key를 안전하게 공유했고 해당 키를 통해 데이터를 암복화하여 공유한다.