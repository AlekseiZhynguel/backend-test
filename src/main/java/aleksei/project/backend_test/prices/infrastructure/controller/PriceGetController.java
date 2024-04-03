package aleksei.project.backend_test.prices.infrastructure.controller;

import aleksei.project.backend_test.prices.application.ListPrices;
import aleksei.project.backend_test.prices.domain.*;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceRequestDto;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PriceGetController {

  private final ListPrices useCase;

  public PriceGetController(ListPrices useCase) {
    this.useCase = useCase;
  }

  @Operation(summary = "Retrieve price for a given product")
  @GetMapping("/prices")
  ResponseEntity<?> getPrice(@ParameterObject GetPriceRequestDto requestDto) {
    PriceRequest request = request(requestDto);

    return useCase
        .execute(request)
        .fold(
            error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
            price -> ResponseEntity.ok(responseDto(price)));
  }

  private PriceRequest request(GetPriceRequestDto requestDto) {
    return new PriceRequest(
        new ApplicationDate(requestDto.applicationDate()),
        new ProductId(requestDto.productId()),
        new BrandId(requestDto.brandId()));
  }

  private GetPriceResponseDto responseDto(Price price) {
    return new GetPriceResponseDto(
        price.productId().value(),
        price.brandId().value(),
        price.priceList().value(),
        price.startDate().value(),
        price.endDate().value(),
        price.amount().value());
  }
}
