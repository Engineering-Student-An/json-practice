package study.jsonpractice;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/json-parser")
public class JsonParserController {

    @PostMapping("/string-to-student")
    public StudentDto stringToStudent(@RequestBody String inputString) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(inputString);

        Student student = new Student(jsonObject.get("name").toString(),
                jsonObject.get("password").toString(),
                Integer.valueOf(jsonObject.get("age").toString()),
                (ArrayList<String>) jsonObject.get("classes"));

        StudentDto studentDto = new StudentDto(student);

        return studentDto;
    }

    @PostMapping("/student-to-string")
    public String studentToString(@RequestBody Student student) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", student.getName());

        // 비밀번호는 담지 않는다고 가정
        // jsonObject.put("password", student.getPassword());

        jsonObject.put("age", student.getAge());
        jsonObject.put("classes", student.getClasses());

        return jsonObject.toJSONString();
    }
}
