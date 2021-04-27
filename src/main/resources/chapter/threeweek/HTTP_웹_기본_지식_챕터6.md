<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejhlmqoj30lc0dwmzx.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.





## HTTP 상태코드



클라이언트가 서버에게 요청을 보내면 서버는 요청을 처리하고 처리상태를 클라이언트에게 제공해준다.

HTTP 응답 상태 코드는 다음과 같다.

- **1xx(조건부 응답)** - 요청을 받았으며 요청을 처리중 (~~거의 사용되지 않는다.~~)
- **2xx(성공)** - 클라이언트가 요청한 동작을 수신하여 성공적으로 처리했음
- **3xx(리다이렉션 완료)** - 요청을 완료하려면 추가행동이 필요함
- **4xx(요청 오류)** - 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수없음
- **5xx(서버오류)** - 서버오류, 서버가 정상요청을 처리하지 못함

만약 구체적인 상태코드타입(405)을 알지 못한다면 해당하는 상위 상태코드(4xx)로 해석하여 처리하면된다.





## 2xx - (성공)

- 200 OK - 클라이언트가 요청한 행동을 처리 후 응답하는 상태코드

- 201 Created - 클라이언트의 요청에 따라 요청 성공 후 서버에 새로운 자원이 생성된 경우 

- 202 Accepted - 요청이 접수되었으나 처리가 완료되지 않음(배치 처리에서 사용)

- 204 No Content - 서버가 요청을 성공적으로 수행했지만 , 응답 페이로드 본문에 보낼 데이터가 없음



## 3xx -(리다이렉션 완료)

리다이렉션이란 무엇인가? - > 웹 브라우저는 응답의 결과가 3xx 이면서 Location 헤더가 있으면 Location 위치로 자동 이동 된다.

정말 이렇게 행동하는지 확인해보자.

- **Controller Code**

  ```java
  @PostMapping
  public ResponseEntity<ResponseMember> save(@RequestBody RequestMember requestMember){
      ResponseMember member = memberService.save(requestMember);
      return ResponseEntity.status(HttpStatus.SEE_OTHER)
          .header("Location", member.resourceLocation()).build(); //응답 헤더에 Location 추가
  }
  ```

- **요청**

  ```http
  ### 회원 생성
  POST http://localhost:8080/members
  Content-Type: application/json
  
  {
    "id" : "dkansk924@naver.com",
    "pw" : "password",
    "local": "seoul",
    "age": 9999
  }
  ```

  만약 리다이렉션이 안된다면 응답의 결과는 303의 상태코드와  header 에 자원의 위치를 나타내는 값이 return 될 것이다.

  

- **결과**
  
  ![image-20210426231830192](https://tva1.sinaimg.cn/large/008i3skNly1gpxibswujfj30ct09t0t9.jpg)

  하지만 다음과 같이  리다이렉션이 되어 `GET /members/{no}` 으로 맵핑한 컨트롤러를 재 호출하여 응답을 해준다.



그림으로 살펴보면 다음과 같을 것이다. (해당 예제는 주문에 대한 예제이지만 위의 요청과 비교하면서보면 흐름은 똑같다.)
<img src="https://tva1.sinaimg.cn/large/008i3skNly1gpxiorh7nsj31jy0u0e77.jpg" alt="image-20210426233019787" style="zoom: 33%;" />

클라이언트는 서버에 요청(SAVE)을 한번 보내는 것처럼 보이겠지만 내부적으로는 SAVE,GET 요청이 2번 호출 되었다.

이렇게 리다이렉션을 하는 이유는 무엇일까?

- 원래의 URI 가 이동되었을때 변경된 URI 로 리다이렉션이 필요한 경우 

- POST 요청후 클라이언트가 새로고침(재 요청) 으로 인한 중복 데이터 저장 방지

  -> 즉 리다이렉트 후 클라이언트가 새로고침을 하여도 결과화면은 GET 요청이기 때문에 POST 요청이 아닌 GET 요청을 계속 호출 한다.

이러한 경우 때문에 리다이렉션을 많이 이용한다. 이제 3xx 의 세부 상태 코드를 알아보자.



## **301, 308 - 영구 리다이렉션**

- 공통
  - 리소스의 URI가 영구적으로 이동
  - 원래의 URL를 사용X, 검색 엔진 등에서도 변경 인지

### 301 Moved Permanently

- 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음

### 308 Permanent Redirect

- 리다이렉트시 요청 메서드와 본문유지(처음 POST를 보내면 리다이렉트도 POST 유지)

  

## 302, 307, 303 - 일시적인 리다이렉션 

- 공통
  - 리소스의 URI가 일시적으로 변경
  - 따라서 검색 엔진 등에서 URI을 변경하면 안됨

### 302 Found

- 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음

### 307 Temporary Redirect

- 리다이렉트시 요청 메서드와 본문 유지(요청 메서드를 변경하면 안된다.)

### 303 See Other

- 리다이렉트시 메서드가 GET으로 변경

## 4xx -(클라이언트 오류)

해당 오류는 클라이언트 측에서 잘못된 요청으로 보내어 서버단에서 요청을 처리할 수 없는 경우 사용한다.

즉 4xx 오류 같은경우 클라이언트 측에 책임이 있음을 말한다.

### 

### 400 Bad Request

- 클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음
  ->  요청 파라미터가 잘못되거나, API 스펙이 맞지 않을 때



### 401 Unauthorized

- 클라이언트가 해당 리소스에 대한 인증이 필요함
  ->  오류발생시 응답에 `WWW-Authenticate` 헤더와 함께 인증 방법을 설명 해야한다.



### 403 Forbidden

- 서버가 요청을 이해했지만 승인을 거부함
  -> 주로 인증 자격증명은 있지만 , 접근 권한이 불충분한 경우



### 404 Not Found

- 요청 리소스를 찾을 수 없음





## 5xx (서버 오류)

클라이언트 측 잘못이 아닌 서버 내부 문제로 오류가 발생했을때 사용한다.

따라서 서버에 문제가 있기 때문에 요청을 재시도 하면 성공(서버가 복구 된다면?) 할 수도 있다.



### 500 Internal Server Error

- 서버 내부 문제로 오류 발생



### 503 Service Unavailable

- 서비스 이용불가
  -> 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없음

  ​	(`Retry-After`헤더 필드로 얼마뒤에 복구되는지 보낼수도 있음)  