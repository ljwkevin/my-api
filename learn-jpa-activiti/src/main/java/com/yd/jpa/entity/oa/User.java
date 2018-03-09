package com.yd.jpa.entity.oa;

import com.yd.jpa.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Yd on  2018-03-06
 * @description
 **/
@Entity
@Table(name = "User")
public class User extends IdEntity implements Serializable {

    @Column
    private String name;
}
