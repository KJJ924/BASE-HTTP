<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejhlmqoj30lc0dwmzx.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.



# HTTP 기본

## 항목

- 모든 것이 HTTP
- 클라이언트 서버 구조
- Stateful, Stateless
- 비 연결성(connectionless)
- HTTP 메서드





## 모든 것이 HTTP

HTTP의 초창기는 Text, HTML 등 을 전송하기위한 프로토콜로 나타났다.

하지만 현재의 HTTP 는 모든 데이터(동영상, 이미지, 등등)를 주고 받을 수 있는 프로토콜로 거듭났다.



HTTP의 역사

- HTTP/0.9 1991년 : GET 메서드만 지원 , HTTP 헤더 X
- HTTP/1.0 1996년 : 메서드, 헤더 추가
- **HTTP/1.1 1997년 : 가장 많이 사용 하는 버전**
- HTTP/2 2015년 : 성능 개선
- HTTP/3 진행중 : **TCP 대신 UDP 사용** , 성능 개선



![image-20210413194608356](https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejr31mxj30d00a9ab5.jpg)

크롬의 개발자 도구를 이용하여 현재 브라우저들이 어떠한 프로토콜을 사용하는지 알아 볼 수 있다.

여기서 보면은 HTTP/2 버전을 많이 사용하고 있는데 사실상 HTTP/1.1 에서 성능 개선만 하여 나온 버전이라

큰 틀인 HTTP/1.1 을 아는 것이 중요하다.



그렇다면 HTTP 의 특징을 이제 하나씩 알아보자.



## 클라이언트 서버구조



**HTTP 의 구조는 Request Response 구조를 가지고 있다**. 즉

클라이언트는 서버에 요청을 보내고 응답을 대기하고 서버가 요청에 대한 결과를 만들어서 응답하는 구조이다.

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejvbdkyj31920boq3s.jpg" alt="image-20210413195150959" style="zoom:50%;" />

그렇다면 이러한 구조를 가지면 어떠한 장점이 있을까?

지금 보면 클라이언트와 서버는 독립적으로 분리되어있다.

클라이언트 측에서는 UI 의 결과만 필요하며 비지니스 로직은 서버가 처리하여 클라이언트에게 결과만 전달하게 되는 구조이다.

이러한 구조는 클라이언트는 비지니스의 로직에 영향을 받지 않으며 서버는 클라이언트에 얽매이지 않음으로 내부적으로

성능 개선 및 프로젝트 구조 개선 등을 진행 하여도 원래의 결과만 잘 전달한다면 클라이언트 측에서는 서버의 변동 사항을 알 수 가 없다.

즉 독립적인 구조는 어느 한쪽에 의존하지 않음으로 서로의 변경사항에 영향을 받지않는다는 뜻이다.



## 무상태 프로토콜 (Stateless)

Stateless 는 말그대로 상태를 보존하지 않는 다는 뜻이다.

이러한 상태(Stateless) 의 단점은 클라이언트 측에서는 한번 서버에서 넘겼던 정보여도 서버측에서는 클라이언트의

상태를 저장하지 않음으로 계속해서 서버에 원하는 정보를 다시 넘겨줘야한다.

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiek2wjmfj31hc0u0goi.jpg" alt="image-20210413201808288" style="zoom:50%;" />

하지만 Stateless 상태를 가짐으로써 서버의 확장(스케일 아웃) 이 아주 쉽다.

이 뜻은 어떠한 이벤트 때문에 클라이언트의 요청이 많아진다면 미리 서버를 많이 증설할 수 있다는 뜻이다.



<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiekezxcqj31hc0u041f.jpg" alt="image-20210413201748089" style="zoom:50%;" />

만약 Stateful 상태를 가지고 있다면

서버입장에서는 하나하나의 고객의 상태를 관리해야하고 만약 A 고객 을 관리하고 있던 서버가 일시적으로 작동불능이 된다면 A 고객의 상태정보는 다른서버에서 모르기 때문에 A 고객은 처음부터 다시 다른 서버에 요청을 하나하나 해야한다.



## 비연결성(connectionless)

비 연결성은 클라이언트가 서버의 요청을 보냈을때 서버는 요청처리 후 응답을하고 바로 연결을 끊어 버린다.

이러한 모델의 장점은 하나의 서버가 여려명의 클라이언트의 요청을 처리하기 좋아진다.

만약 연결성을 가지고있다면 클라이언트가 응답을 받았음에도 불구하고 연결이 끊어지지 않는다면

해당 서버는 다른 클라이언트의 요청을 처리하지 못한다.



하지만 비 연결성에서의 단점도 여전히 존재하는데

연결을 끊는다는건 클라이언트가 요청을 할때마다 앞서 배웠던 TCP/IP 연결(3 way handshake)을 새로 맺어야한다.

우리는 어떠한 홈페이지에 요청을 보내면 대 부분 그 홈페이지에서 HTML 만 보내지는 않는다.

![image-20210413194608356](https://tva1.sinaimg.cn/large/008eGmZEgy1gpib4ppuhxj30d00a9abr.jpg)

이 처럼 수많은 css 파일 , 이미지 , js 등이 함께 다운로드 된다.

이러한 문제점 을 해결하기 위해서 현재는 HTTP 지속 연결(Persistent Connections) 으로 문제를 해결 했다.

|                          HTTP 초기                           |            HTTP 지속 연결(Persistent Connections)            |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![image-20210413215110180](https://tva1.sinaimg.cn/large/008eGmZEgy1gpiequy3s6j30u60nydin.jpg) | ![image-20210413215419149](https://tva1.sinaimg.cn/large/008eGmZEgy1gpieu0effqj30u60nydin.jpg) |



## HTTP 메시지

HTTP 메세지 의 구조는 다음과 같다.

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpidrp0qzqj30qw0meq8r.jpg" alt="image-20210413211723603" style="zoom: 10%;" />

전체적인 구조는 다음과 같은데 요청메세지와 응답메세지의 구조가 약간 다르다.

예제를 보면서 알아보자.

| HTTP 요청 메세지                                             | HTTP  응답 메세지                                            |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiem5kilfj30r007o77r.jpg" alt="image-20210413212333004" style="zoom:50%;" /> | <img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiemiido0j30qm0dodl7.jpg" alt="image-20210413212401904" style="zoom:50%;" /> |

다음과 같은 구조를 가지게 되는데 여기서 주의할점은 HTTP  요청에도 body 본문을 가질 수 있다.



## 시작라인(start-line)

- 요청(request-line)

  -  method (get,post,put 등)  request-target(요청대상 search?q=hello~~) HTTP-version 으로 구성된다.



- 응답(status-line)

  - HTTP-version(HTTP/1.1)  status-Code(200,400,500) reason-phrase(OK) 으로 구성된다

## 

## HTTP 헤더(header)

- 요청, 응답
  - 필드(Host, Content-Type 등): value(필드에 해당하는 값) 로 구성된다.

**용도**

- HTTP 전송에 필요한 모든 부가정보를 포함



## 메세지 바디

- 요청, 응답
  - 실제 전송할 데이터를 포함(HTML , 이미지, 영상, JSON 등등)
  - byte 로 표현할 수 잇는 모든 데이터 전송가능

