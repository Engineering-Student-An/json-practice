package study.jsonpractice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class StudentDto {
    public String name;
    public int age;
    public ArrayList<String> classes;

    public StudentDto(Student student) {
        this.name = student.getName();
        this.age = student.getAge();
        this.classes = student.getClasses();
    }
}
