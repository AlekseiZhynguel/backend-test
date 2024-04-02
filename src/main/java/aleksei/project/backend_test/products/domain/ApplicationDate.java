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

  public boolean isInDateRange(String startDate, String endDate) {
    LocalDateTime thisDateTime = parseDateTime(this.date);
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
