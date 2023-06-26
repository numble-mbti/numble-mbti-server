package numble.mbti.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import numble.mbti.domain.user.entity.User;

@Setter
@AllArgsConstructor
@ToString
public class UserDto {
    private Long id;

    private String email;

    private String nickname;

    private String role;

    public UserDto() {
        this.email = "";
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email == null? "" : email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
        return role;
    }

    public static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getEmail(), user.getNickname(), user.getRole().getName());
    }
}
