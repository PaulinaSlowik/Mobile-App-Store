package fetcher.providers_fetcher;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.data.ProvidersResponse;

@Component
@RequiredArgsConstructor
public class ApiDataFetcher {

    private static final String PROVIDERS_PATH = "providers";
    private static final String PARAMS =
            "?excludeNonTestProviders=false&includeTestProviders=false";

    @Value("${tink.api.base.url}")
    private String tinkApiBaseUrl;

    private final RestTemplate restTemplate;

    public ProvidersResponse fetchProvidersForSpecificMarket(String market) {
        String providerUrl = buildProviderUrl(market);
        return restTemplate.getForObject(providerUrl, ProvidersResponse.class);
    }

    private String buildProviderUrl(String market) {
        return tinkApiBaseUrl + PROVIDERS_PATH + "/" + market + PARAMS;
    }
}
