package spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.UserConverter;
import spring.boot.model.dao.UserDao;
import spring.boot.model.dto.UserDto;
import spring.boot.repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final UserConverter converter;

    public void create(UserDao user) {
        repository.save(user);
    }

    public List<UserDto> getUsers() {
        return converter.fromList(repository.findAll());
    }
}
