package tests.base;

import io.restassured.http.ContentType;

public interface Properties {
    String APP_BASE_URL = "https://dummyjson.com/";
    String APP_BASE_PATH = "";
    ContentType APP_CONTENT_TYPE =  ContentType.JSON;
    Long MAX_TIME_OUT = 6000L;
}
