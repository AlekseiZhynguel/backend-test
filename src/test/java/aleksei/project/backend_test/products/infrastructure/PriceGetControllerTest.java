package aleksei.project.backend_test.products.infrastructure;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import aleksei.project.backend_test.products.infrastructure.dto.GetPriceResponseDto;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceGetControllerTest {

  @LocalServerPort private int port;

  private String uri;

  @PostConstruct
  public void init() {
    uri = "http://localhost:" + port + "/api/v1";
  }

  @Test
  void should_return_a_price_for_a_given_product() {
    var expected = new GetPriceResponseDto(1, 1, "1", "2020-06-14-00.00.00", 30);

    var response =
        given()
            .param("applicationDate", "2020-06-14-00.00.00")
            .param("productId", 1)
            .param("brandId", 1)
            .when()
            .get(uri + "/prices")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(GetPriceResponseDto.class);

    assertThat(response).isEqualTo(expected);
  }
}
