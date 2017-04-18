package lt.ws.mif;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Romas on 2/18/2017.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"lt.ws.mif"})
@EnableJpaRepositories(basePackages = {"lt.ws.mif"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}