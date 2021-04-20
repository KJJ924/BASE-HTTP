package study.http.serivce;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import study.http.dao.MemberRepository;
import study.http.domain.Member;
import study.http.dto.RequestMember;
import study.http.dto.ResponseMember;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    public List<ResponseMember> getList() {
        List<Member> memberList = memberRepository.findAll();

        return memberList.stream()
                .map(member -> modelMapper.map(member, ResponseMember.class))
                .collect(Collectors.toList());
    }

    public ResponseMember get(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(member,ResponseMember.class);
    }

    public ResponseMember save(RequestMember requestMember) {
        Member member = memberRepository.save(requestMember.toEntity());
        return modelMapper.map(member,ResponseMember.class);
    }

    public void delete(Long id) {
        if(memberRepository.existsById(id)){
            memberRepository.deleteById(id);
        }else{
            throw new IllegalArgumentException();
        }
    }
}
