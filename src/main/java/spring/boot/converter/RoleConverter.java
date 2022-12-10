package spring.boot.converter;

import spring.boot.model.dao.RoleDao;
import spring.boot.model.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

public class RoleConverter implements Converter<RoleDto, RoleDao> {
    @Override
    public RoleDto from(RoleDao entity) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(entity.getId());
        roleDto.setName(entity.getName());

        return roleDto;
    }

    @Override
    public List<RoleDto> fromList(List<RoleDao> list) {
        List<RoleDto> dtoList = new ArrayList<>();

        for (RoleDao dao : list) {
            dtoList.add(from(dao));
        }

        return dtoList;
    }
}
