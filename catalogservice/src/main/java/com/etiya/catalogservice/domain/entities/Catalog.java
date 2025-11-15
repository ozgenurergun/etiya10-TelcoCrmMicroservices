package com.etiya.catalogservice.domain.entities;

import com.etiya.common.entities.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "catalogs")
@SQLRestriction("is_active = 1")
public class Catalog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    // 1. Özyinelemeli İlişki (Üst Katalog): Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // Kendi tablosuna bakan yabancı anahtar
    private Catalog parent;

    // 2. Özyinelemeli İlişki (Alt Kataloglar): One-to-Many
    // List kullanıldı ve id'ye göre sıralandı.
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC") // Listeyi ID'ye göre artan sırada sıralar
    private List<Catalog> children = new ArrayList<>();

    @OneToMany(mappedBy = "catalog")
    private List<CatalogProductOffer> catalogProductOffers;

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

    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    public List<Catalog> getChildren() {
        return children;
    }

    public void setChildren(List<Catalog> children) {
        this.children = children;
    }



    public List<CatalogProductOffer> getCatalogProductOffers() {
        return catalogProductOffers;
    }

    public void setCatalogProductOffers(List<CatalogProductOffer> catalogProductOffers) {
        this.catalogProductOffers = catalogProductOffers;
    }

    public Catalog() {
    }

    public Catalog(int id, String name, Catalog parent, List<Catalog> children, List<CatalogProductOffer> catalogProductOffers) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
        this.catalogProductOffers = catalogProductOffers;
    }
}
