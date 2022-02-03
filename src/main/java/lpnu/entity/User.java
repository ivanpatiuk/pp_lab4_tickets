//package lpnu.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lpnu.entity.mapper.Convertable;
//import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
//
//
//import javax.annotation.PostConstruct;
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "user")
//public class User implements Convertable {
//    @Id
//    @EqualsAndHashCode.Exclude
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Column(name = "user_name")
//    private String name;
//    @Column(name = "surname")
//    private String surname;
//
//    @Column(name = "age")
//    private Integer age;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Ticket> ticketList;// = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//        ticketList = new ArrayList<>();
//    }
//}
