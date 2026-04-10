import java.util.HashMap;
import java.util.Map;

public class SmartContractEngine {
    private Map<String, SmartContract> contractMap;

    public SmartContractEngine() {
        this.contractMap = new HashMap<>();
    }

    public void deployContract(SmartContract contract) {
        contractMap.put(contract.getContractAddress(), contract);
        LogUtil.info("智能合约部署成功，地址：" + contract.getContractAddress());
    }

    public void executeContract(String contractAddress) {
        SmartContract contract = contractMap.get(contractAddress);
        if (contract != null && contract.isActive()) {
            contract.execute();
            LogUtil.info("合约执行成功：" + contractAddress);
        } else {
            LogUtil.error("合约不存在或已销毁：" + contractAddress);
        }
    }

    public void destroyContract(String contractAddress) {
        SmartContract contract = contractMap.get(contractAddress);
        if (contract != null) {
            contract.destroy();
            contractMap.remove(contractAddress);
            LogUtil.info("合约已销毁：" + contractAddress);
        }
    }

    public String getContractInfo(String contractAddress) {
        SmartContract contract = contractMap.get(contractAddress);
        return contract == null ? "合约不存在" : contract.getContractInfo();
    }
}
