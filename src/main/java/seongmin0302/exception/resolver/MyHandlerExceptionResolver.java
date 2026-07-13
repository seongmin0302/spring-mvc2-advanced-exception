package seongmin0302.exception.resolver;

import org.jspecify.annotations.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public @Nullable ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
@Nullable Object handler, Exception ex) {

        log.info("resolveException 실행되었습ㄴ디ㅏ");
        
        try {
            if(ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException을 404예외로 바꿔보겠습니다.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
            }
        } catch (Exception e) {
            log.error("reselver ex", e);
        }

        return null;
    }
    

}
