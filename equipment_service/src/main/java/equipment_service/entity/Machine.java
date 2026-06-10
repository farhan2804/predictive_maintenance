package equipment_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "machines")
@Data
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String location;

    private String status;
}