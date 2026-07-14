package seongmin0302.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import seongmin0302.exception.api.ApiExceptionController.MemberDto;
import seongmin0302.exception.exception.BadRequestException;
import seongmin0302.exception.exception.UserException;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
public class ApiExceptionController {
    
    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id, HttpServletRequest request) {

        log.info("확인해보겠습니다 ! Accept Header = {}", request.getHeader("Accept"));

        if(id.equals("ex")) {
            throw new RuntimeException("이것은 잘못된 사용자 입니다. ㅠㅠㅠ");
        }

        if(id.equals("bad")) {
            throw new IllegalArgumentException("이런 잘못된 값을 입력했네요 ㅠㅠㅠ");
        }

        if(id.equals("user-ex")) {
            throw new UserException("사용자 정의 오류 입ㄴ디ㅏㅇ");
        }

        return new MemberDto(id, "회원의 이름을 소개합니다: " + id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatus() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStausEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex") 
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }

    @AllArgsConstructor
    @Data
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
