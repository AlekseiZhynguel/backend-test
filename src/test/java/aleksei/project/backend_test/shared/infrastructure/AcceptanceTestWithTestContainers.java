package aleksei.project.backend_test.shared.infrastructure;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class AcceptanceTestWithTestContainers {
  protected static MySQLContainer<?> MYSQL;

  static {
    MYSQL =
        new MySQLContainer<>(DockerImageName.parse("mysql:oraclelinux8"))
            .withUsername("user")
            .withPassword("pass")
            .withInitScript("init-db.sql")
            .withDatabaseName("ecommerce");
  }

  @BeforeAll
  static void startContainer() {
    MYSQL.start();
  }

  @AfterAll
  static void stopContainer() {
    MYSQL.stop();
  }

  @DynamicPropertySource
  static void setDatasourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MYSQL::getJdbcUrl);
    registry.add("spring.datasource.username", MYSQL::getUsername);
    registry.add("spring.datasource.password", MYSQL::getPassword);
  }
}
