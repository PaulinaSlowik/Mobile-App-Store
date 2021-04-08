package service;

import java.util.List;
import java.util.stream.IntStream;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.AppStoreDataFetcher;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.dto.VersionEntity;
import se.tink.backend.mobile_app_store_spy.storage.ProviderAppPatternDataStorage;

@Service
@RequiredArgsConstructor
public class VersionsComparatorService {

    private final AppStoreDataFetcher appStoreDataFetcher;
    private final ProviderAppPatternDataStorage providerAppPatternDataStorage;

    @Builder
    @Value
    public static class ComparatorResponse {
        String message;
        Integer versionsDifference;
        boolean versionsMatch;
    }

    public ComparatorResponse compareVersionsForProvider(String providerName) {
        if (providerAppPatternDataStorage.getProviderNameWithPatternProviderMap().get(providerName)
                == null) {
            return null;
        }
        List<VersionEntity> appStoreVersionsHistory = getAppStoreVersionsHistory(providerName);

        Integer versionsDifference =
                IntStream.range(0, appStoreVersionsHistory.size())
                        .filter(
                                i ->
                                        appStoreVersionsHistory
                                                .get(i)
                                                .getIOsAppVersion()
                                                .equals(getTinkVersion(providerName)))
                        .findFirst()
                        .orElse(appStoreVersionsHistory.size());

        if (versionsDifference.equals(0)) {
            return buildComparatorResponse("Versions of the app match.", versionsDifference, true);
        } else if (versionsDifference.equals(appStoreVersionsHistory.size())) {

            return buildComparatorResponse(
                    String.format(
                            "Versions of the application do not match and we are at least %d versions behind the newest version available in AppStore, but there is no possibility to provide the exact difference.",
                            versionsDifference),
                    versionsDifference,
                    false);
        } else {
            return buildComparatorResponse(
                    String.format(
                            "Versions of the application do not match and we are %d versions behind the newest version available in AppStore",
                            versionsDifference),
                    versionsDifference,
                    false);
        }
    }

    private List<VersionEntity> getAppStoreVersionsHistory(String providerName) {
        String appIdForSpecificProviderName =
                providerAppPatternDataStorage.getProviderNameWithAppIdMap().get(providerName);
        return appStoreDataFetcher.fetchAppDataById(appIdForSpecificProviderName).getApps().stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find AppStore data"))
                .getAttributeEntity()
                .getPlatformAttributes()
                .getIosAppDetails()
                .getVersionEntity();
    }

    private String getTinkVersion(String providerName) {
        return providerAppPatternDataStorage
                .getProviderNameWithPatternProviderMap()
                .get(providerName)
                .getTinkAppVersion();
    }

    private ComparatorResponse buildComparatorResponse(
            String message, Integer versionsDifference, boolean versionsMatch) {
        return ComparatorResponse.builder()
                .versionsMatch(versionsMatch)
                .message(message)
                .versionsDifference(versionsDifference)
                .build();
    }
}
