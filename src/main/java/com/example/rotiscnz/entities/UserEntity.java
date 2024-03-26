package com.example.rotiscnz.entities;

import com.example.rotiscnz.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "dinner_dash", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "display_name")
    private String displayName;
    @Basic
    @Column(name = "full_name")
    private String fullName;

    @ColumnDefault("'CUSTOMER'")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String password;

}
