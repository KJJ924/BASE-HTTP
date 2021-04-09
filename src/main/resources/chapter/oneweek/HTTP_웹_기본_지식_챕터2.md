<img src="https://cdn.inflearn.com/public/files/courses/326277/4df75704-dd5d-403f-be3c-6860251d4326/326277-kor-b.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.



# URI와 웹 브라우저 요청 흐름

## 항목

- URI
- IP(인터넷 프로토콜)

------------



## URI(Uniform Resource Identifier)

URI는 간단히 말한다면 리소스를 보여 줄 수 있도록 해주는 식별자이다.



하지만 



필자는 학교에서 URI 를 배울때 항상 URL , URN 은 항상 같이 무엇인지 배웠다.

그럼 왜 항상 URI 을 언급할때 URL ,URN 은 같이 나올까?



그 이유에 대해서는 URI은 URN 과 URL 을 포함하고 있기 때문이다.

<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpdtkechizj31hc0u0gy5.jpg" alt="image-20210409223555795" style="zoom: 33%;" />

그러면 URL 은 무엇이고 URN 은 무엇일까?

![image-20210409224018190](/Users/kimjajan/Library/Application Support/typora-user-images/image-20210409224018190.png)



**URL** 은 우리가 평소에 인터넷에 접속할때 흔히 사용하는 주소 형식이다.

특징이 있다면 <u>어떠한 자원에 대해서 위치와 어떤 자원을 원하는지 구체적으로 서술된 형태이다.</u>



**URN** 같은 경우는 자원에 대해서 이름만으로 리소스를 식별하는데 <u>해당 방식은 잘 사용하지 않는다.</u>

왜냐하면 이 방식만으로 자원을 찾기에는 한계가 존재한다. 

단 URL 같은 방식은 자원의 위치가 변하면 해당 자원을 가져없을수 있는 단점이 존재하지만

URN 방식같은경우 리소스의 이름만 안다면 얼마든지 가져올수 있다는 장점이 있다.



따라서 URL 이 곧 URI 이고 URI 이가 URL 이라고 이해해도 될 것 같다.(개인적 생각입니다.)



그렇다면 자주 쓰이는 URL 의 구조에대해 하나씩 알아보자.





## URL 구조

![image-20210409225306812](https://tva1.sinaimg.cn/large/008eGmZEgy1gpdu206rlvj32j60juwj8.jpg)



URL 의 구조는 다음으로 나뉘어진다.

1. scheme
2. authority(사용자정보,호스트,포트)
3. path
4. query
5. fragment



하나씩 알아보자.



### scheme

- 어떠한 프로토콜을 사용할지 알리는데 사용한다
- Ex) Http, Https , ftp 등등



### authority

- authority 은 3가지로 나눌수 있는데 사용자정보, 호스트(host), 포트(port) 로 나뉜다.
- 사용자 정보
  - 말 그대로 사용자정보를 포함하는 것인데 거의 사용하지 않는다
  - 만약 사용자정보가 있다면 @ 으로 끝난다.
- 호스트(Host)
  - 우리가 흔히 알고있는 홈페이지 주소라고 생각하면 된다
  - naver.com, google.com, ip 주소로 되어있어도 호스트이다.
- 포트(Port)
  - 서버의 접속할 포트번호이다
  - 일반적으로 생략 하며 생략시 http는 80, https 는 443 이다.



### Parh

- 리소스의 경로를 나타내며 계층 구조를 가지고있다.
- Ex) /member/100 , /members , /home



### query

-  일반적으로 query parameter  라고 부르며 ? 으로 시작하고  key = value 형태를 가지고있다.
-  웹서버에 제공하는 파라미터 이며 해당 파라미터는 항상 스트링이다.
-  ex) /member**?name=kjj&&address=seoul** 



### fragment

- 서버에 전송하는 정보는 아니다 주로 html 내부 북마크 등에 사용한다.

