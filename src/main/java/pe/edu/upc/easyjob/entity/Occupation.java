package pe.edu.upc.easyjob.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "occupation")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name_occupation",length = 50, nullable = false)
    private String name_occupation;

    @Column(name = "desc_occupation",length = 50, nullable = false)
    private String desc_occupation;

}
