package com.xavier.xos.server.service.impl;

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.ResultEnum;
import com.xavier.xos.api.domain.req.UploadRequest;
import com.xavier.xos.api.service.XosService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/31 13:52
 */
@Slf4j
@DubboService(version = "${dubbo.provider.version}")
public class XosServiceImpl implements XosService {
    @Override
    public Result<String> upload(UploadRequest uploadRequest) {
        Result<String> result = Result.get(ResultEnum.USER_PARAMETER_ERROR);
        return result;
    }

    @Override
    public Result<String> download(UploadRequest uploadRequest) {
        log.info("upload-{}", uploadRequest.getAccessKey());
        Result<String> result = Result.get(ResultEnum.SUCCESS);
        return result;
    }
}
