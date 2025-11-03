package com.etiya.catalogservice.domain.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "GNL_TP")
public class GENELTYPE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "ent_name")
    private String entName;

    @OneToMany(mappedBy = "GENELTYPE", cascade = CascadeType.ALL)
    private List<Characteristic> characteristics;

    @OneToMany(mappedBy = "GENELTYPE", cascade = CascadeType.ALL)
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

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public List<ProductSpecification> getProductSpecifications() {
        return productSpecifications;
    }

    public void setProductSpecifications(List<ProductSpecification> productSpecifications) {
        this.productSpecifications = productSpecifications;
    }

    public GENELTYPE() {
    }

    public GENELTYPE(int id, String name, String entName) {
        this.id = id;
        this.name = name;
        this.entName = entName;
    }
}
