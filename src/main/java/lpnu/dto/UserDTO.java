//package lpnu.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import javax.annotation.PostConstruct;
//import javax.validation.constraints.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class UserDTO {
//    @EqualsAndHashCode.Exclude
//    private Long userId;
//
//    @NotBlank(message="User name is mandatory")
//    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid user name")
//    private String name;
//
//    @NotBlank(message="User surname is mandatory")
//    @Pattern(regexp="([A-Z][a-z]+[\\s-]?)*[A-Z][a-z]+", message="Invalid user surname")
//    private String surname;
//
//    @NotNull
//    @Min(0)
//    @Max(200)
//    private Integer age;
//
//    private List<TicketDTO> ticketDTOList;// = new ArrayList<>();
//
//    @PostConstruct
//    public void init(){
//        ticketDTOList = new ArrayList<>();
//    }
//
//}
