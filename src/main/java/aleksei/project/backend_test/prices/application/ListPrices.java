package aleksei.project.backend_test.prices.application;

import aleksei.project.backend_test.prices.domain.*;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

@Service
public class ListPrices {

  private final PriceRepository repository;

  public ListPrices(PriceRepository repository) {
    this.repository = repository;
  }

  public Either<PriceNotFound, Price> execute(PriceRequest request) {

    return repository
        .findPriceByApplicationDateAndProductIdAndBrandId(
            request.applicationDate(), request.productId(), request.brandId())
        .<Either<PriceNotFound, Price>>map(Either::right)
        .orElseGet(() -> Either.left(PriceNotFound.fromRequest(request)));
  }
}
