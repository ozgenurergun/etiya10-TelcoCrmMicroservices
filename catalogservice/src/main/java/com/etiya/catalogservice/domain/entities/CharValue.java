package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Table(name = "char_values")
@SQLRestriction("is_active = 1")
public class CharValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

    @OneToMany(mappedBy = "charValue")
    private List<ProductCharValue> productCharValues;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characteristic_id", nullable = false)
    private Characteristic characteristic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ProductCharValue> getProductCharValues() {
        return productCharValues;
    }

    public void setProductCharValues(List<ProductCharValue> productCharValues) {
        this.productCharValues = productCharValues;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }

    public CharValue() {
    }

    public CharValue(int id, String value, List<ProductCharValue> productCharValues, Characteristic characteristic) {
        this.id = id;
        this.value = value;
        this.productCharValues = productCharValues;
        this.characteristic = characteristic;
    }
}
