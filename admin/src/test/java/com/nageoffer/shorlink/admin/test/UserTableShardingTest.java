package com.nageoffer.shorlink.admin.test;

/**
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-03 15:52
 */
public class UserTableShardingTest {
    public static final String SQL = "-- auto-generated definition\n" +
            "create table t_group_%d\n" +
            "(\n" +
            "    id          bigint auto_increment comment 'ID'\n" +
            "        primary key,\n" +
            "    gid         varchar(32)  null comment '分组标识',\n" +
            "    name        varchar(64)  null comment '分组名称',\n" +
            "    username    varchar(256) null comment '创建分组用户名',\n" +
            "    sort_order  int          null comment '分组排序',\n" +
            "    create_time datetime     null comment '创建时间',\n" +
            "    update_time datetime     null comment '修改时间',\n" +
            "    del_flag    tinyint(1)   null comment '删除标识 0：未删除 1：已删除',\n" +
            "    constraint idx_unique_username_gid\n" +
            "        unique (gid, username)\n" +
            ");\n" +
            "\n";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL) + "%n", i);
        }
    }
}
