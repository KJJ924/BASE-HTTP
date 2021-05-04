<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejhlmqoj30lc0dwmzx.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.



## HTTP 헤더 - 일반헤더



### 용도

HTTP 전송에 필요한 모든 부가정보들을 담는 곳

![image-20210504200126202](https://tva1.sinaimg.cn/large/008i3skNgy1gq6ll35eiyj30j507xt9j.jpg)

<u>표현 헤더는 표현 데이터를 해석할 수 있는 정보를 제공한다</u>(데이터 유형,데이터길이, 압축 정보 등등)





### 표현(전송, 응답 둘다 사용)

- **Content-Type: 표현 데이터의 형식**
  - 미디어 타입, 문자 인코딩 (text/html; charset=utf-8)
- **Content-Encoding: 표현 데이터의 압축 방식**
  - 표현 데이터를 압축하기 위해 사용 (gzip,identity)
- **Content-Language: 표현 데이터의 자연 언어**
  - 표현 데이터의 자연 언어를 표현(ko,en,en-US)

- **Content-Length: 표현 데이터의 길이**
  - 바이트단위 (단 ! Transfer-Encoding을 사용하면 전송하면 안된다.)



### 협상(content-negotiation, 요청시에만 사용)

- **우선순위**
  1. **구체적인 것이 수선한다.**
     (Accept: text/*,text/plain, text/plain;format=flowed)
     `1.text/plain;format=flowed
     2.text/plain
     3.text/*`
  2. **우선순위를 결정하기위해 q 값을 사용** 0~1  클수록 높은 우선순위를 가지며 생략하면 기본값 1 이 된다.
     `(Aceept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7)`
- **종류**
  - **Aceept**: 클라이언트가 선호하는 미디어 타입 전달
  - **Aceept-Charset**: 클라이언트가 선호하는 문자 인코딩
  - **Aceept-Encoding**: 클라이언트가 선호하는 압축 인코딩
  - **Aceept-Language**: 클라이언트가 선호하는 자연언어



### 전송방식

- **Transfer-Encoding** : 분할 전송
  - 보낼 데이터가 용량이 큰 경우 분할하여 전송한다. 
  - 분할 전송시 Content-Length 를 포함하면 안된다.
- Range,Content-Range : 범위전송



### 일반정보

- **From** : 유저 에이전트의 이메일정보
  - 요청에서 사용
- **Referer** : 이전 웹 페이지 주소
  - 현재 요청된 페이지의 이전 웹 페이지 주소
  - Referer를 사용해서 유입 경로 분석 가능
- **User-Agent** : 유저 에이전트 애플리케이션 정보
  - 클라이언트의 애플리케이션 정보(웹브라우저 정보, 등등)
  - 어떤 종류의 브라우저에서 장애가 발생하는지 파악가능
- **Server** : 요청을 처리하는 ORIGIN 서버의 소프트웨어 정보
  - 응답에서 사용
- **Date **: 메세지가 발생한 날짜와 시간
  - 응답에서 사용



### 특별한 정보

- **Host :** 요청한 호스트 정보(도메인)
  - 요청에서 사용하며 필수이다.
  - 하나의 서버가 여러 도메인을 처리할 때, 하나의 IP 주소에 여러 도메인이 적용되어 있을 때
- **Location**: 페이지 리다이렉션
  - 웹 브라우저는 3xx 응답에 Location 헤더가 있으면  Location 위치로 자동 이동
  - 201(created): Location 값은 요청에 의해 생성된 리소스 URI
- **Allow**: 허용 가능한 HTTP 메서드
  - 405 에서 응답에 포함해야함
- **Retry-After**: 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간
  - 503 서비스가 언제까지 불능인지 알려줄 수 있음 



### 인증

- **Authorization:** 클라이언트 인증 정보를 서버에 전달
- **WWW-Authenticate**: 리소스 접근시  필요한 인증 방법 정의
  - 401 Unauthorized 응답과 함께 사용



### 쿠키

- **종류**
  - **Set-Cookie :** 서버에서 클라이언트로 쿠키 전달(응답)
  - **Cookie:** 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달

- **생명주기**
  - **expireds** -> 만료일이 되면 쿠키 삭제
    (Set-Cookie:**expires**=Sat, 26-Dec-2020 01:01:21 GMT)
  - **max-age** -> 0이나 음수를 지정하면 쿠키 삭제
    (Set-Cookie:**max-age**=3600)
  - **세션 쿠키** :만료 날짜를 생략하면 브라우저 종료시 까지만 유지
  - **영속 쿠키**: 만료 날짜를 입력하면 해당 날짜까지 유지

- **도메인**(domain)

  - **명시한 경우** -> 명시한 문서 기준 도메인 + 서브 도메인 포함
  - **생략** -> 현재 문서 기준 도메인만 적용

- **경로**(Path)

  - 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
  - 일반적으로 path=/ 루트로 지정

- **보안**

  - **Secure** 
    - 쿠키는 http, https를 구분하지 않고 전송한다.
    - 하지만 Secure 를 적용하면 https 인 경우에만 전송
  - **HttpOnly**
    - XSS 공격방지
    - HTTP 전송에만 사용
  - **SameSite**
    - XSRF 공격방지
    - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송

  

  



