package aleksei.project.backend_test.products.infrastructure.persistence.repository;

import aleksei.project.backend_test.products.domain.ApplicationDate;
import aleksei.project.backend_test.products.domain.BrandId;
import aleksei.project.backend_test.products.domain.Price;
import aleksei.project.backend_test.products.domain.PriceRepository;
import aleksei.project.backend_test.products.domain.ProductId;
import aleksei.project.backend_test.products.domain.primitives.PricePrimitives;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPriceRepository implements PriceRepository {

  public static PricePrimitives price =
      new PricePrimitives(
          "id", 1, "2020-06-14T10:00:00", "2021-06-14T10:00:00", 1, 1, 1, 30.80, "EUR");

  private final Map<String, PricePrimitives> prices = new HashMap<>();

  public static InMemoryPriceRepository withSomePrices() {
    var repository = new InMemoryPriceRepository();

    repository.prices.put(price.id(), price);

    return repository;
  }

  public static InMemoryPriceRepository empty() {
    return new InMemoryPriceRepository();
  }

  @Override
  public Optional<Price> findPriceByApplicationDateAndProductIdAndBrandId(
      ApplicationDate date, ProductId productId, BrandId brandId) {
    return prices.values().stream()
        .filter(
            entity ->
                entity.brandId() == brandId.value()
                    && entity.productId() == productId.value()
                    && date.isInDateRange(entity.startDate(), entity.endDate()))
        .sorted(Comparator.comparingInt(PricePrimitives::priority).reversed())
        .limit(1)
        .findFirst()
        .map(Price::fromPrimitives);
  }
}
