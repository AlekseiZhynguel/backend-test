package aleksei.project.backend_test.prices.domain.request;

import aleksei.project.backend_test.prices.domain.ApplicationDate;
import aleksei.project.backend_test.prices.domain.BrandId;
import aleksei.project.backend_test.prices.domain.ProductId;

public record PriceRequest(ApplicationDate applicationDate, ProductId productId, BrandId brandId) {}
