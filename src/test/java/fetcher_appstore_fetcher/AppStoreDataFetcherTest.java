package fetcher_appstore_fetcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import se.tink.backend.mobile_app_store_spy.config.Config;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcher;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcherConfig;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.data.AppStoreResponse;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@SpringBootTest
class AppStoreDataFetcherTest {

    private static final String APP_STORE_BASE_URL = "https://amp-api.apps.apple.com/v1/catalog/";
    private static final String TEST_DATA_DIRECTORY =
            "src/mobile-app-store-spy/src/test/java/se/tink/backend/mobile_app_store_spy/fetcher_appstore_fetcher/resources/";

    private AppStoreDataFetcher appStoreDataFetcher;
    private AppStoreDataFetcherConfig fetcherConfig;
    private Config config;
    private RestTemplate restTemplateMock;
    private ProviderAppPatternDataStorage dataStorageMock;
    private ResponseEntity<AppStoreResponse> responseMock;

    @BeforeEach
    void setUp() {
        restTemplateMock = mock(RestTemplate.class);
        dataStorageMock = mock(ProviderAppPatternDataStorage.class);
        responseMock = mock(ResponseEntity.class);
        fetcherConfig = new AppStoreDataFetcherConfig(APP_STORE_BASE_URL);
        appStoreDataFetcher =
                new AppStoreDataFetcher(restTemplateMock, dataStorageMock, fetcherConfig);
        config = new Config();
    }

    @Test
    void shouldFetchResponseUsingCorrectParameters() throws IOException {
        // given
        AppStoreResponse appResponse = deserializeFromFile("appStoreResponse.json");
        when(responseMock.getBody()).thenReturn(appResponse);
        when(restTemplateMock.exchange(
                any(String.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(AppStoreResponse.class)))
                .thenReturn(responseMock);

        when(dataStorageMock.getMarketCodeForAppId("SAMPLE_APP_ID")).thenReturn("SAMPLE_MARKET");

        // when
        AppStoreResponse appStoreDataFetcherResponse =
                appStoreDataFetcher.fetchAppDataById("SAMPLE_APP_ID");

        // then
        assertThat(appStoreDataFetcherResponse).usingRecursiveComparison().isEqualTo(appResponse);

        DefaultUriBuilderFactory builderFactory =
                new DefaultUriBuilderFactory(fetcherConfig.getAppleAppStoreBaseUrl());
        UriBuilder uriBuilder = builderFactory.builder();
        String expectedUrl =
                uriBuilder
                        .pathSegment("SAMPLE_MARKET")
                        .pathSegment(AppStoreDataFetcherConfig.APPS_PATH)
                        .pathSegment("SAMPLE_APP_ID")
                        .queryParam("platform", "iphone")
                        .queryParam("extend", "versionHistory")
                        .build()
                        .toString();

        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        expectedHeaders.setBearerAuth(AppStoreDataFetcherConfig.BEARER_TOKEN);
        HttpEntity<String> expectedEntity = new HttpEntity<>(expectedHeaders);

        verify(restTemplateMock)
                .exchange(expectedUrl, HttpMethod.GET, expectedEntity, AppStoreResponse.class);
    }

    private AppStoreResponse deserializeFromFile(String fileName) throws IOException {
        File file = new File(TEST_DATA_DIRECTORY + fileName);

        return config.objectMapper().readValue(file, AppStoreResponse.class);
    }
}
