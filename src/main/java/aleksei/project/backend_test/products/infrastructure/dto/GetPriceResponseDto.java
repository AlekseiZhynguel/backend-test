package aleksei.project.backend_test.products.infrastructure.dto;

public record GetPriceResponseDto(
    int productId, int brandId, String priceList, String date, int price) {}
