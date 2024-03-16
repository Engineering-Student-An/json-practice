package study.jsonpractice;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Student {

    @Expose
    public String name;
    public String password;
    @Expose
    public int age;

    @Expose
    public ArrayList<String> classes = new ArrayList<>();

    public Student() {
    }
}

