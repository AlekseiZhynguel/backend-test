package aleksei.project.backend_test.products.infrastructure.persistence.entity;

import java.time.LocalDateTime;

public class PriceEntity {

  private final String id;
  private final int brandId;
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;
  private final int priceList;
  private final int productId;
  private final int priority;
  private final double price;
  private final String currency;

  public PriceEntity(
      String id,
      int brandId,
      LocalDateTime startDate,
      LocalDateTime endDate,
      int priceList,
      int productId,
      int priority,
      double price,
      String currency) {
    this.id = id;
    this.brandId = brandId;
    this.startDate = startDate;
    this.endDate = endDate;
    this.priceList = priceList;
    this.productId = productId;
    this.priority = priority;
    this.price = price;
    this.currency = currency;
  }

  public String id() {
    return id;
  }

  public int brandId() {
    return brandId;
  }

  public LocalDateTime startDate() {
    return startDate;
  }

  public LocalDateTime endDate() {
    return endDate;
  }

  public int priceList() {
    return priceList;
  }

  public int productId() {
    return productId;
  }

  public int priority() {
    return priority;
  }

  public double price() {
    return price;
  }

  public String currency() {
    return currency;
  }
}
