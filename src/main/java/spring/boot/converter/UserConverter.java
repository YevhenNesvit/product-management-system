package spring.boot.converter;

import org.springframework.stereotype.Component;
import spring.boot.model.dao.UserDao;
import spring.boot.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component
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

    @Override
    public UserDao to(UserDto entity) {
        UserDao userDao = new UserDao();
        RoleConverter converter = new RoleConverter();

        userDao.setId(entity.getId());
        userDao.setEmail(entity.getEmail());
        userDao.setPassword(entity.getPassword());
        userDao.setFirstName(entity.getFirstName());
        userDao.setLastName(entity.getLastName());
        userDao.setRole(converter.to(entity.getRole()));

        return userDao;
    }
}
