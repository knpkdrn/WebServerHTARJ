package org.example.tables.models;

import org.example.tables.services.RequestHistoryService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RequestHistory {

    private String requestType;
    private String requestTime;
    private String result;
    private String requestKey;

    public String getRequestKey() {
        return requestKey;
    }
    public String getRequestType() {
        return requestType;
    }
    public String getRequestTime() {
        return requestTime;
    }
    public String getResult() {
        return result;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public void setResult(String result) {
        this.result = result;
    }

    /** Creates a date and time that resembles the format of the DATETIME datatype of MySQL */
    public void setRequestTime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        requestTime = dtf.format(now);
    }

    public void uploadRequest() {
        RequestHistoryService rhs = new RequestHistoryService();
        rhs.save(this);
    }
}
