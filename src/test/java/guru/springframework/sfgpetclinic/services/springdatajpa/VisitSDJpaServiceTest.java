package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAllTest(){
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);

        //when
        Set<Visit> foundVisits = service.findAll();

        //then
        then(visitRepository).should(times(1)).findAll();
        then(visitRepository).shouldHaveNoMoreInteractions();
        assertThat(foundVisits).hasSize(1);

    }

    @Test
    void findByIdTest(){
        //given
        Visit visit = new Visit();
        visit.setId(1L);
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        //when
        Visit foundVisit = service.findById(1L);

        //then
        assertThat(foundVisit).isNotNull();
        then(visitRepository).should().findById(1L);
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void saveTest(){
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = service.save(visit);

        //then
        assertThat(savedVisit).isNotNull();
        then(visitRepository).should().save(visit);
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteTest(){
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(visitRepository).should().delete(visit);
        then(visitRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByIdTest(){
        //given - none

        //when
        service.deleteById(1L);

        //then
        then(visitRepository).should().deleteById(1L);
    }
}