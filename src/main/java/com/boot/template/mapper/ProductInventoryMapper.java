package com.boot.template.mapper;

import com.boot.template.pojo.entity.ProductInventoryEntity;
import com.boot.template.pojo.entity.ProductInventoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductInventoryMapper {
    long countByExample(ProductInventoryExample example);

    int deleteByExample(ProductInventoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductInventoryEntity record);

    int insertSelective(ProductInventoryEntity record);

    List<ProductInventoryEntity> selectByExample(ProductInventoryExample example);

    ProductInventoryEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductInventoryEntity record, @Param("example") ProductInventoryExample example);

    int updateByExample(@Param("record") ProductInventoryEntity record, @Param("example") ProductInventoryExample example);

    int updateByPrimaryKeySelective(ProductInventoryEntity record);

    int updateByPrimaryKey(ProductInventoryEntity record);
}