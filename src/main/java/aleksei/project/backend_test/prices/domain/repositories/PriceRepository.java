package aleksei.project.backend_test.prices.domain.repositories;

import aleksei.project.backend_test.prices.domain.ApplicationDate;
import aleksei.project.backend_test.prices.domain.BrandId;
import aleksei.project.backend_test.prices.domain.Price;
import aleksei.project.backend_test.prices.domain.ProductId;

import java.util.Optional;

public interface PriceRepository {

  Optional<Price> findPriceByApplicationDateAndProductIdAndBrandId(
          ApplicationDate date, ProductId productId, BrandId brandId);
}
