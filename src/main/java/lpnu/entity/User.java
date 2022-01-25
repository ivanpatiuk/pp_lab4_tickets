//package lpnu.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lpnu.dto.TicketDTO;
//
//import javax.annotation.PostConstruct;
//import javax.validation.constraints.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class User {
//    private Long id;
//
//    private String name;
//    private String surname;
//
//    private Integer age;
//    private List<Ticket> ticketList;// = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//        ticketList = new ArrayList<>();
//    }
//
//    public User() {
//    }
//
//    public User(Long id, String name, String surname, Integer age, List<Ticket> ticketList) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.ticketList = ticketList;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public List<TicketDTO> getTicketDTOList() {
//        return ticketDTOList;
//    }
//
//    public void setTicketDTOList(List<TicketDTO> ticketDTOList) {
//        this.ticketDTOList = ticketDTOList;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, surname, age);
//    }
//}
