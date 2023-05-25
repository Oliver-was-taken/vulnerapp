package ch.bbw.m183.vulnerapp.datamodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@RequiredArgsConstructor
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
