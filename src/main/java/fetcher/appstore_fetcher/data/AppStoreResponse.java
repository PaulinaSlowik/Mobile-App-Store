package fetcher.appstore_fetcher.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.AppEntity;

@Setter
@Getter
public class AppStoreResponse {

    @JsonProperty("data")
    private List<AppEntity> apps;
}
