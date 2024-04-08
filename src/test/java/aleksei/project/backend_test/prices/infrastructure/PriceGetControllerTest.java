package aleksei.project.backend_test.prices.infrastructure;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import aleksei.project.backend_test.prices.domain.*;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceRequestDto;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceResponseDto;
import aleksei.project.backend_test.shared.infrastructure.AcceptanceTestWithTestContainers;
import jakarta.annotation.PostConstruct;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceGetControllerTest extends AcceptanceTestWithTestContainers {

  @LocalServerPort private int port;
  private String uri;

  private static Stream<Arguments> requestsToValidate() {
    return Stream.of(
        arguments(
            new GetPriceRequestDto("2020-06-14T10:00:00", 35455, 1),
            new GetPriceResponseDto(35455, 1, 1, "2020-06-14T00:00", "2020-12-31T23:59:59", 35.5)),
        arguments(
            new GetPriceRequestDto("2020-06-14T16:00:00", 35455, 1),
            new GetPriceResponseDto(35455, 1, 2, "2020-06-14T15:00", "2020-06-14T18:30", 25.45)),
        arguments(
            new GetPriceRequestDto("2020-06-14T21:00:00", 35455, 1),
            new GetPriceResponseDto(35455, 1, 1, "2020-06-14T00:00", "2020-12-31T23:59:59", 35.5)),
        arguments(
            new GetPriceRequestDto("2020-06-15T10:00:00", 35455, 1),
            new GetPriceResponseDto(35455, 1, 3, "2020-06-15T00:00", "2020-06-15T11:00", 30.5)),
        arguments(
            new GetPriceRequestDto("2020-06-16T21:00:00", 35455, 1),
            new GetPriceResponseDto(
                35455, 1, 4, "2020-06-15T16:00", "2020-12-31T23:59:59", 38.95)));
  }

  @PostConstruct
  private void init() {
    uri = "http://localhost:" + port + "/api/v1";
  }

  @ParameterizedTest
  @MethodSource("requestsToValidate")
  void should_return_a_price_for_a_given_product(
      GetPriceRequestDto request, GetPriceResponseDto expected) {
    var response =
        given()
            .param("applicationDate", request.applicationDate())
            .param("productId", request.productId())
            .param("brandId", request.brandId())
            .when()
            .get(uri + "/prices")
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .as(GetPriceResponseDto.class);

    assertThat(response).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void should_return_a_response_for_a_non_existing_product() {
    var request = new PriceRequest(new ApplicationDate("2300-06-16T21:00:00"), new ProductId(1), new BrandId(1));
    var expected = PriceNotFound.fromRequest(request);

    var response =
        given()
            .param("applicationDate", request.applicationDate().value())
            .param("productId", request.productId().value())
            .param("brandId", request.brandId().value())
            .when()
            .get(uri + "/prices")
            .then()
            .assertThat()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .extract()
            .as(PriceNotFound.class);

    assertThat(response).usingRecursiveComparison().isEqualTo(expected);
  }
}
