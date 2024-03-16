package study.jsonpractice;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gson")
public class GsonController {

    @PostMapping("/string-to-student")
    public StudentDto stringToStudent(@RequestBody String inputString) {

        Gson gson = new Gson();
        Student student = gson.fromJson(inputString, Student.class);

        // Student 를 StudentDTO로 변환해서 리턴
        return new StudentDto(student);
    }

    @PostMapping("/student-to-string")
    public String studentToString(@RequestBody Student student) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(student);

        return jsonString;
    }

    @PostMapping("/student-to-string-expose")
    public String studentToString2(@RequestBody Student student) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String jsonString = gson.toJson(student);

        return jsonString;
    }

    @PostMapping("/student-to-string-exclusion-strategy")
    public String studentToString3(@RequestBody Student student) {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new PasswordExclusionStrategy())
                .create();
        String jsonString = gson.toJson(student);

        return jsonString;
    }

    static class PasswordExclusionStrategy implements ExclusionStrategy {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            // password 필드를 스킵함
            return f.getName().equals("password");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            // 스킵할 클래스는 없으므로 false 리턴
            return false;
        }
    }


}
