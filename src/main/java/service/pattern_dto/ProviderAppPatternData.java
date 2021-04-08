package service.pattern_dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProviderAppPatternData {

    @NonNull private final PatternProvider patternProvider;

    @NonNull private final PatternApp patternApp;
}
