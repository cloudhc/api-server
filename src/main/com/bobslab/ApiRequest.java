package com.bobslab;

import com.google.gson.JsonObject;

/**
 * Created by bast on 2016-04-19.
 */
public interface ApiRequest {
    public void requestParamValidation() throws RequestParamException;

    public void service() throws ServiceException;

    public void executeService();

    public JsonObject getApiResult();
}
