# Name day REST API
Application interface description
(c) Petr Faltus 2020

**Get supported countries**
----
Returns json data about country codes and countries supported by the API.

* **URL**
  http://api.petrfaltus.net/name_day/json/1.0

* **Method:**
  `POST`

* **URL Params**
  None

* **Data Params**
  * **Required:**
    `method_number : 1`

  * **Optional:**
    None

  * **Example JSON Request Data:**
    ```javascript
    {
      "method_number" : 1
    }
    ```

* **Success Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : 0,
      "error_string" : "ok",
      "data" : {
                 "cz" : "Česká republika",
                 "sk" : "Slovensko"
               }
    }
    ```

* **Error Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : integer,
      "error_string" : "message"
    }
    ```
    *(`error_code` != 0)*

**Name or date request for the country**
----
Returns json data about name days for the specified country and requested name or date.

* **URL**
  http://api.petrfaltus.net/name_day/json/1.0

* **Method:**
  `POST`

* **URL Params**
  None

* **Data Params**
  * **Required:**
    `method_number : 2`
    `query : [name or date string]`
    `country : [country code string]`

  * **Optional:**
    `czech_sensitive : [0|1]`
    `case_sensitive : [0|1]`

  * **Example JSON Request Data:**
    ```javascript
    {
      "method_number" : 2,
      "query" : "petr",
      "country" : "cz"
    }
    ```
    or
    ```javascript
    {
      "method_number" : 2,
      "query" : "31.12.",
      "country" : "cz"
    }
    ```
    or
    ```javascript
    {
      "method_number" : 2,
      "query" : "Štěpán",
      "country" : "cz",
      "czech_sensitive" : 1,
      "case_sensitive" : 1
    }
    ```

* **Success Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : 0,
      "error_string" : "ok",
      "czech_sensitive" : 0,
      "case_sensitive" : 0,
      "data" : [
                 "Petr a Pavel mají svátek 29.6.",
                 "Petr má svátek 22.2."
               ]
    }
    ```
    *(with `czech_sensitive` and `case_sensitive` the values used for this request)*

* **Error Response:**
  * **Code:** 200 OK
    **Content:**
    ```javascript
    {
      "error_code" : integer,
      "error_string" : "message"
    }
    ```
    *(`error_code` != 0)*
