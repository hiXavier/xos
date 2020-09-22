package com.xavier.xos.server.service.impl;

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.ResultEnum;
import com.xavier.xos.api.domain.req.UploadRequest;
import com.xavier.xos.api.service.XosUploadService;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/8 15:41
 */
@Slf4j
@DubboService(version = "${dubbo.provider.version}")
public class XosUploadServiceImpl implements XosUploadService {

    @Override
    public Result<String> upload(UploadRequest uploadRequest) {
        Result<String> result = Result.get(ResultEnum.SUCCESS);
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient(uploadRequest.getEndPoint(), uploadRequest.getAccessKey(), uploadRequest.getSecretKey());

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(uploadRequest.getBucket());
            if (isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket(uploadRequest.getBucket());
            }

            // 使用putObject上传一个文件到存储桶中。
            minioClient.putObject(uploadRequest.getBucket(), uploadRequest.getFileName(), uploadRequest.getFileName());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return result;
    }
}
