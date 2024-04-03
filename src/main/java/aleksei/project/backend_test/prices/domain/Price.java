package aleksei.project.backend_test.prices.domain;

import aleksei.project.backend_test.prices.domain.jooq.ecommerce.tables.records.PricesRecord;
import aleksei.project.backend_test.prices.domain.primitives.PricePrimitives;

public record Price(
    PriceId id,
    BrandId brandId,
    ApplicationDate startDate,
    ApplicationDate endDate,
    PriceList priceList,
    ProductId productId,
    Priority priority,
    Amount amount,
    Currency currency) {

  public static Price fromPrimitives(PricePrimitives primitives) {
    return new Price(
        new PriceId(primitives.id()),
        new BrandId(primitives.brandId()),
        new ApplicationDate(primitives.startDate()),
        new ApplicationDate(primitives.endDate()),
        new PriceList(primitives.priceList()),
        new ProductId(primitives.productId()),
        new Priority(primitives.priority()),
        new Amount(primitives.price()),
        new Currency(primitives.currency()));
  }

  public static Price fromRecord(PricesRecord pricesRecord) {
    return new Price(
        new PriceId(pricesRecord.getId()),
        new BrandId(pricesRecord.getBrandId()),
        new ApplicationDate(pricesRecord.getStartDate().toString()),
        new ApplicationDate(pricesRecord.getEndDate().toString()),
        new PriceList(pricesRecord.getPriceList()),
        new ProductId(pricesRecord.getProductId()),
        new Priority(pricesRecord.getPriority()),
        new Amount(pricesRecord.getPrice().doubleValue()),
        new Currency(pricesRecord.getCurrency()));
  }
}
