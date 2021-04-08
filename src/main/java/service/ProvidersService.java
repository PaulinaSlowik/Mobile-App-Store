package service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.TinkApiDataFetcher;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.data.Provider;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.dto.SimpleProvider;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@Service
@RequiredArgsConstructor
public class ProvidersService {

    private final TinkApiDataFetcher tinkApiDataFetcher;
    private final ProviderAppPatternDataStorage providerAppPatternDataStorage;

    public List<SimpleProvider> getREProviders(String market) {
        List<Provider> providers =
                tinkApiDataFetcher.fetchProvidersForSpecificMarket(market).getProviders();

        return providers.stream()
                .filter(provider -> "OTHER".equals(provider.getAccessType()))
                .map(provider -> new SimpleProvider(provider.getName()))
                .collect(Collectors.toList());
    }

    public List<SimpleProvider> getOBProviders(String market) {
        List<Provider> providers =
                tinkApiDataFetcher.fetchProvidersForSpecificMarket(market).getProviders();

        return providers.stream()
                .filter(provider -> !"OTHER".equals(provider.getAccessType()))
                .map(provider -> new SimpleProvider(provider.getName()))
                .collect(Collectors.toList());
    }

    public String getProviderName(String providerName) {
        return providerName;
    }

    public String getBackendVersion(String providerName) {
        return providerAppPatternDataStorage
                .getProviderNameWithTinkAppVersionMap()
                .get(providerName);
    }
}

