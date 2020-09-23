package com.xavier.xos.web.controller;


import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.ResultEnum;
import com.xavier.xos.api.domain.req.UploadRequest;
import com.xavier.xos.req.UserReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/22 14:20
 */
@Api(tags = "你好")
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam("name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

    @ApiOperation(value = "向客人问好Json")
    @PostMapping("/say")
    public ResponseEntity<String> sayHeyEveryBody(UploadRequest uploadRequest) {
        return ResponseEntity.ok("Hi:" + uploadRequest.getAccessKey());
    }

    @ApiOperation(value = "参数校验")
    @PostMapping("/validate")
    public Result<String> validateParam(@RequestBody @Validated UserReq userReq, BindingResult bindingResult) {
        Result<String> result = Result.get(ResultEnum.SUCCESS);
        if (bindingResult.hasErrors()) {
            result.setMessage(bindingResult.getFieldError().getDefaultMessage());
        }
        return result;
    }
}

