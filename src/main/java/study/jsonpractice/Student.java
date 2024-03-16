package study.jsonpractice;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@JsonFilter("studentFilter")
public class Student {

    public String name;
//    @JsonIgnore
    public String password;
    public int age;

    public ArrayList<String> classes = new ArrayList<>();

    public Student() {
    }
}

