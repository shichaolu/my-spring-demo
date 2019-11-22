package com.example.rwseparatedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: Shichao.Lu
 * @Date: Created in 14:29 2019/7/10
 */
@Builder
@Data
@Table(name = "t_member")
public class Member {

    /**
     * 配置为"JDBC",在数据插入完毕之后,会自动将主键id填充到实体类中.类似普通mapper.xml中配置的selectKey标签
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    @Column
    @NotBlank
    private String name;

}
