package org.boot.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
//@TableGenerator(
//        name = "user_table_generator",
//        table = "id_generator",
//        pkColumnName = "gen_name",
//        valueColumnName = "gen_value",
//        pkColumnValue = "user_id",
//        allocationSize = 1
//)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq_gen")
    @SequenceGenerator(name = "global_seq_gen", sequenceName = "global_sequence", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
