package fetcher.appstore_fetcher.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VersionEntity {

    @JsonProperty("versionDisplay")
    private String iOsAppVersion;

    private String releaseNotes;

    private String releaseDate;
}
