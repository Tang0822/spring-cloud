package com.tjffy.learn.authuser.entity;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author jftang3
 */
@Entity
@Getter
@Setter
@Table(name = "t_group")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer type;

    @NotNull
    private String content;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<GroupPermission> groupPermissions;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="t_group_permission",joinColumns={@JoinColumn(name="g_id")},inverseJoinColumns={@JoinColumn(name="p_id")})
    private List<Permission> permissions;
}
