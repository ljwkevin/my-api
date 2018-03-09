package com.yd.jpa.entity.purOrder;

import com.yd.annotation.ColumnDesc;
import com.yd.jpa.entity.IdEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author Yd on  2018-03-07
 * @description 请购单-表头
 **/
@Entity
@Table(name = "PurOrder")
@Data
public class PurOrder extends IdEntity implements Serializable {

    @Column(name = "purOrderNo",nullable = false)
    @ColumnDesc(columnDesc = "请购单号")
    private String purOrderNo;

    @Column
    @ColumnDesc(columnDesc = "订单类型")
    private String orderType;

    @Column
    @ColumnDesc(columnDesc = "创建人")
    private String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column()
    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDesc(columnDesc = "创建时间")
    private Date createDate;

    @Column()
    @ColumnDesc(columnDesc = "配送中心编码")
    private String orgCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "purorder")//mappedBy 指的是 引用类的属性
    private Set<PurOrderItem> items=new HashSet<PurOrderItem>();
}
