package eureka.client;

/**
 * Created by serv on 16/9/3.
 */
public interface EurekaClient {

    /**
     * 注册一个应用实例
     * @param appId 应用id
     * @param payload json或者xml
     */
    void registerInstance(String appId,String payload);


    /**
     * 删除一个实例
     * @param appId 应用id
     * @param instanceId 实例id
     */
    void deleteInstance(String appId,String instanceId);

    /**
     * 发送一个应用实例心跳
     * @param appId 应用id
     * @param instanceId 实例id
     */
    void heartbeat(String appId,String instanceId);

    /**
     * 列出所有实例
     * @return json/xml
     */
    String instances();

    /**
     * 列出应用下的所有实例
     * @param appId 应用id
     * @return json/xml
     */
    String instances(String appId);

    /**
     * 查询指定的实例
     * @param appId 应用id
     * @param instanceId 实例id
     * @return json/xml
     */
    String instance(String appId,String instanceId);

    /**
     * 查询特定的实例
     * @param instanceId 实例id
     * @return json/xml
     */
    String instance(String instanceId);

    /**
     * 中止/失效一个实例
     * @param appId 应用id
     * @param instanceId 实例id
     */
    void outOfService(String appId,String instanceId);

    /**
     * 恢复一个实例到指定状态
     * @param appId 应用id
     * @param instanceId 实例id
     * @param status json/xml
     */
    void backInService(String appId, String instanceId, EurekaInstanceStatus status);

    /**
     * 更新实例的元数据
     * @param appId 应用id
     * @param instanceId 实例id
     * @param key 键
     * @param value 值
     */
    void updateMetadata(String appId,String instanceId,String key,String value);

    /**
     * 在一个特定的vip地址查询所有实例
     * @param vipAddress vip地址
     * @return json/xml
     */
    String vips(String vipAddress);

    /**
     * 在一个特定的安全vip地址查询所有实例
     * @param svipAddress 安全的vip地址
     * @return json/xml
     */
    String svips(String svipAddress);
}
