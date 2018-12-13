package eureka.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

/**
 * Created by serv on 16/9/3.
 */
public class EurekaClientImpl implements EurekaClient {

    private String eurekaServiceUrl;

    public EurekaClientImpl(String eurekaServiceUrl) {
        if (eurekaServiceUrl.endsWith("/")) {
            eurekaServiceUrl = eurekaServiceUrl.substring(0, eurekaServiceUrl.length() - 1);
        }
        this.eurekaServiceUrl = eurekaServiceUrl;
    }


    @Override
    public void registerInstance(InstanceInfo instanceInfo) {
        String url = String.format(eurekaServiceUrl + "/apps/%s", instanceInfo.getId());
        EurekaNetUtils.post(url, InstanceInfo.class, instanceInfo);
    }

    @Override
    public void deleteInstance(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        EurekaNetUtils.delete(url);
    }

    @Override
    public void heartbeat(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        EurekaNetUtils.put(url);
    }

    @Override
    public Applications applications() {
        String url = eurekaServiceUrl + "/apps";
        return EurekaNetUtils.get(url, Applications.class);
    }

    @Override
    public Application application(String appName) {
        String url = String.format(eurekaServiceUrl + "/apps/%s", appName);
        return EurekaNetUtils.get(url, Application.class);
    }

    @Override
    public InstanceInfo instance(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s", appName, instanceId);
        return EurekaNetUtils.get(url, InstanceInfo.class);
    }

    @Override
    public InstanceInfo instance(String instanceId) {
        String url = String.format(eurekaServiceUrl + "/instances/%s", instanceId);
        return EurekaNetUtils.get(url, InstanceInfo.class);
    }

    @Override
    public void outOfService(String appName, String instanceId) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=OUT_OF_SERVICE", appName, instanceId);
        EurekaNetUtils.put(url);
    }

    @Override
    public void backInService(String appName, String instanceId, EurekaInstanceStatus status) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/status?value=%s", appName, instanceId, status.name());
        EurekaNetUtils.delete(url);
    }

    @Override
    public void updateMetadata(String appName, String instanceId, String key, String value) {
        String url = String.format(eurekaServiceUrl + "/apps/%s/%s/metadata?%s=%s", appName, instanceId, key, value);
        EurekaNetUtils.put(url);
    }

    @Override
    public Applications vips(String vipAddress) {
        String url = String.format(eurekaServiceUrl + "/vips/%s", vipAddress);
        return EurekaNetUtils.get(url, Applications.class);
    }

    @Override
    public Applications svips(String svipAddress) {
        String url = String.format(eurekaServiceUrl + "/svips/%s", svipAddress);
        return EurekaNetUtils.get(url, Applications.class);
    }
}
