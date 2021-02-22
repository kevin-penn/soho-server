package peng.ziliang.soho.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import peng.ziliang.soho.config.properties.SohoAppProperties;

@Configuration
@MapperScan("peng.ziliang.soho.repository")
@EnableConfigurationProperties(SohoAppProperties.class)
public class WebConfig implements WebMvcConfigurer {
}
