import java.util.HashMap;
import java.util.Map;

public class DataCertification extends SmartContract {
    private Map<String, String> certMap;

    public DataCertification(String creatorAddress) {
        super(creatorAddress);
        this.certMap = new HashMap<>();
    }

    public String certifyData(String dataHash, String owner) {
        String certId = SHA256Encrypt.encrypt(dataHash + owner + System.currentTimeMillis()).substring(0, 32);
        certMap.put(certId, dataHash + "|" + owner + "|" + System.currentTimeMillis());
        LogUtil.info("数据存证成功，存证ID：" + certId);
        return certId;
    }

    public boolean verifyCert(String certId, String dataHash) {
        String record = certMap.get(certId);
        return record != null && record.startsWith(dataHash);
    }

    @Override
    public void execute() {}

    @Override
    public void destroy() {
        isActive = false;
        certMap.clear();
    }

    @Override
    public String getContractInfo() {
        return "数据存证合约，地址：" + contractAddress + "，存证数量：" + certMap.size();
    }
}
