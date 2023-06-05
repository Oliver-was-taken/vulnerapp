package ch.bbw.m183.vulnerapp.datamodel;

import jakarta.persistence.*;
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
    String username;

    @Column
    String fullname;

    @Column
    String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
