package com.bobslab;

import java.util.logging.LogManager;

/**
 * Created by bast on 2016-04-19.
 */
public class ApiRequestTemplate implements ApiRequest {
    protected Logger logger;

    protected Map<String, String> reqData;

    protected JsonObject apiResult;

    public ApiRequestTemplate(Map<String, String> reqData) {
        this.logger = LogManager.getLogger(this.getClass());
        this.apiResult = new JsonObject();
        this.reqData = reqData;

        logger.info("request data :" + this.reqData);
    }
    public void executeService() {
        try {
            this.requestParamValidation();

            this.service();
        } catch (RequestParamException e) {
            logger.error(e);
            this.apiResult.addProperty("resultCode", "405");

        } catch(ServiceException e) {
            logger.error(e);
            this.apiResult.addProperty("resultCode", "501");
        }
    }

    public JsonObject getApiResult() {
        return this.apiResult;
    }
}
