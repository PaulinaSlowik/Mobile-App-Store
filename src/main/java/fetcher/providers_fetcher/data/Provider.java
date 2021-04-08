package fetcher.providers_fetcher.data;

import lombok.Getter;

@Getter
public class Provider {

    @JsonIgnore private String accessType;
    private String name;
    private String backendVersion;

    public Provider(String providerName, String backedVersion) {
        this.name = providerName;
        this.backendVersion = backedVersion;
    }
}
