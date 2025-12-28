package com.boot.template.controller;

import com.boot.template.aop.anntation.BootDoc;
import com.boot.template.aop.anntation.OrgExist;
import com.boot.template.common.constant.SystemConstants;
import com.boot.template.common.result.IResultable;
import com.boot.template.common.thread.ThreadLocalMap;
import com.boot.template.service.ProductInventoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yishuai
 * @date 2023-07-05 16:09
 **/
@RestController
@RequestMapping(value = "/section")
@BootDoc
public class SectionController {

    @Autowired
    private ProductInventoryService productInventoryService;


    @ApiOperation(value = "轮播图模块", notes = "轮播图模块", tags = "首页", response = IResultable.class)
    @PostMapping(value = "/homePage/carousel")
    @OrgExist
    public IResultable carousel() throws Exception {
        String orgCode = (String) ThreadLocalMap.get(SystemConstants.ORG_CODE_KEY);
        /*if(StringUtils.isNotBlank(batchId)) {
            return ResultWrapper.wrapObject(batchId, StatusCode.SUCC.getCode(), StatusCode.SUCC.getMsg());
        }else{
            throw new SystemInsideException(ErrorMsgConstants.DB_INSERT_ERROR);
        }*/
        return null;
    }
}
