-- ===============================
-- 短链接项目测试数据
-- ===============================

-- 1. 先创建一个分组（gid='15'）
INSERT INTO `t_group` (`gid`, `name`, `username`, `sort_order`, `create_time`, `update_time`, `del_flag`)
VALUES ('15', '测试分组', 'admin', 0, NOW(), NOW(), 0);

-- 2. 创建几条测试短链接数据
INSERT INTO `t_link` (
    `domain`, 
    `short_uri`, 
    `full_short_url`, 
    `origin_url`, 
    `click_num`, 
    `gid`, 
    `enable_status`, 
    `created_type`, 
    `valid_date_type`, 
    `valid_date`, 
    `describe`, 
    `create_time`, 
    `update_time`, 
    `del_flag`
) VALUES 
(
    'nurl.ink',
    'abc123',
    'nurl.ink/abc123',
    'https://www.baidu.com',
    0,
    '15',
    1,
    0,
    0,
    NULL,
    '测试短链接-百度',
    NOW(),
    NOW(),
    0
),
(
    'nurl.ink',
    'xyz789',
    'nurl.ink/xyz789',
    'https://www.github.com',
    10,
    '15',
    1,
    0,
    0,
    NULL,
    '测试短链接-GitHub',
    NOW(),
    NOW(),
    0
),
(
    'nurl.ink',
    'test01',
    'nurl.ink/test01',
    'https://www.google.com',
    5,
    '15',
    1,
    0,
    0,
    NULL,
    '测试短链接-Google',
    NOW(),
    NOW(),
    0
);

-- 3. 查询验证数据是否插入成功
SELECT * FROM t_group WHERE gid = '15';
SELECT * FROM t_link WHERE gid = '15'; 