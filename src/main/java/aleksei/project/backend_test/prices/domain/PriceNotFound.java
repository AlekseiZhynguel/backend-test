package aleksei.project.backend_test.prices.domain;

public record PriceNotFound(String message, Parameters parameters) {
  public static PriceNotFound fromRequest(PriceRequest request) {
    return new PriceNotFound(
        "Price not found",
        new Parameters(
            request.applicationDate().value(),
            request.productId().value(),
            request.brandId().value()));
  }
}
