package aleksei.project.backend_test.prices.domain;

import aleksei.project.backend_test.prices.domain.primitives.PricePrimitives;

public class Price {

  private final PriceId id;
  private final BrandId brandId;
  private final ApplicationDate startDate;
  private final ApplicationDate endDate;
  private final PriceList priceList;
  private final ProductId productId;
  private final Priority priority;
  private final Amount amount;
  private final Currency currency;

  public Price(
      PriceId id,
      BrandId brandId,
      ApplicationDate startDate,
      ApplicationDate endDate,
      PriceList priceList,
      ProductId productId,
      Priority priority,
      Amount amount,
      Currency currency) {
    this.id = id;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceList = priceList;
    this.productId = productId;
    this.priority = priority;
    this.amount = amount;
    this.currency = currency;
  }

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

  public PriceId id() {
    return id;
  }

  public BrandId brandId() {
    return brandId;
  }

  public ApplicationDate startDate() {
    return startDate;
  }

  public ApplicationDate endDate() {
    return endDate;
  }

  public PriceList priceList() {
    return priceList;
  }

  public ProductId productId() {
    return productId;
  }

  public Priority priority() {
    return priority;
  }

  public Amount amount() {
    return amount;
  }

  public Currency currency() {
    return currency;
  }
}
