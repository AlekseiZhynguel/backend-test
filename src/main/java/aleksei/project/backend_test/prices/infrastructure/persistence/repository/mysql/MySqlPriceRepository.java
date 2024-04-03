package aleksei.project.backend_test.prices.infrastructure.persistence.repository.mysql;


import static aleksei.project.backend_test.prices.domain.jooq.ecommerce.tables.Prices.PRICES;

import aleksei.project.backend_test.prices.domain.*;
import java.time.LocalDateTime;
import java.util.Optional;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MySqlPriceRepository implements PriceRepository {

  private final DSLContext dsl;

  public MySqlPriceRepository(DSLContext dsl) {
    this.dsl = dsl;
  }

  @Override
  public Optional<Price> findPriceByApplicationDateAndProductIdAndBrandId(
      ApplicationDate applicationDate, ProductId productId, BrandId brandId) {

    return dsl.selectFrom(PRICES)
        .where(
            PRICES
                .START_DATE
                .le(LocalDateTime.parse(applicationDate.value()))
                .and(PRICES.END_DATE.ge(LocalDateTime.parse(applicationDate.value())))
                .and(PRICES.PRODUCT_ID.eq(productId.value()))
                .and(PRICES.BRAND_ID.eq(brandId.value())))
        .orderBy(PRICES.PRIORITY.desc())
        .limit(1)
        .fetchOptional()
        .map(Price::fromRecord);
  }
}
