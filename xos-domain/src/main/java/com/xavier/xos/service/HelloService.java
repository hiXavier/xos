package com.xavier.xos.service;

import com.xavier.xos.bo.Result;
import com.xavier.xos.req.UploadReq;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/31 11:56
 */
public interface HelloService {
    /**
     * test
     * @param uploadReq
     * @return
     */
    Result<String> getHello(UploadReq uploadReq);
}
