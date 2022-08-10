package de.buggxs.mygarage.car.brand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "model_series")
@AllArgsConstructor
@NoArgsConstructor
public class ModelSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    @JsonIgnore
    private String url;

    @Column(name = "brand_id")
    @JsonIgnore
    private Long brandId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Brand brand;

    @OneToMany(mappedBy = "modelSeries", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference
    @JsonIgnore
    private Set<ModelSeriesGeneration> modelSeriesGenerationSet;

    public ModelSeries(String name, String url, Brand brand) {
        this.name = name;
        this.url = url;
        this.brand = brand;
    }
}
