package com.choo.blog.commons.response;


import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import java.util.Optional;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private HttpStatus status;
    private Integer result;
    private T body;

    public ApiResponse(HttpStatus status){
        this(status,  status.isError() ? -1 : 0, null);
    }

    public ApiResponse(@Nullable T body, HttpStatus status, Integer result){
        this(status, result, body);
    }

    public static BodyBuilder status(HttpStatus status){
        return new DefaultBuilder(status);
    }

    public static BodyBuilder status(HttpStatus status, Integer result){
        return new DefaultBuilder(status, result);
    }

    public static BodyBuilder ok(){
        return ok(0);
    }

    public static BodyBuilder ok(Integer result){
        return status(HttpStatus.OK, result);
    }

    public static <T> ApiResponse<T> ok(@Nullable T body){
        return ok().body(body);
    }

    public static <T> ApiResponse<T> of(Optional<T> body){
        return body.map(ApiResponse::ok).orElseGet(() -> notFound().build());
    }

    public ResponseEntity toResponse(){
        return ResponseEntity.ok(this);
    }

    public static BodyBuilder notFound(){
        return status(HttpStatus.NOT_FOUND);
    }

    public static BodyBuilder badRequest(){
        return status(HttpStatus.BAD_REQUEST);
    }

    public interface BodyBuilder{
        <T> ApiResponse<T> body(@Nullable T body);

        <T> ApiResponse<T> build();
    }

    private static class DefaultBuilder implements BodyBuilder{
        private final HttpStatus status;
        private final Integer result;

        public DefaultBuilder(HttpStatus status, Integer result){
            this.status = status;
            this.result = result;
        }


        public DefaultBuilder(HttpStatus status){
            this.status = status;
            this.result = status.isError() ? -1 : 0;
        }

        @Override
        public <T> ApiResponse<T> body(T body) {
            return new ApiResponse<>(status, result, body);
        }

        @Override
        public <T> ApiResponse<T> build() {
            return body(null);
        }
    }
}
