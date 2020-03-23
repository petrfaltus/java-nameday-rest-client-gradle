**Get supported countries**
----
Returns json data about countries supported by the name days API.

* **URL**
http://api.petrfaltus.net/name_day/json/1.0

* **Method:**
`POST`

*  **URL Params**
None

* **Data Params**
  * **Required:**
  `method_number : 1`

  * **Optional:**
  None

* **Success Response:**
  * **Code:** 200 OK
  **Content:** `{error_code : 0, error_string : "ok", data : {"cz" : "Česká republika"}}`

* **Error Response:**
  * **Code:** 200 OK
    **Content:** `{error_code : integer, error_string : "message"}` *(integer != 0)*

* **Example JSON Request Data:**
  ```javascript
  {
    "method_number": 1
  }
  ```

**Name or date request for the country**
----
Returns json data about name days for the specified country and requested name or date.

* **URL**
http://api.petrfaltus.net/name_day/json/1.0

* **Method:**
`POST`

*  **URL Params**
None

* **Data Params**
  * **Required:**
  `method_number : 2`
  `query : [name or date string]`
  `country : [country code string]`

  * **Optional:**
  `czech_sensitive : [0|1]`
  `case_sensitive : [0|1]`

* **Success Response:**
  * **Code:** 200 OK
  **Content:** `{error_code : 0, error_string : "ok", czech_sensitive : 0, case_sensitive : 0, data : ["Marie má svátek 12.9."]}`

* **Error Response:**
  * **Code:** 200 OK
    **Content:** `{error_code : integer, error_string : "message"}}` *(integer != 0)*

* **Example JSON Request Data:**
  ```javascript
  {
    "method_number": 2,
    "query": "marie",
    "country": "cz"
  }
  ```
  OR
  ```javascript
  {
    "method_number": 2,
    "query": "31.12.",
    "country": "cz"
  }
  ```
  OR
  ```javascript
  {
    "method_number": 2,
    "query": "Štěpán",
    "country": "cz",
    "czech_sensitive: 1,
    "case_sensitive: 1
  }
  ```
