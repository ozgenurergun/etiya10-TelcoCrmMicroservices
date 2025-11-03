package com.etiya.catalogservice.domain.entities;

import com.etiya.customerservice.domain.entities.District;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GNL_ST")
public class GENELSTATUS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ent_name")
    private String entName;

    @OneToMany(mappedBy = "GENELSTATUS", cascade = CascadeType.ALL)
    private List<ProductSpecification> productSpecifications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public List<ProductSpecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(List<ProductSpecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public GENELSTATUS() {
    }

    public GENELSTATUS(int id, String name, String entName) {
        this.id = id;
        this.name = name;
        this.entName = entName;
    }
}
