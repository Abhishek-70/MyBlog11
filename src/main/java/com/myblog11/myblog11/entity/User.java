package com.myblog11.myblog11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users", uniqueConstraints={
        @UniqueConstraint(columnNames ={"userName"}),
        @UniqueConstraint(columnNames={"email"})
})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String userName;
    private String email;
    private String password;

    private String roleType;

    @ManyToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="user_roles",
    joinColumns = @JoinColumn(name="user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;

}
