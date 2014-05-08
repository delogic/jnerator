package br.com.delogic.jnerator.test.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.delogic.jnerator.test.entities.enums.OrderStatus;
import br.com.delogic.jnerator.test.entities.util.TenentProperty;

@SuppressWarnings("serial")
@Entity
public class Order extends LongEntityId implements TenentProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqOrder")
    @SequenceGenerator(name = "seqOrder", sequenceName = "seq_Order", allocationSize = 1, initialValue = 1)
    @Column(precision = 10)
    private Long             id;

    @Column(length = 50)
    private String           orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OrderStatus      status;

    @JoinColumn(nullable = false)
    private Tenent           tenent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date             creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date             lastChangeDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Set<ItemProduct> orderItens;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal       productsValue;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal       orderValue;

    @JoinColumn(nullable = false)
    private Client           client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private DeliveryMode     deliveryMode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private PaymenteMode     paymentMode;

    @Column(length = 100)
    private String           comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Tenent getTenent() {
        return tenent;
    }

    public void setTenent(Tenent tenent) {
        this.tenent = tenent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public Set<ItemProduct> getOrderItens() {
        return orderItens;
    }

    public void setOrderItens(Set<ItemProduct> orderItens) {
        this.orderItens = orderItens;
    }

    public BigDecimal getProductsValue() {
        return productsValue;
    }

    public void setProductsValue(BigDecimal productsValue) {
        this.productsValue = productsValue;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public PaymenteMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymenteMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
