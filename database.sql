-- 水果信息表
CREATE TABLE fruit
(
  id       int COMMENT 'id',
  sci_name varchar(200) COMMENT '学名',
  type     varchar(200) COMMENT '种类'
);
ALTER TABLE fruit
  COMMENT = '水果信息表';