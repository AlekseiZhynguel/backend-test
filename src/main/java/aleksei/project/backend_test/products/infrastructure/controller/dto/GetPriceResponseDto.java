package aleksei.project.backend_test.products.infrastructure.controller.dto;

public record GetPriceResponseDto(
    int productId, int brandId, int priceList, String startDate, String endDate, double price) {}
