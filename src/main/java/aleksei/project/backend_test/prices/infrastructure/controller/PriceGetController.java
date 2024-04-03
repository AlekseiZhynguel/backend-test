package aleksei.project.backend_test.prices.infrastructure.controller;

import aleksei.project.backend_test.prices.application.ListPrices;
import aleksei.project.backend_test.prices.domain.ApplicationDate;
import aleksei.project.backend_test.prices.domain.BrandId;
import aleksei.project.backend_test.prices.domain.PriceRequest;
import aleksei.project.backend_test.prices.domain.ProductId;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceRequestDto;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
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
  @ResponseStatus(HttpStatus.OK)
  GetPriceResponseDto getPrice(@ParameterObject GetPriceRequestDto requestDto) {
    PriceRequest request =
        new PriceRequest(
            new ApplicationDate(requestDto.applicationDate()),
            new ProductId(requestDto.productId()),
            new BrandId(requestDto.brandId()));

    var result = useCase.execute(request);

    return new GetPriceResponseDto(
        result.productId().value(),
        result.brandId().value(),
        result.priceList().value(),
        result.startDate().value(),
        result.endDate().value(),
        result.amount().value());
  }
}
