package aleksei.project.backend_test.products.infrastructure;

import aleksei.project.backend_test.products.application.ListPrices;
import aleksei.project.backend_test.products.infrastructure.dto.GetPriceResponseDto;
import aleksei.project.backend_test.products.infrastructure.dto.GetProductRequestDto;
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
  GetPriceResponseDto getPrice(@ParameterObject GetProductRequestDto request) {
    var result = useCase.execute(request);
    return new GetPriceResponseDto(
        request.productId(), request.brandId(), "1", request.applicationDate(), 30);
  }
}
