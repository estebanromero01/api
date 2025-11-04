package co.kata.api.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiPojo {
    private int id = 1;
    private String username = "";
    private String email = "";
    private String password = "";
}
