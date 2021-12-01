package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lpnu.dto.TicketDTO;

import javax.annotation.PostConstruct;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;

    @NotBlank(message="User name is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid user name")
    private String name;

    @NotBlank(message="User surname is mandatory")
    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid user surname")
    private String surname;

    @NotNull
    @Min(0)
    @Max(200)
    private Integer age;

    private List<TicketDTO> ticketDTOList;// = new ArrayList<>();

    @PostConstruct
    public void init(){
        ticketDTOList = new ArrayList<>();
    }

    public User() {
    }

    public User(Long id, String name, String surname, Integer age, List<TicketDTO> ticketDTOList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ticketDTOList = ticketDTOList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<TicketDTO> getTicketDTOList() {
        return ticketDTOList;
    }

    public void setTicketDTOList(List<TicketDTO> ticketDTOList) {
        this.ticketDTOList = ticketDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }
}
