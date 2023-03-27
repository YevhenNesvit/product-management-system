package spring.boot.converter;

import org.springframework.stereotype.Component;
import spring.boot.manufacturer.ManufacturerDao;
import spring.boot.manufacturer.ManufacturerDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManufacturerConverter implements Converter<ManufacturerDto, ManufacturerDao>{
    @Override
    public ManufacturerDto from(ManufacturerDao entity) {
        ManufacturerDto manufacturerDto = new ManufacturerDto();

        manufacturerDto.setId(entity.getId());
        manufacturerDto.setName(entity.getName());

        return manufacturerDto;
    }

    @Override
    public List<ManufacturerDto> fromList(List<ManufacturerDao> list) {
        List<ManufacturerDto> dtoList = new ArrayList<>();

        for (ManufacturerDao dao : list) {
            dtoList.add(from(dao));
        }

        return dtoList;
    }

    @Override
    public ManufacturerDao to(ManufacturerDto entity) {
        ManufacturerDao manufacturerDao = new ManufacturerDao();

        manufacturerDao.setId(entity.getId());
        manufacturerDao.setName(entity.getName());

        return manufacturerDao;
    }
}
