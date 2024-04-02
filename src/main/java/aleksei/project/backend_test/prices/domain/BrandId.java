package aleksei.project.backend_test.prices.domain;

public record BrandId(int value) {

  public BrandId {
    ensureIsValidId(value);
  }

  private void ensureIsValidId(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("BrandId must be a positive integer");
    }
  }
}
