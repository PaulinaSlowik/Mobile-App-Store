package controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.tink.backend.mobile_app_store_spy.service.AppStoreService;
import se.tink.backend.mobile_app_store_spy.service.ProviderAppVersionData;

@RestController
@RequestMapping("/appstore")
public class AppStoreController {

    private final AppStoreService appStoreService;

    public AppStoreController(AppStoreService appStoreService) {
        this.appStoreService = appStoreService;
    }

    @GetMapping("/providers/{providerNames}")
    public List<ProviderAppVersionData> getAppDetailsForMultipleProviders(
            @PathVariable String[] providerNames) {
        return appStoreService.getProviderAppVersionDataForMultipleProviderNames(providerNames);
    }

    @GetMapping("/providers")
    public List<ProviderAppVersionData> getAppDetailsForAllProviders() {
        return appStoreService.getProviderAppVersionDataForAllProviders();
    }
}
