<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejhlmqoj30lc0dwmzx.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.





## HTTP 메소드 활용 



`/members/1 (삭제)` `/members (리스트 조회)` 

`/members/1 (삭제)` `/members/1 (조회)`

앞서 URI 에는 자원만을 표현  행위는 HTTP 메소드로 구분한다고 하였다.

이번에는 이론적인 내용이아닌 Spring  을 이용하여 예제 코드를 만들어보자.

(본문 길이상 Controller 를 제외한 나머지 코드는 생략하겠습니다.  [나머지코드보기](https://github.com/KJJ924/BASE-HTTP/tree/main/src/main/java/study/http))



## 회원 저장(/members)

새로운 리소스나 프로세스를 진행 할때에는 POST Method 를 사용한다고 하였다.

그렇다면 POST /member 형식으로 컨트롤러를 만들면 된다.

```java
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SimpleController {
    private final MemberService memberService;
   @PostMapping
    public ResponseEntity<ResponseMember> save(@RequestBody RequestMember requestMember){
        ResponseMember member = memberService.save(requestMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}
```

그렇게 어렵지않다. 하지만 여기서 생각해야할 부분이 하나 있다.

기존 개발자들이 부르는 REST API 는 대부분 REST API 의 형식을 지키고 있지않다. 

[참고: 그런 REST API 괜찮은가?](https://tv.naver.com/v/2292653)

영상 일부 내용중 

> *대부분 REST API 를 설계에 있어서 자원이 다음상태로 전이될 수 있는 LINK 들을 제공하지 않는다.*

내용이 있다 해당 내용은 [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS) 라고 부른다.

즉 다음과 같은 상황이다.



### **요청**

```http
POST http://localhost:8080/members
Content-Type: application/json
  
{
  "id" : "dkansk924@naver.com",
  "pw" : "password",
  "local": "seoul",
  "age": 9999
}
```

### **응답**

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gpsnlaeux0j30r20lytaq.jpg" alt="image-20210422182402235" style="zoom:50%;" />

다음과 같은 응답결과는 다음상태로 전이 할수 없는 응답 객체이다.

즉 자원의 위치로 갈 수 있는 LINK 를 제공을 해주지 않는다. 



따라서 좀 더 REST API 한 응답은 다음과 같아야 한다.

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gpsnlgp596j30qi0kq0um.jpg" alt="image-20210422183244277" style="zoom:50%;" />

완벽한 포맷은 아니지만 이렇게 다음상대로 전이 시킬수 있는 URI 를 제공해준다면 어떠한 장점이 있을까?
(현재는 본문(body)에 link 를 제공하고 있지만 본문이 아니라 header 영역에서 제공해도 됩니다)

프론트엔드와 백엔드가 분리되어 있는경우 프론트엔드에서는 URI 를 제공해주지않는다면 응답결과의 no 필드의 값을 확인 후

URI 를 만들어 (/member/no) 요청을 다시 서버로 보낼 것이다.

어찌됫든 앞단에서 필드의 값을 보고 조합하는 일이 발생하는데 만약 어떠한 이유로 요청 URI 가 변경되었다고 가정해보자

즉 백엔드에서 요청을 받는 Controller 의 맵핑 이 달라졌다는 뜻인데 기존 같은경우 조합해서 요청을 보내기때문에

백엔드에서 변경이 생긴건데 프론트엔드까지 URI 를 조합하는 로직이 변경이 일어나야한다.

하지만 URI 를 제공해준다면 프론트엔드에서 백엔드에서 내려주는 URI 만 호출해준다면 앞서 발생하는 문제점은 해결된다.



단점은 모든 전이 상태에 대한 URI 를 제공하기는 생각보다 쉽지않다.

따라서 이러한 방법도 있다는 것을 알고 팀 프로젝트의 형식에 맞는 REST API 를 만들어 제공하자 !

 

## 특정 회원 가져오기(/members/{no})

어떠한 자원을 가져오는 경우 GET 메서드를 사용한다.

```java
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SimpleController {

    private final MemberService memberService;
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseMember> getMember(@PathVariable Long id){
        ResponseMember member = memberService.get(id);
        return ResponseEntity.ok(member);
    }
}
```



### **요청**

```http
### 특정 회원 가져오기
GET http://localhost:8080/members/1
```



### **응답**

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gpsof2jyatj30qm0lsdht.jpg" alt="image-20210422190131623" style="zoom:50%;" />



## 회원 리스트 가져오기(/members)

해당 부분도 어떠한 자원을 가져오는 경우임으로 GET 메서드를 사용한다.

```java
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SimpleController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<ResponseMember>> getMemberList(){
        List<ResponseMember> memberList = memberService.getList();
        return ResponseEntity.ok(memberList);
    }
}
```



### 요청

```http
### 회원 목록 가져오기
GET http://localhost:8080/members
```

### **응답**

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gpsolw2l8ij30rm0xegt4.jpg" alt="image-20210422190800519" style="zoom:50%;" />



## 특정 회원 삭제하기(/members/{no})

대상 리소스를 삭제 하는경우 DELETE 메서드를 사용한다.

응답의 결과로는  200(OK) , 204(Not Content) 를 반환하면 된다.

```java
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SimpleController {

    private final MemberService memberService;
  
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
```



### 요청

```http
### 특정 회원 삭제하기
DELETE http://localhost:8080/members/1
```

### 응답

<img src="https://tva1.sinaimg.cn/large/008i3skNgy1gpsowgzv74j30po0cet9s.jpg" alt="image-20210422191816570" style="zoom:50%;" />

