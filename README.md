# eureka-client


[![](https://jitpack.io/v/izerui/eureka-client.svg)](https://jitpack.io/#izerui/eureka-client)

---

### SETUP 1

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### SETUP 2

```
<dependency>
    <groupId>com.github.izerui</groupId>
    <artifactId>eureka-client</artifactId>
    <version>1.0.3.RELEASE</version>
</dependency>
```

### usage

```
@Test
    public void registerTest(){
        InstanceInfo instanceInfo = InstanceInfo.Builder.newBuilder()
                .setInstanceId("99999")
                .setAppName("99999")
                .setIPAddr("127.0.0.1")
                .setHostName("127.0.0.1")
                .setPort(8832)
                .setVIPAddress("aaa")
                .setDataCenterInfo(() -> DataCenterInfo.Name.MyOwn)
                .build();
        client.registerInstance(instanceInfo);
    }

    @Test
    public void deleteTest(){
        client.deleteInstance("EUREKA-SERVER","192.168.0.107:eureka-server:3030");
    }

    @Test
    public void applicationsTest(){
        Applications applications = client.applications();
        for (Application application : applications.getRegisteredApplications()) {
            System.out.println(application);
        }
    }

    @Test
    public void applicationTest(){
        Application application = client.application("SALES-API");
        for (InstanceInfo instance : application.getInstances()) {
            System.out.println(instance.getId());
        }
    }

    @Test
    public void instanceTest(){
        InstanceInfo instance = client.instance("dev181:sales-api:50003");
        System.out.println(instance.getId());
        System.out.println(instance.getAppName());
    }

    @Test
    public void updateMetadataTest(){
        InstanceInfo instance = client.instance("dev181:sales-api:50003");
        client.updateMetadata(instance.getAppName(),instance.getInstanceId(),"ff","fsss");
        InstanceInfo result = client.instance("dev181:sales-api:50003");
        System.out.println(result.getMetadata());
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
    public void vipsTest(){
        Applications vips = client.vips("192.168.1.236:3030");
        System.out.println(vips);
    }

    @Test
    public void svipsTest(){
        Applications svips = client.svips("eureka-server");
        System.out.println(svips);
    }
```

### see

> https://github.com/Netflix/eureka/wiki/Eureka-REST-operations

note: Before changing the state, you need to out of service !!!
