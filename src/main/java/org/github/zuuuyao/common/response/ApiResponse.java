package org.github.zuuuyao.common.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * @Desc 接口响应包装类
 * @Time 2024-07-12 16:02
 * @Author HuangZhongYao
 */
public class ApiResponse<Result> extends AbstractResponse {

    @Serial
    private static final long serialVersionUID = 5094140230570937764L;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private Result result;

    public ApiResponse(Integer code, String msg, String path, Boolean success, Result result) {
        super(code, msg, path, success);
        this.result = result;
    }

    public ApiResponse(Result result) {
        this.result = result;
    }

    public ApiResponse() {

    }

    /**
     * 创建一个表示操作成功的响应对象。
     *
     * @param result 操作成功的结果数据。
     * @param <Result> 结果数据的泛型类型。
     * @return 返回一个包含成功结果的响应对象。
     */
    public static <Result> ApiResponse ok(Result result) {
        return new ApiResponse<Result>(OK, "操作成功", "", true, result);

    }

    /**
     * 创建一个表示操作成功的响应对象。
     *
     * @param result 操作成功的结果数据。
     * @param msg 响应信息
     * @param <Result> 结果数据的泛型类型。
     * @return 返回一个包含成功结果的响应对象。
     */
    public static <Result> ApiResponse ok(Result result, String msg) {
        return new ApiResponse<Result>(OK, msg, "", true, result);

    }

    /**
     * 创建一个表示失败结果的R对象。
     * <p>
     * 这个方法提供了一个简洁的方式，来创建一个表示操作失败的R对象，其中包含失败信息但不包括具体的错误代码。
     * 使用者可以通过传入一个描述失败原因的字符串来定制这个失败结果。
     *
     * @param msg 失败的详细信息，描述了操作失败的原因。
     * @return 返回一个新的R对象，表示操作失败。
     */
    public static ApiResponse failed(String msg) {
        return new ApiResponse(FAILED, msg, "", false, null);

    }


    /**
     * 创建一个表示失败结果的R对象，带有自定义错误代码。
     * <p>
     * 这个方法扩展了创建失败R对象的功能，允许用户指定一个特定的错误代码来更准确地描述失败的原因。
     * 这对于需要根据错误代码进行不同处理逻辑的场景非常有用。
     *
     * @param msg   失败的详细信息，描述了操作失败的原因。
     * @param code  自定义的错误代码，用于更精确地描述失败的原因。
     * @return 返回一个新的R对象，表示操作失败，并携带自定义的错误代码。
     */
    public static ApiResponse failed(String msg, Integer code) {
        return new ApiResponse(code, msg, "", false, null);

    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
            " 'result' :" + result +
            ", 'code' :" + code +
            ", 'msg' : '" + msg + '\'' +
            ", 'path' : '" + path + '\'' +
            ", 'success' : " + success +
            "}";
    }
}
