package aleksei.project.backend_test.products.domain;

public class PriceMother {
  public static Price random() {
    return new Price(
        new PriceId("id"),
        new BrandId(1),
        new ApplicationDate("2020-06-14T10:00:00"),
        new ApplicationDate("2021-06-14T10:00:00"),
        new PriceList(1),
        new ProductId(1),
        new Priority(1),
        new Amount(30.80),
        new Currency("EUR"));
  }
}
