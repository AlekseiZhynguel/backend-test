package aleksei.project.backend_test.prices.infrastructure.controller;

import aleksei.project.backend_test.prices.application.PriceSearcher;
import aleksei.project.backend_test.prices.domain.request.PriceRequest;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceRequestDto;
import aleksei.project.backend_test.prices.infrastructure.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PriceGetController {

  private final PriceSearcher useCase;
  private final PriceMapper priceMapper;

  public PriceGetController(PriceSearcher useCase, PriceMapper priceMapper) {
    this.useCase = useCase;
    this.priceMapper = priceMapper;
  }

  @Operation(summary = "Retrieve price for a given product")
  @GetMapping("/prices")
  ResponseEntity<?> getPrice(@ParameterObject GetPriceRequestDto requestDto) {
    PriceRequest request = priceMapper.toDomainRequest(requestDto);
    return useCase
        .execute(request)
        .fold(
            error -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error),
            price -> ResponseEntity.ok(priceMapper.toResponse(price)));
  }
}
