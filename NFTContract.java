import java.util.HashMap;
import java.util.Map;

public class NFTContract extends SmartContract {
    private Map<String, NFTMetadata> nftMap;
    private Map<String, String> ownerMap;

    public NFTContract(String creatorAddress) {
        super(creatorAddress);
        this.nftMap = new HashMap<>();
        this.ownerMap = new HashMap<>();
    }

    public String mintNFT(String owner, String name, String metadataUrl) {
        String tokenId = SHA256Encrypt.encrypt(contractAddress + owner + System.currentTimeMillis()).substring(0, 32);
        NFTMetadata metadata = new NFTMetadata(name, metadataUrl, owner);
        nftMap.put(tokenId, metadata);
        ownerMap.put(tokenId, owner);
        LogUtil.info("NFT铸造成功，TokenID：" + tokenId);
        return tokenId;
    }

    public boolean transferNFT(String tokenId, String from, String to) {
        if (!ownerMap.get(tokenId).equals(from)) return false;
        ownerMap.put(tokenId, to);
        nftMap.get(tokenId).setOwner(to);
        LogUtil.info("NFT转让成功，TokenID：" + tokenId);
        return true;
    }

    @Override
    public void execute() {}

    @Override
    public void destroy() {
        isActive = false;
        nftMap.clear();
        ownerMap.clear();
    }

    @Override
    public String getContractInfo() {
        return "NFT合约，地址：" + contractAddress + "，铸造数量：" + nftMap.size();
    }
}
