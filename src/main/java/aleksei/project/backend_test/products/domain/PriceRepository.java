package aleksei.project.backend_test.products.domain;

import java.util.Optional;

public interface PriceRepository {

  Optional<Price> findPriceByApplicationDateAndProductIdAndBrandId(
      ApplicationDate date, ProductId productId, BrandId brandId);
}
