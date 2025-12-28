package com.boot.template.service;

import com.boot.template.mapper.ProductInventoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yishuai
 * @date 2023-07-05 16:10
 **/
@Service
@Slf4j
public class ProductInventoryService {
    @Resource
    private ProductInventoryMapper productInventoryMapper;



}
