package com.example.CleanList.Services;

import com.example.CleanList.dto.Response;
import com.example.CleanList.entities.Group;
import com.example.CleanList.repositories.GroupRepository;
import com.example.CleanList.Holiday.CalendarificHoliday;
import com.example.CleanList.entities.Users;
import com.example.CleanList.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CleanDayService {

    private final DateService dateService;
    private final GroupRepository groupRepository;
    private final UsersRepository usersRepository;

    public Object checkDay(LocalDate date){

        CalendarificHoliday holiday = dateService.getHolidayIfExist2(date);

        if (holiday != null) {
            return Response.builder()
                    .status("feriado".toUpperCase())
                    .groupName(null)
                    .message(holiday.getName())
                    .users(null)
                    .build();
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek() ;
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return Response.builder()
                    .status("fim de semana".toUpperCase())
                    .message(dayOfWeek.name())
                    .groupName(null)
                    .users(null)
                    .build();
        }

        long dayNumber = date.getDayOfMonth(); // 1 a 31
        long fullGroups = groupRepository.count(); // 11 grupos
        long groupOrder = ((dayNumber - 1) % fullGroups) + 1; // de volta ao intervalo 1..11
        System.out.println("Dia do mês: " + dayNumber + ", Ordem do grupo ..........: " + groupOrder);

        Group group = groupRepository.findByOrder(groupOrder);
        if (group == null){
            return Response.builder()
                    .status("sem_grupo")
                    .message("Nenhum grupo definido para hoje")
                    .groupName(null)
                    .users(null)
                    .build();
        }


        List<Users> users = usersRepository.findAllByGroupId(group.getId_group());


        return Response.builder()
                .status("dia de limpeza")
                .message( null)
                .groupName(group.getName())
                .users(users)
                .build();
    }



}
