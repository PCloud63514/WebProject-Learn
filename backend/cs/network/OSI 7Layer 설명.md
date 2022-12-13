# OSI 7Layer
**(Open Systems Interconnection Reference Model 7 Layer)**

> 국체 표준화 기구(ISO)에서 개발한 통신에 관한 계층화 표준 모델입니다.
>

![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f8e9c592-d6d0-4d39-83d7-6759aa7d9f18/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221213%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221213T131815Z&X-Amz-Expires=86400&X-Amz-Signature=22b91aa0abd26cd2056aa8a2695c8c9854e331d246dba7b8cd74365a57434485&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

---

### Layer 1 : 물리 계층 (Physical Layer)

📒 하드웨어의 신호를 전송하는 계층  
> **Note:**
**디지털 데이터를 아날로그적인 전기적 신호로 변환**하여 물리적인 전송이 가능하도록 합니다.  
주소 개념이 없고, 물리적으로 연결된 노드간에 데이터 비트를 주고 받습니다.

| 단위(PDU) | Bit |
| --- | --- |
| 주소/대상특정방법 | 없음 (물리적으로 연결된 대상) |
| 주요 장비 | Hub, Repeater, Nnetwork Interface Card |

---

### Layer 2: 데이터 링크 계층 (Data Link Layer)

📒 Point to Point 간 신뢰성 있는 전송을 보장하기 위한 계층

> **Note:**
Layer1과 Layer2는 통합 칩 형태로 구현되기 때문에 해당 계층은 하드웨어 형태입니다.  
물리계층 동작 과정에서 발생할 수 있는 **오류제어**, **흐름제어** 역할을 갖고 있습니다.
>
- **오류 제어**
    - 오류는 전송 데이터가 누락되는 것 등을 의미합니다.
- **흐름 제어**
    - 흐름은 송신 측이 수신측으로 데이터를 보낼 때 속도 및 양을 의미합니다.
      수신측이 읽는 것 보다 전송이 빠르면 안되기에 수신측이 발송 데이터의 양이나 속도를 제어하게 되며, 이를 흐름제어 또는 흐름 조절(pacing)이라고 합니다.
- 여담

  컴공 전공 수업에서 Layer2와 Layer3를 중점으로 강의가 이주어졌던 기억이 있습니다.
  수업의 주된 학습 내용은 해당 Layer에서 동작하는 오류 검출, 오류 정정, 오류 무시, 반향(Echo) 검사, 재전송 등 오류에 대한 **흐름제어를 설명**하며, 일반적인 예시로 오류검출 **패리티 비트**를 배웁니다.


| 단위(PDU) | Frame |
| --- | --- |
| 주소/대상특정방법 | MAC Address (네트워크 카드(통합칩)가 구성되며 적용된 주소입니다.) |
| 주요 장비 | 브릿지(Bridge), L2 Switch, USB, Blutooth, WiFi |

---

### Layer 3: 네트워크 계층 (Network Layer)


📒 종단간 전송(End To End)을 위한 경로 설정을 담당하는 계층입니다.


> 라우팅, 패킷 포워딩, 세그멘테이션, 인터네트워킹 등 통신과정에 중심인 계층이며 현재 사용하는 인터넷은 네트워크 계층에 속하는 망입니다.  
TCP/IP 프로토콜이 속한 계층이 3/4계층이며, 컴퓨터 커널(OS) 내부에 구현된 TCP/IP 프로토콜 스택은
2계층에서 받은 Frame을 Socket Interface를 통해 7계층으로 올려보내는 역할을 합니다.
>

| 단위(PDU) | Packet |
| --- | --- |
| 주소/대상특정방법 | IP Address |
| 주요 장비 | Router, L3 Switch |
| Protocol | IP, ARP… |

---

### Layer 4: 전송 계층 (Transport Layer)

<aside>
📒 종단간 전송(End To End) 사용자들의 주고받는 데이터의 신뢰성을 담당하는 계층

</aside>

> Note:
종단(Host)의 구체적인 목적지(Process)까지 데이터가 도달할 수 있도록 합니다.  
해당 계층에서 Process를 특정하기 위해 Port Number를 주소로 사용합니다.
>

| 단위(PDU) | Segment |
| --- | --- |
| 주소/대상특정방법 | Port |
| 주요 장비 | L4 Switch |
| Protocol | TCP, UDP |

### Layer 5: 세션 계층 (Session Layer)

---

<aside>
📒 응용 프로그램간의 통신을 위한 논리적인 연결(Session) 생성 및 제어를 담당하는 계층

</aside>

> **Note:**
해당 계층에서 기억할 내용은 TCP/IP 통신의 세션을 생성 및 제거를 책임지는 것입니다.  

**Keyword:**
- 동시 송수신 방식
- 반이중 방식
- 전이중 방식


| 단위(PDU) | Data or Message (딱히 단위라고 할만한 것이 없습니다.) |
| --- | --- |
| 주요 장비 | L5 Switch |
| Protocol | SSL, TLS |

---

### Layer 6: 표현 계층 (Presentation Layer)

<aside>
📒 코드간의 번역을 담당하여 7계층의 보조를 담당하는 계층

</aside>

> Note:
인코딩/디코딩, 압축/해제, 암호화/복호화를 담당하는 계층입니다.  
MIME 인코딩, ASCII 파일 변환 등 응용계층 동작에 필요한 정보를 전처리하여 보조합니다.
>

| 단위(PDU) | Data |
| --- | --- |
| 주요 장비 | X |
| Protocol | X |

---

### Layer 7: 응용 계층 (Application Layer)

<aside>
📒 사용자의 인터페이스 역할을 담당하는 계층

</aside>

> Note:
응용 프로세스 간의 정보 교환을 담당하는 계층입니다.  
예시로 메일을 주고받거나, 브라우저에 인터넷을 검색하는 등의 사용자에게 직접적으로 제공되는 서비스 등을 의미합니다.
>

| 단위(PDU) | Data |
| --- | --- |
| 주요 장비 | L7 Switch, Firewall |
| Protocol | TELNET, FTP, SMTP, HTTP |