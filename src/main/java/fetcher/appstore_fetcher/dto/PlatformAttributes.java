package fetcher.appstore_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlatformAttributes {

    @JsonProperty("ios")
    private IosAppDetails iosAppDetails;
}
