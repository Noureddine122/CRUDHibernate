package data;

import jakarta.persistence.*;

@Entity
@Table(name = "Offre")
public class Offre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_offre")
    private int idOffre;
    @Basic
    @Column(name = "Profile")
    private String profile;

    @Override
    public String toString() {
        return "Offre{" +
                "idOffre=" + idOffre +
                ", profile='" + profile + '\'' +
                ", description='" + description + '\'' +
                ", typeOByIdType=" + typeOByIdType.toString() +
                '}';
    }

    @Basic
    @Column(name = "Description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id_type")
    private TypeO typeOByIdType;

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offre offre = (Offre) o;

        if (idOffre != offre.idOffre) return false;
        if (profile != null ? !profile.equals(offre.profile) : offre.profile != null) return false;
        if (description != null ? !description.equals(offre.description) : offre.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOffre;
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public TypeO getTypeOByIdType() {
        return typeOByIdType;
    }

    public void setTypeOByIdType(TypeO typeOByIdType) {
        this.typeOByIdType = typeOByIdType;
    }
}
