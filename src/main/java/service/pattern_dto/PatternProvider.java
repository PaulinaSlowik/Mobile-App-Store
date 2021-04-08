package service.pattern_dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatternProvider {
    private String providerName;
    private String market;
    private String tinkAppVersion;

    public String getAccessType() {
        if (providerName.contains("-ob") || providerName.contains("-oauth2")) {
            return "ob";
        } else {
            return "re";
        }
    }
}
