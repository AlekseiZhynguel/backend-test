package aleksei.project.backend_test.products.application;

import aleksei.project.backend_test.products.domain.ApplicationDate;
import aleksei.project.backend_test.products.domain.BrandId;
import aleksei.project.backend_test.products.domain.ProductId;
import aleksei.project.backend_test.products.infrastructure.dto.GetProductRequestDto;
import org.springframework.stereotype.Service;

@Service
public class ListPrices {

  public Object execute(GetProductRequestDto request) {
    ApplicationDate date = new ApplicationDate(request.applicationDate());
    ProductId productId = new ProductId(request.productId());
    BrandId brandId = new BrandId(request.brandId());

    return null;
  }
}
