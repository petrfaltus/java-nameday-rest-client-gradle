# Name day REST API
Application interface description
(c) Petr Faltus 2020-2021

---
## All supported countries request
Returns all country codes and names supported by the API.

-   **URL**
    <http://api.petrfaltus.net/name_day/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value | Type    |
        | --------        | ----- | ----    |
        | `method_number` | 1     | integer |

    -   **Optional**
        None

    -   **Example JSON Request**
        ```javascript
        {
          "method_number" : 1
        }
        ```

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value          | Type                |
        | --------       | -----          | ----                |
        | `error_code`   | 0              | integer             |
        | `error_string` | "ok"           | string              |
        | `data`         | *substructure* | list of *Data Item* |

    -   **Data Item Content**
        | Variable     | Value        | Type   |
        | --------     | -----        | ----   |
        | country code | country name | string |

    -   **Example JSON Reply**
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

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |

---
## Name or date request for the country
Returns name days for the specified country and requested name/date from the API.

-   **URL**
    <http://api.petrfaltus.net/name_day/json/1.0>

-   **Method**
    `POST`

-   **URL Params**
    None

-   **Form Data Params**
    None

-   **Raw Data Params**
    -   **Required**
        | Variable        | Value        | Type    |
        | --------        | -----        | ----    |
        | `method_number` | 2            | integer |
        | `query`         | name or date | string  |
        | `country`       | country code | string  |

    -   **Optional**
        | Variable          | Value  | Type    |
        | --------          | -----  | ----    |
        | `czech_sensitive` | 0 or 1 | integer |
        | `case_sensitive`  | 0 or 1 | integer |

    -   **Example JSON Request**
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

-   **Success Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable          | Value                          | Type            |
        | --------          | -----                          | ----            |
        | `error_code`      | 0                              | integer         |
        | `error_string`    | "ok"                           | string          |
        | `czech_sensitive` | 0 or 1 (used for this request) | integer         |
        | `case_sensitive`  | 0 or 1 (used for this request) | integer         |
        | `data`            | *substructure*                 | array of string |

    -   **Example JSON Reply**
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

-   **Error Response**
    -   **Code**
        200 OK

    -   **Content**
        | Variable       | Value       | Type    |
        | --------       | -----       | ----    |
        | `error_code`   | number <> 0 | integer |
        | `error_string` | message     | string  |
