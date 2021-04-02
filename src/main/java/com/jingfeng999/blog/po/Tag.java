package com.jingfeng999.blog.po;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther : jingfeng999
 * @Date : 2021/4/2 20:53
 * @Description: blog
 * @Version: 1.0
 */
@Entity
@Table(name = "t_tag")
@Data
public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
