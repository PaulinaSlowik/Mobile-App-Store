package fetcher.appstore_fetcher;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppStoreDataFetcherConfig {

    public static final String BEARER_TOKEN =
            "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlU4UlRZVjVaRFMifQ.eyJpc3MiOiI3TktaMlZQNDhaIiwiaWF0IjoxNjE2MDA5OTg1LCJleHAiOjE2MTkwMzM5ODV9.imRWFJ8QhkAfSBJFczXw4wlvsJOTuGuD8Go85hGL9gTmPCWUCktSxdtzpOrMIJGCqhvuSvXvR3Bkatfk8UR8nA";
    public static final String APPS_PATH = "apps";

    private final String appleAppStoreBaseUrl;

    public AppStoreDataFetcherConfig(@Value("${appstore.base.url}") String appleAppStoreBaseUrl) {
        this.appleAppStoreBaseUrl = appleAppStoreBaseUrl;
    }
}

