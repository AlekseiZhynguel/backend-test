package aleksei.project.backend_test.prices.domain.primitives;

public record PricePrimitives(
    String id,
    int brandId,
    String startDate,
    String endDate,
    int priceList,
    int productId,
    int priority,
    double price,
    String currency) {}
