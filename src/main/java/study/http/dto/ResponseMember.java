package study.http.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMember {

    private Long no;
    private String id;
    private String pw;
    private String local;
    private Integer age;

}
