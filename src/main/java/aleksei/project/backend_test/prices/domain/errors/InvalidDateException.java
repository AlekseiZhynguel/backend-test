package aleksei.project.backend_test.prices.domain.errors;

public class InvalidDateException extends DomainError {
  public InvalidDateException(String date) {
    super("invalid_date", String.format("The field <%s> is not a valid value", date));
  }
}
