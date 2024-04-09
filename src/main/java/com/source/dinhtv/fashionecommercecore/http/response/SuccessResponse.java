package com.source.dinhtv.fashionecommercecore.http.response;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.RESPONSE_STATUS_SUCCESS;

public class SuccessResponse extends BaseResponse {
    public SuccessResponse() {
    }

    public SuccessResponse(Object data) {
        super(RESPONSE_STATUS_SUCCESS, "Success", data);
    }

    public SuccessResponse(String resultMessage, Object data) {
        super(RESPONSE_STATUS_SUCCESS,resultMessage, data);
    }
}
