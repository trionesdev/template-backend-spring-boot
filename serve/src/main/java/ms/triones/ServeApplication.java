package ms.triones;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan(value = {"com.ms.core.modules.*.dao.mapper"})
//@EnableFeignClients
public class ServeApplication {
    public static void main(String[] args){
        log.info("--app start--");
        SpringApplication.run(ServeApplication.class, args);
        log.info("--app end--");
    }
}
