package cz.petrfaltus.namedayrestclient;

import java.util.Map;
import java.util.Set;

public class Search {
    private static final String WEB_REQUEST_FAILED = "The web request to the REST service failed";

    private static String lastError = null;
    private static Set<Map.Entry<String, String>> countriesEntrySet = null; // all countries supported by the service

    public static String run(String query) {
        lastError = null; // await no errors

        if (countriesEntrySet == null) {
            // JSON REST request for all countries supported by the service
            String requestJsonCountries = Json.codeQueryCountries();
            String replyJsonCountries = Web.request(requestJsonCountries);
            if (replyJsonCountries == null) {
                lastError = WEB_REQUEST_FAILED;
                return null;
            }

            // decode countries
            Map<String, String> countries = Json.decodeResultCountries(replyJsonCountries);
            if (countries == null) {
                lastError = WEB_REQUEST_FAILED;
                return null;
            }

            // store countries for all next searchings
            countriesEntrySet = countries.entrySet();
        }

        String result = Const.EMPTY_STRING; // default value

        for (Map.Entry<String, String> oneCountryEntry: countriesEntrySet) {
            // JSON REST request for one country
            String countryCode = oneCountryEntry.getKey();
            String requestJsonOneCountry = Json.codeQueryOneCountry(query, countryCode);

            String replyJsonOneCountry = Web.request(requestJsonOneCountry);
            if (replyJsonOneCountry == null) {
                lastError = WEB_REQUEST_FAILED;
                return null;
            }

            // decode one country and add to the result string
            String countryName = oneCountryEntry.getValue();
            result += Json.decodeResultOneCountry(replyJsonOneCountry, countryName);
        }

        return result;
    }

    public static String getLastError() {
        return lastError;
    }
}
