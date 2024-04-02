package aleksei.project.backend_test.products.application;

import static org.junit.jupiter.api.Assertions.*;

import aleksei.project.backend_test.products.domain.exceptions.InvalidDateException;
import aleksei.project.backend_test.products.infrastructure.dto.GetProductRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ListPricesTest {

  private final ListPrices useCase = new ListPrices();

  @Test
  void should_return_a_valid_response() {
    GetProductRequestDto request = new GetProductRequestDto("2020-06-14T10:00:00", 1, 1);
    useCase.execute(request);
  }

  @Test
  void should_throw_an_exception_when_date_is_not_valid() {
    assertThrows(
        InvalidDateException.class,
        () -> {
          GetProductRequestDto request = new GetProductRequestDto("invalid", 1, 1);
          useCase.execute(request);
        });
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/price_test_data.csv")
  void should_throw_an_exception_when_product_is_or_brand_id_are_not_valid(
      String date, int productId, int brandId) {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          GetProductRequestDto request = new GetProductRequestDto(date, productId, brandId);
          useCase.execute(request);
        });
  }
}
