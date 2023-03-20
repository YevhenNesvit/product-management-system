package spring.boot.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.UserConverter;
import spring.boot.model.dto.UserDto;
import spring.boot.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

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

    public boolean IsUserEmailExists(String email) {
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public UUID getUserIdByEmail(String email) {
        List<UserDto> users = getUsers();
        UUID id = null;
        for (UserDto user : users) {
            if (user.getEmail().equals(email)) {
                id = user.getId();
            }
        }
        return id;
    }
}
