package com.ldm;

import com.ldm.netty.DirectMemoryReporter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ldm.dao")
public class SocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketApplication.class, args);
        DirectMemoryReporter reporter = DirectMemoryReporter.getInstance();
        reporter.setDataUnit(DirectMemoryReporter.DataUnit.BYTE);
        reporter.startReport();
    }

}
