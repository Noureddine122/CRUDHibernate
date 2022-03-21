package data;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "TypeO")
public class TypeO {
    @Override
    public String toString() {
        return "TypeO{" +
                "idType=" + idType +
                ", libelle='" + libelle + '\'' +
                ", offresByIdType=" + offresByIdType.toString() +
                '}';
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_type")
    private int idType;
    @Basic
    @Column(name = "Libelle")
    private String libelle;
    @OneToMany(mappedBy = "typeOByIdType")
    private Collection<Offre> offresByIdType;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeO typeO = (TypeO) o;

        if (idType != typeO.idType) return false;
        if (libelle != null ? !libelle.equals(typeO.libelle) : typeO.libelle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idType;
        result = 31 * result + (libelle != null ? libelle.hashCode() : 0);
        return result;
    }

    public Collection<Offre> getOffresByIdType() {
        return offresByIdType;
    }

    public void setOffresByIdType(Collection<Offre> offresByIdType) {
        this.offresByIdType = offresByIdType;
    }
}
