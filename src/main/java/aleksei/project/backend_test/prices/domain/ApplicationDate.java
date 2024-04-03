package aleksei.project.backend_test.prices.domain;

import aleksei.project.backend_test.prices.domain.exceptions.InvalidDateException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record ApplicationDate(String value) {
  public ApplicationDate {
    ensureIsValidDate(value);
  }

  private void ensureIsValidDate(String value) {
    try {
      LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME);
    } catch (Exception exception) {
      throw new InvalidDateException(value);
    }
  }

  public boolean isInDateRange(String startDate, String endDate) {
    LocalDateTime thisDateTime = parseDateTime(this.value);
    LocalDateTime otherStartDate = parseDateTime(startDate);
    LocalDateTime otherEndDate = parseDateTime(endDate);

    return dateIsAfterStartDateAndBeforeEndDate(otherStartDate, otherEndDate, thisDateTime);
  }

  private LocalDateTime parseDateTime(String date) {
    return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
  }

  private boolean dateIsAfterStartDateAndBeforeEndDate(
      LocalDateTime startDate, LocalDateTime endDate, LocalDateTime thisDateTime) {
    return thisDateTime.isAfter(startDate) && thisDateTime.isBefore(endDate);
  }
}
