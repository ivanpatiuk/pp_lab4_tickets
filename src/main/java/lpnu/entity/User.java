package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;


import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private Integer age;
    @OneToMany
    private List<Ticket> ticketList;// = new ArrayList<>();

    @PostConstruct
    public void init(){
        ticketList = new ArrayList<>();
    }
}
