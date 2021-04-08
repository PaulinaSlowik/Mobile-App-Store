package service;

import lombok.Getter;

@Getter
public class ProviderAppVersionData {
    private String providerName;
    private String appName;
    private String linkAppStore;
    private String backendVersion;
    private String newestVersion;

    public ProviderAppVersionData(
            String providerName,
            String appName,
            String linkAppStore,
            String backendVersion,
            String newestVersion) {
        this.providerName = providerName;
        this.appName = appName;
        this.linkAppStore = linkAppStore;
        this.backendVersion = backendVersion;
        this.newestVersion = newestVersion;
    }
}
