package com.xavier.xos.consumer.controller;

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.ResultEnum;
import com.xavier.xos.api.domain.req.UploadRequest;
import com.xavier.xos.api.service.XosService;
import com.xavier.xos.api.service.XosUploadService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/31 13:59
 */
@RestController
public class ConsumerController {

    @DubboReference(version = "${dubbo.consumer.version}", check = true, interfaceClass = XosService.class)
    private XosService xosService;

    @DubboReference(version = "${dubbo.consumer.version}", check = true, interfaceClass = XosUploadService.class)
    private XosUploadService xosUploadService;

    @GetMapping(value = "/hello/{name}")
    public Result<String> hello(@PathVariable String name) {
        Result<String> result = xosService.download(UploadRequest.builder().accessKey("dfet").bucket("dgfdg").build());
        return result;
    }

    @PostMapping(value = "/upload")
    public Result<String> hello(MultipartFile multipartFile, UploadRequest uploadRequest) {
        Result<String> result = Result.get(ResultEnum.SUCCESS);
        // 用uuid作为文件名，防止生成的临时文件重复
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        final File file;
        try {
            file = File.createTempFile(String.valueOf(System.currentTimeMillis()), multipartFile.getOriginalFilename());
            // MultipartFile to File
            multipartFile.transferTo(file);
            uploadRequest.setFile(file);
            xosUploadService.upload(uploadRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
