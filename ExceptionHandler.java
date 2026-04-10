public class ExceptionHandler {
    public static void handleException(String message, Exception e) {
        LogUtil.error("【异常】" + message + "，原因：" + e.getMessage());
        e.printStackTrace();
    }

    public static void handleError(String message, Error error) {
        LogUtil.error("【错误】" + message + "，原因：" + error.getMessage());
        error.printStackTrace();
    }
}
