package aleksei.project.backend_test.prices.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import aleksei.project.backend_test.prices.domain.*;
import aleksei.project.backend_test.prices.domain.exceptions.InvalidDateException;
import aleksei.project.backend_test.prices.infrastructure.persistence.repository.memory.InMemoryPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ListPricesTest {

  @Test
  void should_return_a_valid_price_for_an_existing_product() {
    var repository = InMemoryPriceRepository.withSomePrices();
    var useCase = new ListPrices(repository);
    var expected = PriceMother.random();
    var request =
        new PriceRequest(
            new ApplicationDate("2020-08-14T10:00:00"), new ProductId(1), new BrandId(1));

    var result = useCase.execute(request);

    assertThat(result.get()).isEqualTo(expected);
  }

  @Test
  void should_return_a_response_when_could_not_find_price() {
    var repository = InMemoryPriceRepository.empty();
    var useCase = new ListPrices(repository);
    var expected = new PriceNotFound("2020-08-14T10:00:00", 1, 1);
    var request =
        new PriceRequest(
            new ApplicationDate("2020-08-14T10:00:00"), new ProductId(1), new BrandId(1));

    var result = useCase.execute(request);

    assertThat(result.getLeft()).isEqualTo(expected);
  }

  @Test
  void should_throw_an_exception_when_date_is_not_valid() {
    assertThrows(
        InvalidDateException.class,
        () -> new PriceRequest(new ApplicationDate("invalid"), new ProductId(1), new BrandId(1)));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/invalid_ids.csv")
  void should_throw_an_exception_when_product_id_or_brand_id_are_not_valid(
      String date, int productId, int brandId) {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new PriceRequest(
                new ApplicationDate(date), new ProductId(productId), new BrandId(brandId)));
  }
}
