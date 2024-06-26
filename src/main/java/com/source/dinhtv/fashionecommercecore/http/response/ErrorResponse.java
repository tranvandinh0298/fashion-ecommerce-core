package com.source.dinhtv.fashionecommercecore.http.response;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.RESPONSE_STATUS_FAIL;

public class ErrorResponse extends BaseResponse {
    private String resultSubCode;

    public ErrorResponse() {
        super(RESPONSE_STATUS_FAIL);
    }

    public ErrorResponse(String resultSubCode) {
        super(RESPONSE_STATUS_FAIL);
        this.resultSubCode = resultSubCode;
    }

    public ErrorResponse(String resultSubCode, String resultMessage, Object data) {
        super(RESPONSE_STATUS_FAIL, resultMessage, data);
        this.resultSubCode = resultSubCode;
    }


}
