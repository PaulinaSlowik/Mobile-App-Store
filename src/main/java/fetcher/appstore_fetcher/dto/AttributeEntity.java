package fetcher.appstore_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttributeEntity {
    @JsonProperty("artistName")
    private String appName;

    private String url;

    private PlatformAttributes platformAttributes;
}

