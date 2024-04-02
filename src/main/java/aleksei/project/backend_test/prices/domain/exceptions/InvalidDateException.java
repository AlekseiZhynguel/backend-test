package aleksei.project.backend_test.prices.domain.exceptions;

import aleksei.project.backend_test.shared.domain.DomainError;

public class InvalidDateException extends DomainError {
  public InvalidDateException(String date) {
    super("invalid_date", String.format("The field <%s> is not a valid date", date));
  }
}
