package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
//@PropertySource({"classpath:db.properties"})
public class DatabaseConfig implements TransactionManagementConfigurer {

    @Autowired
    ApplicationContext applicationContext;

//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.url}")
//    private String url;
//
//    @Value("${spring.datasource.username}")
//    private String userName;
//
//    @Value("${spring.datasource.password}")
//    private String password;

    // Apache DBCP 커넥션 풀 설정
    @Bean
    public DataSource dataSource()  {
        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://211.227.85.209:3306/DMS04?characterEncoding=UTF-8");
        dataSource.setUsername("dms02user01");
        dataSource.setPassword("devel!");
        dataSource.setDefaultAutoCommit(false);
        dataSource.setInitialSize(2);
        dataSource.setMaxTotal(10);
//        System.out.println(dataSource);
        return dataSource;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setMapperLocations(dataSource());
        sessionFactory.setTypeAliasesPackage("action"); // 도메인 클래스가 위치한 패키지
//        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*Mapper.xml"));
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

//        System.out.println(sessionFactory);
        return sessionFactory.getObject();
    }
}
