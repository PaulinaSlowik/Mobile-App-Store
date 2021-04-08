package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import se.tink.backend.mobile_app_store_spy.config.Config;
import se.tink.backend.mobile_app_store_spy.fetcher.appstore_fetcher.data.AppStoreResponse;

class Fixtures {
    private static Config config = new Config();

    static AppStoreResponse appStoreResponseForTheNewestVersion() throws JsonProcessingException {
        return config.objectMapper()
                .readValue(
                        "{\n"
                                + " \"data\": [\n"
                                + " {\n"
                                + " \"id\" : \"649492675\",\n"
                                + " \"attributes\" : {\n"
                                + " \"platformAttributes\" : {\n"
                                + " \"ios\" : {\n"
                                + " \"versionHistory\" : [ {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l'Identità Digitale PosteID abilitato a SPID direttamente in App, completando l'identificazione anche con la disposizione di un bonifico. Al termine della procedura, l’importo ti sarà restituito mediante bonifico sullo stesso conto che hai utilizzato\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2021-02-22\",\n"
                                + " \"iosAppVersion\" : \"2.18.1\",\n"
                                + " \"versionDisplay\" : \"2.18.1\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-27\",\n"
                                + " \"iosAppVersion\" : \"2.11.1\",\n"
                                + " \"versionDisplay\" : \"2.11.1\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"\\n- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-08\",\n"
                                + " \"iosAppVersion\" : \"2.10.6\",\n"
                                + " \"versionDisplay\" : \"2.10.6\"\n"
                                + " }]\n"
                                + " }\n"
                                + " },\n"
                                + " \"artistName\" : \"Poste Italiane Spa\"\n"
                                + " }\n"
                                + " }\n"
                                + "]\n"
                                + "}",
                        AppStoreResponse.class);
    }

    static AppStoreResponse appStoreResponseForNonExistingVersion() throws JsonProcessingException {
        return config.objectMapper()
                .readValue(
                        "{\n"
                                + " \"data\": [\n"
                                + " {\n"
                                + " \"id\" : \"649492675\",\n"
                                + " \"attributes\" : {\n"
                                + " \"platformAttributes\" : {\n"
                                + " \"ios\" : {\n"
                                + " \"versionHistory\" : [ {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l'Identità Digitale PosteID abilitato a SPID direttamente in App, completando l'identificazione anche con la disposizione di un bonifico. Al termine della procedura, l’importo ti sarà restituito mediante bonifico sullo stesso conto che hai utilizzato\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2021-02-22\",\n"
                                + " \"iosAppVersion\" : \"3.12.6\",\n"
                                + " \"versionDisplay\" : \"3.12.6\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-27\",\n"
                                + " \"iosAppVersion\" : \"3.11.1\",\n"
                                + " \"versionDisplay\" : \"3.11.1\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"\\n- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-08\",\n"
                                + " \"iosAppVersion\" : \"3.10.6\",\n"
                                + " \"versionDisplay\" : \"3.10.6\"\n"
                                + " }]\n"
                                + " }\n"
                                + " },\n"
                                + " \"artistName\" : \"Poste Italiane Spa\"\n"
                                + " }\n"
                                + " }\n"
                                + "]\n"
                                + "}",
                        AppStoreResponse.class);
    }

    static AppStoreResponse appStoreResponseForNonLatestVersion() throws JsonProcessingException {
        return config.objectMapper()
                .readValue(
                        "{\n"
                                + " \"data\": [\n"
                                + " {\n"
                                + " \"id\" : \"649492675\",\n"
                                + " \"attributes\" : {\n"
                                + " \"platformAttributes\" : {\n"
                                + " \"ios\" : {\n"
                                + " \"versionHistory\" : [ {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l'Identità Digitale PosteID abilitato a SPID direttamente in App, completando l'identificazione anche con la disposizione di un bonifico. Al termine della procedura, l’importo ti sarà restituito mediante bonifico sullo stesso conto che hai utilizzato\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2021-02-22\",\n"
                                + " \"iosAppVersion\" : \"3.10.6\",\n"
                                + " \"versionDisplay\" : \"3.10.6\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-27\",\n"
                                + " \"iosAppVersion\" : \"2.18.1\",\n"
                                + " \"versionDisplay\" : \"2.18.1\"\n"
                                + " }, {\n"
                                + " \"releaseNotes\" : \"\\n- Possibilità di richiedere l’Identità Digitale PosteID abilitato a SPID interamente in app e con un processo semplificato in caso di possesso della CIE e del relativo PIN\\n- Bug fixing\\n- Ottimizzazioni e miglioramenti sulla base dei vostri suggerimenti\",\n"
                                + " \"releaseDate\" : \"2020-10-08\",\n"
                                + " \"iosAppVersion\" : \"1.18.1\",\n"
                                + " \"versionDisplay\" : \"1.18.1\"\n"
                                + " }]\n"
                                + " }\n"
                                + " },\n"
                                + " \"artistName\" : \"Poste Italiane Spa\"\n"
                                + " }\n"
                                + " }\n"
                                + "]\n"
                                + "}",
                        AppStoreResponse.class);
    }
}
