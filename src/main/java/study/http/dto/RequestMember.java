package study.http.dto;

import lombok.Getter;
import lombok.Setter;
import study.http.domain.Member;

@Getter
@Setter
public class RequestMember {

    private String id;
    private String pw;
    private String local;
    private Integer age;

    public Member toEntity() {
        return Member.builder()
            .id(id)
            .pw(pw)
            .local(local)
            .age(age)
            .build();
    }
}
