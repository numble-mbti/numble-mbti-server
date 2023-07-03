package numble.mbti.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Builder
    public UserDto(String email, String nickname, String role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
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
