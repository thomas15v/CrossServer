package impl;

import com.thomas15v.crossserver.api.Plugin;
import com.thomas15v.crossserver.api.remote.CrossServer;
import com.thomas15v.crossserver.api.remote.Server;
import com.thomas15v.crossserver.client.Client;
import lombok.Getter;

/**
 * Created by thomas15v on 28/12/14.
 */
public class TestPlugin implements Plugin {

    private static int namenumber;

    private Server server;

    @Getter
    private CrossServer crossServer;

    public TestPlugin(){
        this("TestServer" + namenumber);
        namenumber++;
    }

    public TestPlugin(String name){
        server = new TestServer(name, this);
        crossServer = new Client(this);
        crossServer.run();
    }

    @Override
    public Server getLocalServer() {
        return server;
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public void execute(Runnable task) {
        task.run();
    }
}
