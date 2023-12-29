package com.github.xuchen93.cloud.seata.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@TableName("seata_t1")
public class SeataT1 {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String varV1;
    private Integer intV1;
}
