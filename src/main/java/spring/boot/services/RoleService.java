package spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.converter.RoleConverter;
import spring.boot.model.dao.RoleDao;
import spring.boot.model.dto.RoleDto;
import spring.boot.repositories.RoleRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {
    @Autowired
    private final RoleRepository repository;
    @Autowired
    private final RoleConverter converter;

    public void create(RoleDao role) {
        repository.save(role);
    }

    public List<RoleDto> getRoles() {
        return converter.fromList(repository.findAll());
    }

    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
