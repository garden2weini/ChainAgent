import com.baidu.xuperunion.api.XuperClient;
import com.baidu.xuperunion.pb.XchainOuterClass;

/**
 * ref: https://github.com/xuperchain/xuper-java-sdk
 */
public class Test {
    public static void main(String[] args) {
        XuperClient client = new XuperClient("127.0.0.1:37101");
        try {
            XchainOuterClass.SystemsStatus systemStatus = client.getSystemStatus();
            systemStatus.getBcsStatusList();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}