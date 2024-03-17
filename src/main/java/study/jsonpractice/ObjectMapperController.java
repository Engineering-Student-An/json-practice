package study.jsonpractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/object-mapper")
public class ObjectMapperController {

    @PostMapping("/string-to-student")
    public StudentDto stringToStudent(@RequestBody String inputString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.readValue(inputString, new TypeReference<>() {});
        Student student = new Student(map.get("name").toString(),
                map.get("password").toString(),
                Integer.valueOf(map.get("age").toString()),
                (ArrayList<String>) map.get("classes"));

        return new StudentDto(student);
    }

    @PostMapping("/student-to-string")
    public String studentToString(@RequestBody Student student) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String result = objectMapper.writeValueAsString(student);

        return result;
    }

    @PostMapping("/student-to-string-filter")
    public String studentToString2(@RequestBody Student student) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleFilterProvider filter = new SimpleFilterProvider().addFilter("studentFilter",
                SimpleBeanPropertyFilter.serializeAllExcept("password"));


        objectMapper.setFilterProvider(filter);

        String result = objectMapper.writeValueAsString(student);

        return result;
    }
}
