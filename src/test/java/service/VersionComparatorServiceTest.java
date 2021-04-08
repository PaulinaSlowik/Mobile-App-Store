package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static se.tink.backend.mobile_app_store_spy.service.Fixtures.appStoreResponseForNonExistingVersion;
import static se.tink.backend.mobile_app_store_spy.service.Fixtures.appStoreResponseForNonLatestVersion;
import static se.tink.backend.mobile_app_store_spy.service.Fixtures.appStoreResponseForTheNewestVersion;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcher;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@SpringBootTest
class VersionsComparatorServiceTest {

    private VersionsComparatorService objectUnderTest;
    private AppStoreDataFetcher appStoreDataFetcherMock;

    @BeforeEach
    void setUp() {
        appStoreDataFetcherMock = Mockito.mock(AppStoreDataFetcher.class);
        objectUnderTest =
                new VersionsComparatorService(
                        appStoreDataFetcherMock, new ProviderAppPatternDataStorage());
    }

    @Test
    void shouldReturnMessageForTheNewestVersion() throws JsonProcessingException {
        // given
        String providerName = "it-isp-password";

        // when
        when(appStoreDataFetcherMock.fetchAppDataById(anyString()))
                .thenReturn(appStoreResponseForTheNewestVersion());

        VersionsComparatorService.ComparatorResponse response =
                objectUnderTest.compareVersionsForProvider(providerName);

        // then
        String expectedMessage = "Versions of the app match.";
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void shouldReturnMessageForNonExistingVersion() throws JsonProcessingException {
        // given
        String providerName = "it-isp-password";

        // when
        when(appStoreDataFetcherMock.fetchAppDataById(anyString()))
                .thenReturn(appStoreResponseForNonExistingVersion());
        VersionsComparatorService.ComparatorResponse response =
                objectUnderTest.compareVersionsForProvider(providerName);

        // then
        String expectedMessage =
                String.format(
                        "Versions of the application do not match and we are at least %d versions behind the newest version available in AppStore, but there is no possibility to provide the exact difference.",
                        3);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void shouldReturnMessageForNonLatestVersion() throws JsonProcessingException {
        // given
        String providerName = "it-isp-password";

        // when
        when(appStoreDataFetcherMock.fetchAppDataById(anyString()))
                .thenReturn(appStoreResponseForNonLatestVersion());
        VersionsComparatorService.ComparatorResponse response =
                objectUnderTest.compareVersionsForProvider(providerName);

        // then
        String expectedMessage =
                String.format(
                        "Versions of the application do not match and we are %d versions behind the newest version available in AppStore",
                        1);
        assertThat(response.getMessage()).isEqualTo(expectedMessage);
    }
}
