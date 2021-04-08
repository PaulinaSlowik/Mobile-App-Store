package fetcher.providers_fetcher;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.data.ProvidersResponse;

@Disabled
@SpringBootTest
class ApiDataFetcherTest {

    @Autowired private TinkApiDataFetcher tinkApiDataFetcher;

    @Test
    void shouldReturnAnyProviders() {
        // when
        ProvidersResponse providersResponse =
                tinkApiDataFetcher.fetchProvidersForSpecificMarket("IT");

        // then
        Assertions.assertThat(providersResponse.getProviders()).isNotEmpty();
    }
}
