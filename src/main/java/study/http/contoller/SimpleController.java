package study.http.contoller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.http.dto.RequestMember;
import study.http.dto.ResponseMember;
import study.http.serivce.MemberService;

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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMember> getMember(@PathVariable Long id){
        ResponseMember member = memberService.get(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<ResponseMember> save(@RequestBody RequestMember requestMember){
        ResponseMember member = memberService.save(requestMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        memberService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
