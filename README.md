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
EurekaClient client = new EurekaClientImpl("http://192.168.1.238:3030/eureka/");
client.registerInstance(APPID,PAYLOAD);
// ... other api
```

### see

> https://github.com/Netflix/eureka/wiki/Eureka-REST-operations