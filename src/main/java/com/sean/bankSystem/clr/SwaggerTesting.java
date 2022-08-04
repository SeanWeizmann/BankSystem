package com.sean.bankSystem.clr;

import com.sean.bankSystem.config.MRS;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class SwaggerTesting implements CommandLineRunner {

    private final MRS myRestTemplate;

    @Override
    public void run(String... args) throws Exception {

    }
}
