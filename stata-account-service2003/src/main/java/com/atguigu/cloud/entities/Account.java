package com.atguigu.cloud.entities;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 表名：t_account
*/
@Table(name = "t_account")
@ToString
public class Account implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用账户
     */
    private BigDecimal userd;

    /**
     * 剩余可用额度
     */
    private BigDecimal residue;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取总额度
     *
     * @return total - 总额度
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置总额度
     *
     * @param total 总额度
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取已用账户
     *
     * @return userd - 已用账户
     */
    public BigDecimal getUserd() {
        return userd;
    }

    /**
     * 设置已用账户
     *
     * @param userd 已用账户
     */
    public void setUserd(BigDecimal userd) {
        this.userd = userd;
    }

    /**
     * 获取剩余可用额度
     *
     * @return residue - 剩余可用额度
     */
    public BigDecimal getResidue() {
        return residue;
    }

    /**
     * 设置剩余可用额度
     *
     * @param residue 剩余可用额度
     */
    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }
}