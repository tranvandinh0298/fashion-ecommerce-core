package com.source.dinhtv.fashionecommercecore.http.response;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.RESPONSE_STATUS_PENDING;

public class PendingResponse extends BaseResponse{
    public PendingResponse() {
        super(RESPONSE_STATUS_PENDING);
    }

    public PendingResponse(Object data) {
        super(RESPONSE_STATUS_PENDING, "Unknow exception", data);
    }

    public PendingResponse(String resultMessage, Object data) {
        super(RESPONSE_STATUS_PENDING,resultMessage, data);
    }
}
