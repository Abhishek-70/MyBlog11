package com.myblog11.myblog11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
}
