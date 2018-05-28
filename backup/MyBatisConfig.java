package com.raowei.common.config;

import com.tx.common.servlet.Initialization;
import com.tx.orm.BaseDao;
import com.tx.orm.MySQLDialect;
import com.tx.orm.mybatis.MyBatisDaoImpl;
import com.tx.orm.mybatis.PagePluging;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * mybatis配置
 */
public class MyBatisConfig {
    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Value("${mybatis.mapper-locations}")
    private String mapperDir;
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperDir));
        PagePluging pluging = new PagePluging();
        Properties dialectClass = new Properties();
        dialectClass.put("dialectClass", MySQLDialect.class.getName());
        pluging.setProperties(dialectClass);
        bean.setPlugins(new Interceptor[]{pluging});
        return bean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("myBatisDao")
    public BaseDao  myBatisDao(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        MyBatisDaoImpl myBatisDao = new MyBatisDaoImpl();
        myBatisDao.setSqlSessionFactory(sqlSessionFactory);
        return myBatisDao;
    }

    @Bean
    public Initialization initialization () {
        return new Initialization();
    }


}
