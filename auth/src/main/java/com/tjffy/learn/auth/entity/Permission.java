package com.tjffy.learn.auth.entity;

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
@Table(name = "t_permission")
public class Permission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String url;

    @NotNull
    private String method;

    @NotNull
    private String path;

    @NotNull
    private Integer isCom;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
    private List<GroupPermission> groupPermissions;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @JoinTable(name="t_group_permission",joinColumns={@JoinColumn(name="p_id")},inverseJoinColumns={@JoinColumn(name="g_id")})
    private List<Group> groups;
}
