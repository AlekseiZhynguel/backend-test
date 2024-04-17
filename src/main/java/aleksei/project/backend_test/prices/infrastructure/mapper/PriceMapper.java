package aleksei.project.backend_test.prices.infrastructure.mapper;

import aleksei.project.backend_test.prices.domain.*;
import aleksei.project.backend_test.prices.domain.request.PriceRequest;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceRequestDto;
import aleksei.project.backend_test.prices.infrastructure.controller.dto.GetPriceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public PriceRequest toDomainRequest(GetPriceRequestDto dto) {
        return new PriceRequest(
                new ApplicationDate(dto.applicationDate()),
                new ProductId(dto.productId()),
                new BrandId(dto.brandId())
        );
    }

    public GetPriceResponseDto toResponse(Price price) {
        return new GetPriceResponseDto(
                price.productId().value(),
                price.brandId().value(),
                price.priceList().value(),
                price.startDate().value(),
                price.endDate().value(),
                price.amount().value()
        );
    }
}
