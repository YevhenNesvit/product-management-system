package spring.boot.converter;

import spring.boot.model.dao.UserDao;
import spring.boot.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserConverter implements Converter<UserDto, UserDao> {
    @Override
    public UserDto from(UserDao entity) {
        UserDto userDto = new UserDto();
        RoleConverter converter = new RoleConverter();

        userDto.setId(entity.getId());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        userDto.setFirstName(entity.getFirstName());
        userDto.setLastName(entity.getLastName());
        userDto.setRole(converter.from(entity.getRole()));

        return userDto;
    }

    @Override
    public List<UserDto> fromList(List<UserDao> list) {
        List<UserDto> dtoList = new ArrayList<>();

        for (UserDao dao : list) {
            dtoList.add(from(dao));
        }

        return dtoList;
    }
}
