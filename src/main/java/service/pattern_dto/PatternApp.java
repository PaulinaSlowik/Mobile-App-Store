package service.pattern_dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PatternApp {
    @NonNull private String appId;

    private String bankName;

    @NonNull private String appleAppVersion;
}
