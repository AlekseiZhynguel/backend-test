package aleksei.project.backend_test.prices.domain.exceptions;

import aleksei.project.backend_test.shared.domain.DomainError;

public class PriceNotFoundException extends DomainError {
  public PriceNotFoundException(String errorCode, String errorMessage) {
    super(errorCode, errorMessage);
  }
}
