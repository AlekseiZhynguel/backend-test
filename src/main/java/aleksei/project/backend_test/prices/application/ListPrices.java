package aleksei.project.backend_test.prices.application;

import aleksei.project.backend_test.prices.domain.*;
import aleksei.project.backend_test.prices.domain.exceptions.PriceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ListPrices {

  private final PriceRepository repository;

  public ListPrices(PriceRepository repository) {
    this.repository = repository;
  }

  public Price execute(PriceRequest request) {

    return repository
        .findPriceByApplicationDateAndProductIdAndBrandId(
            request.applicationDate(), request.productId(), request.brandId())
        .orElseThrow(
            () -> new PriceNotFoundException("price_not_found", "Could not find any price"));
  }
}
