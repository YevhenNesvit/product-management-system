package spring.boot.role;

import org.springframework.stereotype.Component;
import spring.boot.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Component
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

    @Override
    public RoleDao to(RoleDto entity) {
        RoleDao roleDao = new RoleDao();

        roleDao.setId(entity.getId());
        roleDao.setName(entity.getName());

        return roleDao;
    }
}
