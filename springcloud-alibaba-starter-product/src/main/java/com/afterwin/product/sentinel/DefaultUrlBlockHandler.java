package com.afterwin.product.sentinel;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.fastjson.JSON;
import feign.form.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: sentinel容错处理
 * @author: zhengchj
 * @create: 2020-05-01 16:17
 **/
@Component
@Slf4j
public class DefaultUrlBlockHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseEntity<String> responseEntity = null;
        if (e instanceof FlowException) {
            responseEntity = new ResponseEntity<String>("限流", HttpStatus.SERVICE_UNAVAILABLE);
        } else if (e instanceof DegradeException) {
            responseEntity = new ResponseEntity<String>("降级", HttpStatus.SERVICE_UNAVAILABLE);
        } else if (e instanceof AuthorityException) {
            responseEntity = new ResponseEntity<String>("未授权", HttpStatus.SERVICE_UNAVAILABLE);
        } else if (e instanceof ParamFlowException) {
            responseEntity = new ResponseEntity<String>("热点限流", HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            responseEntity = new ResponseEntity<String>("系统不可用", HttpStatus.SERVICE_UNAVAILABLE);
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(responseEntity));
    }
}
