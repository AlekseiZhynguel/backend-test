package aleksei.project.backend_test.products.application;

import static org.junit.jupiter.api.Assertions.*;

import aleksei.project.backend_test.products.domain.ApplicationDate;
import aleksei.project.backend_test.products.domain.BrandId;
import aleksei.project.backend_test.products.domain.PriceRequest;
import aleksei.project.backend_test.products.domain.ProductId;
import aleksei.project.backend_test.products.domain.exceptions.InvalidDateException;
import aleksei.project.backend_test.products.domain.exceptions.PriceNotFoundException;
import aleksei.project.backend_test.products.infrastructure.persistence.repository.InMemoryPriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class ListPricesTest {

  @Test
  void should_return_a_valid_response() {
    var repository = InMemoryPriceRepository.withSomePrices();
    var useCase = new ListPrices(repository);
    var request =
        new PriceRequest(
            new ApplicationDate("2020-08-14T10:00:00"), new ProductId(1), new BrandId(1));
    useCase.execute(request);
  }

  @Test
  void should_return_an_exception_when_could_not_find_price() {
    var repository = InMemoryPriceRepository.empty();
    var useCase = new ListPrices(repository);

    assertThrows(
        PriceNotFoundException.class,
        () -> {
          var request =
              new PriceRequest(
                  new ApplicationDate("2020-08-14T10:00:00"), new ProductId(1), new BrandId(1));
          useCase.execute(request);
        });
  }

  @Test
  void should_throw_an_exception_when_date_is_not_valid() {
    var repository = InMemoryPriceRepository.withSomePrices();
    var useCase = new ListPrices(repository);

    assertThrows(
        InvalidDateException.class,
        () -> {
          var request =
              new PriceRequest(new ApplicationDate("invalid"), new ProductId(1), new BrandId(1));
          useCase.execute(request);
        });
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/price_test_data.csv")
  void should_throw_an_exception_when_product_is_or_brand_id_are_not_valid(
      String date, int productId, int brandId) {
    var repository = InMemoryPriceRepository.withSomePrices();
    var useCase = new ListPrices(repository);

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          var request =
              new PriceRequest(
                  new ApplicationDate(date), new ProductId(productId), new BrandId(brandId));
          useCase.execute(request);
        });
  }
}
