package club.kwcoder.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@ComponentScan("club.kwcoder")
@MapperScan({"club.kwcoder.vote.mapper.generate", "club.kwcoder.vote.mapper.custom"})
public class UserApplication {

    private static final Logger LOG = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserApplication.class);

        Environment env = app.run(args).getEnvironment();

        LOG.info("启动成功！！！");
        LOG.info("UserApplication地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));

    }

}

