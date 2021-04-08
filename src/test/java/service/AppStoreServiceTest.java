package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcher;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.data.AppStoreResponse;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.AppEntity;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.AttributeEntity;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.IosAppDetails;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.PlatformAttributes;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.VersionEntity;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

class AppStoreServiceTest {

    private AppStoreDataFetcher appStoreDataFetcher;
    private ProvidersService providersService;
    private ProviderAppPatternDataStorage providerAppPatternDataStorage;

    @BeforeEach
    void beforeEach() {
        appStoreDataFetcher = mock(AppStoreDataFetcher.class);
        providersService = mock(ProvidersService.class);
        providerAppPatternDataStorage = mock(ProviderAppPatternDataStorage.class);
    }

    private static final String providerName = "it-n26-password";
    private static final String appStoreId = "1119225763";

    @Test
    void shouldReturnMatchingAppEntity() {
        // given
        Map<String, String> providerNameWithAppIdMap = ImmutableMap.of(providerName, appStoreId);
        when(providerAppPatternDataStorage.getProviderNameWithAppIdMap())
                .thenReturn(providerNameWithAppIdMap);
        AppStoreResponse response = getAppStoreResponse(appStoreId);
        when(appStoreDataFetcher.fetchAppDataById(appStoreId)).thenReturn(response);
        AppStoreService service =
                new AppStoreService(
                        appStoreDataFetcher, providersService, providerAppPatternDataStorage);

        // when
        AppEntity result = service.getAppStoreDataForSpecificProviderName(providerName);

        // then
        assertThat(result.getAppId()).isEqualTo(appStoreId);
    }

    @Test
    void shouldThrowExceptionIfNoAppEntitiesFetched() {
        // given
        Map<String, String> providerNameWithAppIdMap = ImmutableMap.of(providerName, appStoreId);
        when(providerAppPatternDataStorage.getProviderNameWithAppIdMap())
                .thenReturn(providerNameWithAppIdMap);
        AppStoreResponse response = getEmptyAppStoreResponse();
        when(appStoreDataFetcher.fetchAppDataById(appStoreId)).thenReturn(response);

        AppStoreService service =
                new AppStoreService(
                        appStoreDataFetcher, providersService, providerAppPatternDataStorage);

        // when
        Throwable thrown =
                catchThrowable(() -> service.getAppStoreDataForSpecificProviderName(providerName));

        // then
        assertThat(thrown)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Could not find AppStore data");
    }

    @Test
    void shouldReturnProperBackendVersion() {
        // given
        Map<String, String> providerNameWithAppIdMap = ImmutableMap.of(providerName, appStoreId);
        when(providerAppPatternDataStorage.getProviderNameWithAppIdMap())
                .thenReturn(providerNameWithAppIdMap);
        AppStoreResponse response = getAppStoreResponse(appStoreId);
        when(appStoreDataFetcher.fetchAppDataById(appStoreId)).thenReturn(response);
        when(providersService.getBackendVersion(providerName)).thenReturn("version1234");

        AppStoreService service =
                new AppStoreService(
                        appStoreDataFetcher, providersService, providerAppPatternDataStorage);

        // when
        ProviderAppVersionData result = service.getAppDetailsByProviderName(providerName);

        // then
        assertThat(result.getProviderName()).isEqualTo(providerName);
        assertThat(result.getBackendVersion()).isEqualTo("version1234");
    }

    private AppStoreResponse getAppStoreResponse(String appId) {
        AppStoreResponse response = new AppStoreResponse();
        AppEntity appEntity = new AppEntity();
        appEntity.setAppId(appId);
        AttributeEntity attributeEntity = new AttributeEntity();
        attributeEntity.setAppName("myApp");
        attributeEntity.setUrl("http://my.url");
        PlatformAttributes platformAttributes = new PlatformAttributes();
        IosAppDetails iosAppDetails = new IosAppDetails();
        VersionEntity versionEntity = new VersionEntity();
        versionEntity.setIOsAppVersion("currentIosVersion123");
        versionEntity.setReleaseDate("2020-09-21");
        iosAppDetails.setVersionEntity(Collections.singletonList(versionEntity));
        platformAttributes.setIosAppDetails(iosAppDetails);
        attributeEntity.setPlatformAttributes(platformAttributes);
        appEntity.setAttributeEntity(attributeEntity);
        response.setApps(Collections.singletonList(appEntity));
        return response;
    }

    private AppStoreResponse getEmptyAppStoreResponse() {
        AppStoreResponse response = new AppStoreResponse();
        response.setApps(Collections.emptyList());
        return response;
    }

    @Test
    void shouldGetCorrectNumberOfAppStoreDataForAllProviderNames() {
        // given
        AppStoreService service =
                new AppStoreService(
                        appStoreDataFetcher, providersService, providerAppPatternDataStorage);
        int numberOfProviders =
                providerAppPatternDataStorage
                        .getProviderNameWithPatternProviderMap()
                        .keySet()
                        .size();
        // when
        List<AppEntity> actualList = service.getAppStoreDataForAllProviders();
        // then
        assertThat(actualList.size()).isEqualTo(numberOfProviders);
    }
}
