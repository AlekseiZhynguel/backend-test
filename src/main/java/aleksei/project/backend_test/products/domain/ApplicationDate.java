package aleksei.project.backend_test.products.domain;

import aleksei.project.backend_test.products.domain.exceptions.InvalidDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ApplicationDate(String date) {
  public ApplicationDate {
    ensureIsValidDate(date);
  }

  private void ensureIsValidDate(String date) {
    try {
      LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
    } catch (Exception exception) {
      throw new InvalidDateException(date);
    }
  }
}
