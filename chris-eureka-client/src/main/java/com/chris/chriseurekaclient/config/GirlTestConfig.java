package com.chris.chriseurekaclient.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: linfei
 * @date: 2019/05/19
 * @Description:
 */
@ConfigurationProperties("girl")
@Component
@RefreshScope
@Data
@ToString
public class GirlTestConfig {

    private String name;
    private String age;
}
