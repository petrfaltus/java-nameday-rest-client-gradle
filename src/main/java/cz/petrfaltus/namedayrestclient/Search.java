package cz.petrfaltus.namedayrestclient;

public class Search {
    private static String lastError = null;

    public static String run(String query) {
        lastError = null;

        String output = Web.request(query);
        if (output == null) {
            lastError = "The web request to the REST service failed";
            return null;
        }

        return output;
    }

    public static String getLastError() {
        return lastError;
    }
}
