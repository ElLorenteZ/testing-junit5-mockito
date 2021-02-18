package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject(){
        Speciality speciality = new Speciality();

        service.delete(speciality);

        verify(specialityRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest(){
        Speciality speciality = new Speciality();
        speciality.setId(1L);
        when(specialityRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1L);

        assertThat(foundSpeciality).isNotNull();

        verify(specialityRepository).findById(anyLong());
    }

    @Test
    void findByIdBdd() {
        //given
        Speciality speciality = new Speciality();
        speciality.setId(1L);
        given(specialityRepository.findById(1L)).willReturn(Optional.of(speciality));

        //when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
        then(specialityRepository).should(times(1)).findById(anyLong());
        then(specialityRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByIdTest() {
        //given - none

        //when
        service.deleteById(1L);

        //then
        //verify(specialityRepository).deleteById(1L);
        then(specialityRepository).should(times(1)).deleteById(1L);
        then(specialityRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByIdTestTimes(){
        service.deleteById(1L);
        service.deleteById(1L);
        verify(specialityRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast(){
        service.deleteById(1L);
        verify(specialityRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost(){
        service.deleteById(1L);
        verify(specialityRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever(){
        //given - none

        //when
        service.deleteById(1L);

        //then
        then(specialityRepository).should(never()).deleteById(5L);
    }

    @Test
    void deleteTestBdd(){
        //given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialityRepository).should(times(1)).delete(any(Speciality.class));
        then(specialityRepository).shouldHaveNoMoreInteractions();
    }

}