package storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;
import se.tink.backend.mobile_app_store_spy.service.pattern_dto.PatternApp;
import se.tink.backend.mobile_app_store_spy.service.pattern_dto.PatternProvider;
import se.tink.backend.mobile_app_store_spy.service.pattern_dto.ProviderAppPatternData;

@Component
public class ProviderAppPatternDataStorage {

    public ProviderAppPatternDataStorage() {
        init();
    }

    @Getter
    private final List<ProviderAppPatternData> providerAppPatternDataList = new ArrayList<>();

    @Getter private final Map<String, String> appIdWithMarketCodeMap = new HashMap<>();
    @Getter private final Map<String, String> providerNameWithAppIdMap = new HashMap<>();
    @Getter private final List<PatternProvider> patternProvidersDataList = new ArrayList<>();
    @Getter private final List<PatternApp> patternAppsDataList = new ArrayList<>();
    @Getter private final Map<String, String> providerNameWithTinkAppVersionMap = new HashMap<>();

    @Getter
    private final Map<String, PatternProvider> providerNameWithPatternProviderMap = new HashMap<>();

    @Getter private final Map<String, PatternApp> providerNameWithPatternAppMap = new HashMap<>();

    @Getter
    private final Map<String, PatternProvider> appIdWithPatternProviderMap = new HashMap<>();

    public String getMarketCodeForAppId(String appId) {
        return appIdWithPatternProviderMap.get(appId).getMarket();
    }

    private void init() {
        PatternProvider ispProvider = new PatternProvider("it-isp-password", "it", "2.18.1");
        PatternProvider n26Provider = new PatternProvider("it-n26-password", "it", "3.58");

        PatternApp ispApp = new PatternApp("649492675", "2.18.1");
        PatternApp n26App = new PatternApp("1119225763", "3.58");

        providerNameWithPatternProviderMap.put(ispProvider.getProviderName(), ispProvider);
        providerNameWithPatternProviderMap.put(n26Provider.getProviderName(), n26Provider);

        providerNameWithPatternAppMap.put(ispProvider.getProviderName(), ispApp);
        providerNameWithPatternAppMap.put(n26Provider.getProviderName(), n26App);

        appIdWithPatternProviderMap.put(ispApp.getAppId(), ispProvider);
        appIdWithPatternProviderMap.put(n26App.getAppId(), n26Provider);

        providerNameWithAppIdMap.put(ispProvider.getProviderName(), ispApp.getAppId());
        providerNameWithAppIdMap.put(n26Provider.getProviderName(), n26App.getAppId());

        providerNameWithTinkAppVersionMap.put(
                ispProvider.getProviderName(), ispProvider.getTinkAppVersion());
        providerNameWithTinkAppVersionMap.put(
                n26Provider.getProviderName(), n26Provider.getTinkAppVersion());
    }
}
