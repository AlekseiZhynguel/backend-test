package aleksei.project.backend_test.prices.domain;

import java.util.Optional;

public interface PriceRepository {

  Optional<Price> findPriceByApplicationDateAndProductIdAndBrandId(
      ApplicationDate date, ProductId productId, BrandId brandId);
}
