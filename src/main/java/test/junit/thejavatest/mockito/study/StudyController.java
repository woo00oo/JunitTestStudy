package test.junit.thejavatest.mockito.study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import test.junit.thejavatest.mockito.domain.Study;

@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyRepository repository;

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createStudy(@RequestBody Study study) {
        return repository.save(study);
    }
}
