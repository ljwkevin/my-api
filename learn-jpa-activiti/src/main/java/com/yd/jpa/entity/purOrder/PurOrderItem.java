package com.yd.jpa.entity.purOrder;

import com.yd.annotation.ColumnDesc;
import com.yd.jpa.entity.IdEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Yd on  2018-03-07
 * @description
 **/
@Entity
@Table(name = "PurOrderItem")
@Data
public class PurOrderItem extends IdEntity implements Serializable {

    // optional=true：可选，表示此对象可以没有，可以为null；false表示必须存在
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, optional = true)
    @JoinColumn(name = "purOrderNo", nullable = false)
    private PurOrder purorder;

    @Column
    @ColumnDesc(columnDesc = "商品编码")
    private String goodsCode;

    @Column
    @ColumnDesc(columnDesc = "数量")
    private Double quality;

    @Column
    @ColumnDesc(columnDesc = "价格")
    private BigDecimal price;

}
