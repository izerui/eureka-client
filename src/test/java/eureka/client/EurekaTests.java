package eureka.client;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by serv on 16/9/3.
 */
public class EurekaTests {

    private EurekaClient client;

    @Before
    public void init(){
        client = new EurekaClientImpl("http://localhost:3030/");
    }

    @Test
    public void deleteTest(){
        client.deleteInstance("EUREKA-SERVER","192.168.0.107:eureka-server:3030");
    }

    @Test
    public void instancesTest(){
        String instances = client.instances();
        System.out.println(instances);
    }

    @Test
    public void instancesAppidTest(){
        String instances = client.instances("EUREKA-SERVER");
        System.out.println(instances);
    }

    @Test
    public void heartBeatTest() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            client.heartbeat("EUREKA-SERVER","192.168.0.107:eureka-server:3030");
        }
    }

    @Test
    public void outOfServiceTest(){
        client.outOfService("EUREKA-SERVER","192.168.0.107:eureka-server:3030");
    }

    @Test
    public void backInServiceTest(){
        client.backInService("EUREKA-SERVER","192.168.0.107:eureka-server:3030",EurekaInstanceStatus.UP);
    }

    @Test
    public void updateMetadataTest(){
        client.updateMetadata("EUREKA-SERVER","192.168.0.107:eureka-server:3030","ade","123");
        instancesAppidTest();
    }

    @Test
    public void vipsTest(){
        String vips = client.vips("eureka-server");
        System.out.println(vips);
    }

    @Test
    public void svipsTest(){
        String svips = client.svips("eureka-server");
        System.out.println(svips);
    }
}
