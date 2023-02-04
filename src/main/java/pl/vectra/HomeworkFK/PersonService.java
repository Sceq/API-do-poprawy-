package pl.vectra.HomeworkFK;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<PersonModel> list() {
        return personRepository.findAll();
    }

    public Page<PersonModel> listPaginated(Pageable pageable) {
        return personRepository.findAll(pageable);
    }


    public PersonModel create(Person person) {
        PersonModel newInstance = PersonModel.from(person);
        personRepository.save(newInstance);
        return newInstance;

    }

    public Optional<PersonModel> getById(Long id) {
        PersonModel x = null;
        return personRepository.findById(id);
    }

    public void delete(long id) {
        personRepository.deleteById(id);

    }

    public PersonModel updatePerson(long id, Person person) {
        PersonModel current = personRepository.findById(id).get();
        current.setName(person.getName());
        current.setSecond_name(person.getSecond_name());
        current.setId(person.getId());

        return personRepository.save(current);
    }

    }