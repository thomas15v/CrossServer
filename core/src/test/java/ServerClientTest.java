import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.api.util.ServerStatus;
import com.thomas15v.crossserver.client.Client;
import com.thomas15v.crossserver.server.CrossServer;
import impl.TestServer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by thomas15v on 26/12/14.
 */
public class ServerClientTest {

    public static CrossServer crossServer;

    @BeforeClass
    public static void setupServer(){
        crossServer = new CrossServer();
        new Thread(crossServer).start();
    }

    @Test
    public void TestLogin() throws InterruptedException {
        Assert.assertTrue(startAndQuitClient());
    }

    public boolean startAndQuitClient() throws InterruptedException {
        Client client = new Client(new TestServer("TestServer", ServerStatus.ONLINE));
        client.run();
        Thread.sleep(50);
        boolean value = client.getStatus() == ConnectionStatus.CONNECTED;
        client.getConnectionHandler().getChannel().disconnect();
        return value;
    }

    @Test
    public void testClientJoinConnect() throws InterruptedException {
        boolean value = true;
        for (int i = 0; 10 > i; i++) {
            if (!startAndQuitClient())
                value = false;
        }
        Assert.assertTrue(value);
    }

    public void testLoginWithSameName(){

    }

    @AfterClass
    public static void cleanup(){
        crossServer.stop();
    }



}
