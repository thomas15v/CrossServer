import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.util.ConnectionStatus;
import com.thomas15v.crossserver.server.CrossServer;
import impl.TestPlayer;
import impl.TestPlugin;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by thomas15v on 26/12/14.
 *
 * I know that Thread.sleep() in unit tests is bad. But I am to leazy now to fix these test.
 * Knowing myself, I probally will remove these test if things get more stable
 *
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
        Plugin client = new TestPlugin();
        Thread.sleep(50);
        boolean value = client.getCrossServer().getStatus() == ConnectionStatus.CONNECTED;
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

    @Test
    public void testPlayerJoin() throws InterruptedException {
        Plugin plugin1 = new TestPlugin("AddedPlayer");
        Thread.sleep(100);
        if (plugin1.getCrossServer().getStatus() == ConnectionStatus.CONNECTED)
        {
            plugin1.getLocalServer().addPlayer(new TestPlayer("Thomas15v"));
        }
        Thread.sleep(200);
        Assert.assertTrue(crossServer.getClients().get(plugin1.getLocalServer().getName()).getPlayer("Thomas15v") != null);
    }

    @AfterClass
    public static void cleanup(){
        crossServer.stop();
    }



}
