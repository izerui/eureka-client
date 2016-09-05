package eureka.client;

/**
 * Created by serv on 16/9/3.
 */
public class EurekaClientImpl implements EurekaClient {

    private String eurekaServiceUrl;

    public EurekaClientImpl(String eurekaServiceUrl) {
        if(eurekaServiceUrl.endsWith("/")){
            eurekaServiceUrl = eurekaServiceUrl.substring(0,eurekaServiceUrl.length()-1);
        }
        this.eurekaServiceUrl = eurekaServiceUrl;
    }


    @Override
    public void registerInstance(String appId, String payload) {
        // 暂时不打算实现
        throw new EurekaClientException("未实现该功能",1001);
    }

    @Override
    public void deleteInstance(String appId, String instanceId) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s",appId,instanceId);
        EurekaNetUtils.delete(url);
    }

    @Override
    public void heartbeat(String appId, String instanceId) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s",appId,instanceId);
        EurekaNetUtils.put(url);
    }

    @Override
    public String instances() {
        String url = eurekaServiceUrl+"/apps";
        return EurekaNetUtils.get(url);
    }

    @Override
    public String instances(String appId) {
        String url = String.format(eurekaServiceUrl+"/apps/%s",appId);
        return EurekaNetUtils.get(url);
    }

    @Override
    public String instance(String appId, String instanceId) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s",appId,instanceId);
        return EurekaNetUtils.get(url);
    }

    @Override
    public String instance(String instanceId) {
        String url = String.format(eurekaServiceUrl+"/instances/%s",instanceId);
        return EurekaNetUtils.get(url);
    }

    @Override
    public void outOfService(String appId, String instanceId) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s/status?value=OUT_OF_SERVICE",appId,instanceId);
        EurekaNetUtils.put(url);
    }

    @Override
    public void backInService(String appId, String instanceId, EurekaInstanceStatus status) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s/status?value=%s",appId,instanceId,status.name());
        EurekaNetUtils.delete(url);
    }

    @Override
    public void updateMetadata(String appId, String instanceId, String key, String value) {
        String url = String.format(eurekaServiceUrl+"/apps/%s/%s/metadata?%s=%s",appId,instanceId,key,value);
        EurekaNetUtils.put(url);
    }

    @Override
    public String vips(String vipAddress) {
        String url = String.format(eurekaServiceUrl+"/vips/%s",vipAddress);
        return EurekaNetUtils.get(url);
    }

    @Override
    public String svips(String svipAddress) {
        String url = String.format(eurekaServiceUrl+"/svips/%s",svipAddress);
        return EurekaNetUtils.get(url);
    }
}
