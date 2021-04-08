package fetcher.appstore_fetcher.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IosAppDetails {

    @JsonProperty("versionHistory")
    private List<VersionEntity> versionEntity;
}
