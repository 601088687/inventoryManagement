package com.boot.template.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author yishuai
 * @date 2022-06-27 10:05
 * bootTemplate数据源加载
 */
@Configuration
@MapperScan(value = {"com.boot.template.mapper"}, sqlSessionTemplateRef = "bootTemplateSqlSessionTemplate")
public class BootTemplateDataSourceConfiguration {
    @Bean(name = "bootTemplateDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.bootemplate.druid")
    public DataSource bootTemplateDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }


    @Bean(name = "bootTemplateSqlSessionFactory")
    public SqlSessionFactory bootTemplateSqlSessionFactory(@Qualifier("bootTemplateDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //由于拆分了包结构，这里指定mapper
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "bootTemplateTransactionManager")
    @Primary
    public DataSourceTransactionManager bootTemplateTransactionManager(@Qualifier("bootTemplateDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "bootTemplateSqlSessionTemplate")
    public SqlSessionTemplate bootTemplateSqlSessionTemplate(@Qualifier("bootTemplateSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
