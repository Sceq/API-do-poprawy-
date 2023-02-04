package pl.vectra.HomeworkFK;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APICTRL {

    @GetMapping("/")
    public String GetHome() {
        return "Home";
    }

    @GetMapping("/vectra123")
    public String getTest() {
        return "Pozdrawiam";

    }
    @GetMapping
    public Person getPerson(){ return Person.builder().name("Filip").second_name("Kaniecki").build();
    }



}


