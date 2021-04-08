package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcher;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.AppEntity;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.VersionEntity;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.data.Provider;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@Service
@RequiredArgsConstructor
public class AppStoreService {

    private final AppStoreDataFetcher appStoreDataFetcher;
    private final ProvidersService providersService;
    private final ProviderAppPatternDataStorage providerAppPatternDataStorage;

    public ProviderAppVersionData getAppDetailsByProviderName(String providerName) {
        AppEntity appStoreData = getAppStoreDataForSpecificProviderName(providerName);
        Provider providerData =
                new Provider(providerName, providersService.getBackendVersion(providerName));

        return new ProviderAppVersionData(
                providerData.getName(),
                appStoreData.getAttributeEntity().getAppName(),
                appStoreData.getAttributeEntity().getUrl(),
                providerData.getBackendVersion(),
                getLatestAppVersion(appStoreData));
    }

    private String getLatestAppVersion(AppEntity appEntity) {
        List<VersionEntity> versionsHistory =
                appEntity
                        .getAttributeEntity()
                        .getPlatformAttributes()
                        .getIosAppDetails()
                        .getVersionEntity();
        return versionsHistory.stream()
                .max(
                        Comparator.comparing(
                                versionHistory -> getLocalDate(versionHistory.getReleaseDate())))
                .map(VersionEntity::getIOsAppVersion)
                .orElseThrow(() -> new IllegalStateException("Could not find latest app version"));
    }

    private LocalDate getLocalDate(String dateAsString) {
        return LocalDate.parse(dateAsString);
    }

    public AppEntity getAppStoreDataForSpecificProviderName(String providerName) {
        String appIdForSpecificProviderName =
                providerAppPatternDataStorage.getProviderNameWithAppIdMap().get(providerName);

        return appStoreDataFetcher.fetchAppDataById(appIdForSpecificProviderName).getApps().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find AppStore data"));
    }

    public List<AppEntity> getAppStoreDataForMultipleProviderNames(String[] providerNames) {
        List<AppEntity> appsList = new ArrayList<>();
        for (String providerName : providerNames) {
            appsList.add(getAppStoreDataForSpecificProviderName(providerName));
        }
        return appsList;
    }

    public List<ProviderAppVersionData> getProviderAppVersionDataForMultipleProviderNames(
            String[] providerNames) {
        List<ProviderAppVersionData> providerAppVersionDataList = new ArrayList<>();
        for (String providerName : providerNames) {
            providerAppVersionDataList.add(getAppDetailsByProviderName(providerName));
        }
        return providerAppVersionDataList;
    }

    public List<AppEntity> getAppStoreDataForAllProviders() {
        List<AppEntity> appsList = new ArrayList<>();
        for (String providerName :
                providerAppPatternDataStorage.getProviderNameWithPatternProviderMap().keySet()) {
            appsList.add(getAppStoreDataForSpecificProviderName(providerName));
        }
        return appsList;
    }

    public List<ProviderAppVersionData> getProviderAppVersionDataForAllProviders() {
        List<ProviderAppVersionData> appsList = new ArrayList<>();
        for (String providerName :
                providerAppPatternDataStorage.getProviderNameWithPatternProviderMap().keySet()) {
            appsList.add(getAppDetailsByProviderName(providerName));
        }
        return appsList;
    }
}

