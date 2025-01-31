package WeatherDash.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Weather_Data_Entity")
@Data
public class WeatherDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "tempC")
    private double tempC;

    @Column(name = "tempColor")
    private String tempColor;

    @Column(name = "windKph")
    private double windKph;

    @Column(name = "windColor")
    private String windColor;

    @Column(name = "cloud")
    private int cloud;

    @Column(name = "cloudColor")
    private String cloudColor;

    @Column(name = "data")
    private LocalDateTime date = LocalDateTime.now();

}
