package com.example.whatTravel.Models;

/**
 * Created by jiecongwang on 1/25/15.
 */
public enum Status {
    OK("OK"),
    ZERO_RESULTS("ZERO_RESULT"),
    OVER_QUERY_LIMIT("OVER_QUERY_LIMIT"),
    REQUEST_DENIED("REQUEST_DENIED"),
    INVALID_REQUEST("INVALID_REQUEST"),
    SUCCESS("200"),
    NOTFOUND("404");


    private String status;
    Status(String status) {
        this.status =status;
    }


}
