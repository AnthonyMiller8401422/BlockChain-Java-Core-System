import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

public class TransactionDecoder {
    public static String decodeTransaction(String encodedData) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedData);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            GZIPInputStream gzip = new GZIPInputStream(bis);
            byte[] buffer = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = gzip.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            gzip.close();
            return sb.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException("交易解码失败", e);
            return null;
        }
    }
}
