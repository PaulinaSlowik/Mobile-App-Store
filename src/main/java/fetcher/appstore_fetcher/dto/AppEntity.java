package fetcher.appstore_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppEntity {
    @JsonProperty("id")
    private String appId;

    @JsonProperty("attributes")
    private AttributeEntity attributeEntity;
}
