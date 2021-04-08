package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.tink.backend.mobile_app_store_spy.fetcher.providers_fetcher.data.Provider;
import se.tink.backend.mobile_app_store_spy.service.ProvidersService;

@RestController
@RequestMapping("/backend")
public class ProviderController {

    private final ProvidersService providersService;

    public ProviderController(ProvidersService providersService) {
        this.providersService = providersService;
    }

    @GetMapping("/app-details/{providerName}")
    public Provider getProviderData(@PathVariable String providerName) {
        return new Provider(
                providersService.getProviderName(providerName),
                providersService.getBackendVersion(providerName));
    }
}
