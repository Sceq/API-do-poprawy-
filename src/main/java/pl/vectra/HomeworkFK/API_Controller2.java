package pl.vectra.HomeworkFK;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.beans.PersistenceDelegate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("person")
public class API_Controller2 {


    @Autowired
    private PersonService personService;

    API_Controller2() { this.personService = new PersonService(); }

    @GetMapping()
    public List<Person> getAllPerson(@RequestParam("pageSize") int pageSize,
                                    @RequestParam("pageNumber") int pageNumber) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);

        Page<PersonModel> list = this.personService.listPaginated(pageable);
        return list.stream().map(x -> Person.builder()
                        .name(x.getName())
                        .second_name(x.getSecond_name())
                        .id(x.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    Person getPersonById(@PathVariable("id") Long id) {
        Optional<PersonModel> data = this.personService.getById(id);
        PersonModel result = data.orElseThrow();
        return Person.builder()
                .name(result.getName())
                .second_name(result.getSecond_name())
                .id(result.getId())
                .build();
    }

    @PostMapping
    Person createPerson(@RequestBody Person person)
        {
            PersonModel result = this.personService.create(person);
            return Person.builder()
                    .name(result.getName())
                    .second_name(result.getSecond_name())
                    .id(result.getId())
                    .build();

        }
    @DeleteMapping("/usun/{id}")
    public boolean deletePerson(@PathVariable Long id) {
        if (!this.personService.getById(id).equals(Optional.empty())) {
            this.personService.delete(id);
            return true;
        }
        return false;
    }

    @PutMapping("update/{id}")

    Person changePerson(@PathVariable (value = "id") long id, @RequestBody Person person) {
        PersonModel change  = this.personService.updatePerson(id, person);
        return  Person.builder()
                .name(change.getName())
                .second_name(change.getSecond_name())
                .id(change.getId())
                .build();

    }


        @ExceptionHandler({ NoSuchElementException.class })
    public ResponseEntity<Object> handleException() {
        return new  ResponseEntity<Object>("Not found", HttpHeaders.EMPTY, HttpStatus.NOT_FOUND);
    }
}
