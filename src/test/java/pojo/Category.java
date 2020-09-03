package pojo;

import lombok.*;

// All Annotation need to be at a class level

@Getter // This annotation will add getters for all fields
@Setter // This annotation will add setters for all fields
@AllArgsConstructor // will add constructor including all fields
@NoArgsConstructor // will add no argument constructor
@ToString // will add toString method for the class
public class Category {

    private String id;
    private String name;


}
