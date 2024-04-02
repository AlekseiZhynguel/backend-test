package aleksei.project.backend_test.products.infrastructure.controller;

import aleksei.project.backend_test.products.application.ListPrices;
import aleksei.project.backend_test.products.domain.ApplicationDate;
import aleksei.project.backend_test.products.domain.BrandId;
import aleksei.project.backend_test.products.domain.PriceRequest;
import aleksei.project.backend_test.products.domain.ProductId;
import aleksei.project.backend_test.products.infrastructure.controller.dto.GetPriceResponseDto;
import aleksei.project.backend_test.products.infrastructure.controller.dto.GetProductRequestDto;
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
  GetPriceResponseDto getPrice(@ParameterObject GetProductRequestDto requestDto) {
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
        result.startDate().date(),
        result.endDate().date(),
        result.amount().value());
  }
}
