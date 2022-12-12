package spring.boot.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.boot.services.UserService;
@AllArgsConstructor
@Component
public class CheckUsers {
    @Autowired
    private final UserService userService;

    public boolean IsUserEmailExists(String email) {
        for (int i = 0; i < userService.getUsers().size(); i++) {
            if (userService.getUsers().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
