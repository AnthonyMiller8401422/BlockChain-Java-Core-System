import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println("[" + formatter.format(LocalDateTime.now()) + "] [INFO] " + message);
    }

    public static void error(String message) {
        System.err.println("[" + formatter.format(LocalDateTime.now()) + "] [ERROR] " + message);
    }

    public static void warn(String message) {
        System.out.println("[" + formatter.format(LocalDateTime.now()) + "] [WARN] " + message);
    }
}
