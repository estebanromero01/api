package co.kata.api.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiPojo {
    private String title = "";
    private String body = "";
    private int userId = 0;
}
