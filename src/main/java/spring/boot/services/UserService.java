package spring.boot.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.UserConverter;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dto.UserDto;
import spring.boot.repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository repository;
    @Autowired
    private final UserConverter converter;

    public void create(UserDto user) {
        repository.save(converter.to(user));
    }

    public List<UserDto> getUsers() {
        return converter.fromList(repository.findAll());
    }

    public void deleteByEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void updateByEmail(String newEmail, String password, String first_name, String lastName, RoleDao role,
                              String oldEmail) {
        repository.updateByEmail(newEmail, password, first_name, lastName, role, oldEmail);
    }

    public boolean IsUserEmailExists(String email) {
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
