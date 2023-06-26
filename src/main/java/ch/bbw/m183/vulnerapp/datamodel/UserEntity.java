package ch.bbw.m183.vulnerapp.datamodel;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @NotBlank(message = "Username is mandatory")
    String username;

    @Column
    @NotBlank(message = "Fullname is mandatory")
    String fullname;

    @Column
    @NotBlank(message = "Password is mandatory")
    String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
