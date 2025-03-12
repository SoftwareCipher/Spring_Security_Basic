package org.boot.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.boot.security.entity.Role;
import org.boot.security.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;

    @NotBlank(message = "Name may not be blank")
    private String name;
    private String password;
    private RoleDTO role;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.role = new RoleDTO(user.getRole().getId(), user.getRole().getName());
    }

    public UserDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = new RoleDTO(role.getId(), role.getName());
    }

    public UserDTO(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
