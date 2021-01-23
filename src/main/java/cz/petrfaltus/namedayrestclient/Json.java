package cz.petrfaltus.namedayrestclient;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {
    private static final String METHOD_NUMBER = "method_number";
    private static final String QUERY = "query";
    private static final String COUNTRY = "country";

    private static final String ERROR_CODE = "error_code";
    private static final String ERROR_STRING = "error_string";
    private static final String DATA = "data";

    private static final long METHOD_COUNTRIES_NUMBER = 1;
    private static final long METHOD_ONE_COUNTRY_NUMBER = 2;

    private static String objToString(JSONObject obj) {
        String retString = Const.EMPTY_STRING;

        try {
            StringWriter out = new StringWriter();
            obj.writeJSONString(out);

            retString = out.toString();
        } catch (IOException ioex) {
            retString = null;
        }

        return retString;
    }

    public static String codeQueryCountries() {
        JSONObject obj = new JSONObject();
        obj.put(METHOD_NUMBER, METHOD_COUNTRIES_NUMBER);

        String retString = objToString(obj);

        return retString;
    }

    public static Map<String, String> decodeResultCountries(String resultJson) {
        Map<String, String> retMap = null;

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultJson);
            long errorCode = (long) jsonObject.get(ERROR_CODE);

            if (errorCode == 0) {
                retMap = (Map<String, String>) jsonObject.get(DATA);
            }
        } catch (ParseException pe) {
            retMap = null;
        } catch (NullPointerException npe) {
            retMap = null;
        }

        return retMap;
    }

    public static String codeQueryOneCountry(String query, String countryCode) {
        JSONObject obj = new JSONObject();
        obj.put(METHOD_NUMBER, METHOD_ONE_COUNTRY_NUMBER);
        obj.put(QUERY, query);
        obj.put(COUNTRY, countryCode);

        String retString = objToString(obj);

        return retString;
    }

    public static String decodeResultOneCountry(String resultJson, String countryName) {
        String retString = countryName + " ... ";

        try {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = (JSONObject) parser.parse(resultJson);
            long errorCode = (long) jsonObject.get(ERROR_CODE);
            String errorString = (String) jsonObject.get(ERROR_STRING);

            if (errorString != null) {
                retString += errorString;
                retString += System.lineSeparator();

                if (errorCode == 0) {
                    JSONArray data = (JSONArray) jsonObject.get(DATA);

                    Iterator<String> di = data.iterator();
                    while (di.hasNext()) {
                        retString += "- ";
                        retString += di.next();
                        retString += System.lineSeparator();
                    }
                }
            } else {
                retString += "nekonzistentní data";
                retString += System.lineSeparator();
            }
        } catch (ParseException pe) {
            retString += "chyba parsování";
            retString += System.lineSeparator();
        } catch (NullPointerException npe) {
            retString += "data nenalezena";
            retString += System.lineSeparator();
        }

        retString += System.lineSeparator();

        return retString;
    }
}
