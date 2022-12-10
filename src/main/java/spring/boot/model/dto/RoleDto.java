package spring.boot.model.dto;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class RoleDto {
    private UUID id;
    private String name;

    public RoleDto() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
