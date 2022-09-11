package club.kwcoder.vote.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> {

    private Integer code;
    private Boolean flag;
    private String message;
    private T data;

    /**
     * @return 200, true, message, null
     */
    public static <T> ResultBean<T> success(String message) {
        return success(message, null);
    }

    /**
     * @return 200, true, message, data
     */
    public static <T> ResultBean<T> success(String message, T data) {
        return new ResultBean<>(200, true, message, data);
    }

    public static <T> ResultBean<T> forbidden(String message) {
        return forbidden(message, null);
    }

    public static <T> ResultBean<T> forbidden(String message, T data) {
        return new ResultBean<>(403, false, message, data);
    }

    /**
     * 客户端应继续请求
     *
     * @return 100, true, message, null
     */
    public static <T> ResultBean<T> continueStatus(String message) {
        return continueStatus(message, null);
    }

    /**
     * 客户端应继续请求
     *
     * @return 100, true, message, data
     */
    public static <T> ResultBean<T> continueStatus(String message, T data) {
        return new ResultBean<>(100, true, message, data);
    }

    /**
     * @return 406, false, message, null
     */
    public static <T> ResultBean<T> notAcceptable(String message) {
        return new ResultBean<>(406, false, message, null);
    }

    /**
     * @return 500, false, 系统错误，请联系管理员解决, null
     */
    public static <T> ResultBean<T> systemError() {
        return new ResultBean<>(500, false, "系统错误，请联系管理员解决", null);
    }

}
