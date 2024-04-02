package aleksei.project.backend_test.prices.domain;

public record ProductId(int value) {
  public ProductId {
    ensureIsValidId(value);
  }

  private void ensureIsValidId(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("ProductId must be a positive integer");
    }
  }
}
