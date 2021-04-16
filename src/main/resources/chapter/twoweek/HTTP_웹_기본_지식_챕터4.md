<img src="https://tva1.sinaimg.cn/large/008eGmZEgy1gpiejhlmqoj30lc0dwmzx.jpg" alt="1" style="zoom: 50%;" />

[모든 개발자를 위한 HTTP 웹 기본 지식](https://www.inflearn.com/course/http-웹-네트워크/dashboard) 을 듣고 정리한 내용이며 **모든 그림 예제는 해당 강의에서 가져온 내용입니다**.



# HTTP 메서드

## 항목

- HTTP API를 만들어보자
- HTTP 메서드 - GET, POST
- HTTP 메서드 - PUT, PATCH, DELETE
- HTTP 메서드의 속성



## HTTP API를 만들어보자



웹 개발자라면 한번쯤은 RESTful  API 이라는 문구를 보았을 것이다.

![image-20210416215618619](https://tva1.sinaimg.cn/large/008eGmZEgy1gplvr41udqj30cq049jrm.jpg) 

REST API 의 이해가 왜 중요한가? 또는 왜 필요할까? 라는생각이 든다.

(REST 규칙은 HTTP 의 장점을 최대한 활용할수 있는 아키텍처입니다.)

현 개발의 가장 중요한 부분은 소통과 협업이다. 우리는 개발을 진행함에 있어 절대로 혼자 코딩하고 혼자 배포하지않는다.

따라서 나만의 규칙보다는 관례와 규칙이 있다면 따라야한다. 왜냐하면 나만의 규칙과 습관은 자기한테는 쉽게 알아 볼 수 있겠지만 상대방은 전혀아니다.

이 부분을 다시한번 상기하고 HTTP API 를 만들어보자.



REST 구성은 다음과 같다.

- 자원(RESOURCE)
- 행위(Verb) 
- 표현(Representations) 

그렇다면 한번 만들어보자.

`/members/delete/1  (삭제)` `/members/get-list  (리스트 조회)`

 `/members/edit/1  (수정)` `/members/get/1  (조회)`

잘 설계된 API 처럼 보이는가? 전혀 아니다.

왜? [URI 는 리소스를 보여 줄 수 있도록 해주는 식별자라고 말했었다](https://k3068.tistory.com/83).

앞서 예시에는 URI는 자원과 행위가 같이 들어가 있다.



즉 URI 에서는 리소스만 식별해야 함으로 다음과 같이 변경해야한다.

`/members/1 (삭제)` `/members (리스트 조회)` 

`/members/1 (수정)` `/members/1 (조회)`

여기서 의문이 들 수 있다.  행위가 삭제되었음으로 URI 가 똑같은 것은 어떻게 구분할까? 

행위의 같은경우 HTTP 메서드를 통해 분리 하여 구분하면된다.





