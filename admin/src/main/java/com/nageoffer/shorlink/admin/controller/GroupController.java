package com.nageoffer.shorlink.admin.controller;

import com.nageoffer.shorlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短链接分组控制层
 * @author 一只咸鱼的大厂梦-hxw
 * @date 2025-09-09 09:05
 */
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
}
