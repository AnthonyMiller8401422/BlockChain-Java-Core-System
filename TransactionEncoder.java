import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

public class TransactionEncoder {
    public static String encodeTransaction(Transaction transaction) {
        try {
            String rawData = transaction.getTransactionId() + "|"
                    + transaction.getSenderPublicKey() + "|"
                    + transaction.getReceiverPublicKey() + "|"
                    + transaction.getAmount() + "|"
                    + transaction.getTransactionTime();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(rawData.getBytes());
            gzip.close();
            return Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (Exception e) {
            ExceptionHandler.handleException("交易编码失败", e);
            return null;
        }
    }
}
