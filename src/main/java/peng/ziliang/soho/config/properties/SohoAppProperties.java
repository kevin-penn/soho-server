package peng.ziliang.soho.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@Data
@ConfigurationProperties("sohoapp")
public class SohoAppProperties {

    @Data
    public static class Security {

        private Set<String> permitAntPartents;

    }
}
