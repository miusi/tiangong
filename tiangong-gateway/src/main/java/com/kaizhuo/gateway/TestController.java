package com.kaizhuo.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: tiangong
 * @package: com.kaizhuo.gateway
 * @description: 测试
 * @author: miaochen
 * @create: 2020-02-14 00:06
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@RestController
public class TestController {

    @Value("${server.port}")
    private String port;

    /**
     * 测试调用请求
     * @param time
     * @return
     */
    @RequestMapping(value = "/get/{time}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> get(@PathVariable("time") long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(port + " get ok.", HttpStatus.OK);
    }

    /**
     * 发生熔断调用的请求
     * @return
     */
    @RequestMapping(value = "/fallback", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> fallback() {
        return new ResponseEntity<>("error.", HttpStatus.OK);
    }
}
