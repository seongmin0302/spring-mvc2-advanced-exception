package seongmin0302.exception.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionController {
    
    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletRequest request) {

        log.info("확인해보겠습니다 ! Accept Header = {}", request.getHeader("Accept"));

        if(id.equals("ex")) {
            throw new RuntimeException("이것은 잘못된 사용자 입니다. ㅠㅠㅠ");
        }

        return new MemberDto(id, "회원의 이름을 소개합니다: " + id);
    }

    @AllArgsConstructor
    @Data
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
