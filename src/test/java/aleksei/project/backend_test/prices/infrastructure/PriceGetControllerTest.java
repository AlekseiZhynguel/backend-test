package aleksei.project.backend_test.prices.infrastructure;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import aleksei.project.backend_test.prices.domain.PriceMother;
import aleksei.project.backend_test.prices.domain.PriceRepository;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceResponseDto;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceGetControllerTest {

  @LocalServerPort private int port;
  @MockBean private PriceRepository repository;

  private String uri;

  @PostConstruct
  public void init() {
    uri = "http://localhost:" + port + "/api/v1";
  }

  @Test
  void should_return_a_price_for_a_given_product() {
    var expected = PriceMother.random();
    when(repository.findPriceByApplicationDateAndProductIdAndBrandId(any(), any(), any()))
        .thenReturn(Optional.of(expected));
    var response =
        given()
            .param("applicationDate", "2020-06-14T10:00:00")
            .param("productId", 1)
            .param("brandId", 1)
            .when()
            .get(uri + "/prices")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(GetPriceResponseDto.class);

    assertThat(response).isNotNull();
  }
}
