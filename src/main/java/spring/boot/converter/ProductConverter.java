package spring.boot.converter;

import spring.boot.model.dao.ProductDao;
import spring.boot.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class ProductConverter implements Converter<ProductDto, ProductDao> {
    @Override
    public ProductDto from(ProductDao entity) {
        ProductDto productDto = new ProductDto();
        ManufacturerConverter converter = new ManufacturerConverter();

        productDto.setId(entity.getId());
        productDto.setName(entity.getName());
        productDto.setPrice(entity.getPrice());
        productDto.setManufacturer(converter.from(entity.getManufacturer()));

        return productDto;
    }

    @Override
    public List<ProductDto> fromList(List<ProductDao> list) {
        List<ProductDto> dtoList = new ArrayList<>();

        for (ProductDao dao : list) {
            dtoList.add(from(dao));
        }

        return dtoList;
    }
}
