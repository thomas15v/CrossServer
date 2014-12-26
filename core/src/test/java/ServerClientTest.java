import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.server.CrossServer;
import impl.TestServer;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ServerClientTest {

    @BeforeClass
    public static void setupServer(){
        CrossServer.main(null);
    }

    @Test
    public void runClientTests(){
        Client client = new Client(new TestServer("TestServer", ServerStatus.ONLINE));
        client.run();
    }

}
