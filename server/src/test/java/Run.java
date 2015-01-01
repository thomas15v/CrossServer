import java.io.IOException;

/**
 * Created by thomas15v on 28/12/14.
 */
public class Run {
    public static void main(String[] args) throws InterruptedException, IOException {
        new ServerClientTest().testPlayerJoin();
        System.out.println("this closed");
        System.in.read();
    }

}
