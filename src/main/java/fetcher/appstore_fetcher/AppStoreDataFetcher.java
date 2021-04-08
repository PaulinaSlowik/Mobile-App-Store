package fetcher.appstore_fetcher;

package se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher;

import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.data.AppStoreResponse;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@RequiredArgsConstructor
@Component
public class AppStoreDataFetcher {

    private final RestTemplate restTemplate;
    private final ProviderAppPatternDataStorage dataStorage;
    private final AppStoreDataFetcherConfig fetcherConfig;

    public AppStoreResponse fetchAppDataById(String appId) {
        String specificBankAppUrl = buildAppStoreAppUrl(appId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(AppStoreDataFetcherConfig.BEARER_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate
                .exchange(specificBankAppUrl, HttpMethod.GET, entity, AppStoreResponse.class)
                .getBody();
    }

    private String buildAppStoreAppUrl(String appId) {
        String marketForSpecificId = dataStorage.getMarketCodeForAppId(appId);

        DefaultUriBuilderFactory builderFactory =
                new DefaultUriBuilderFactory(fetcherConfig.getAppleAppStoreBaseUrl());

        UriBuilder uriBuilder = builderFactory.builder();

        return uriBuilder
                .pathSegment(marketForSpecificId)
                .pathSegment(AppStoreDataFetcherConfig.APPS_PATH)
                .pathSegment(appId)
                .queryParam("platform", "iphone")
                .queryParam("extend", "versionHistory")
                .build()
                .toString();
    }
}
