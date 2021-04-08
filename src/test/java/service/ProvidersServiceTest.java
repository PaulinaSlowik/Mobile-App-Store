package service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.dto.SimpleProvider;

@Disabled
@SpringBootTest
class ProvidersServiceTest {

    @Autowired private ProvidersService providersService;

    @Test
    void shouldFetchReProviderForGivenMarket() {
        // given
        String market = "IT";

        // when
        List<SimpleProvider> reProviders = providersService.getREProviders(market);

        // then
        assertThat(reProviders).isNotEmpty();
    }
}

