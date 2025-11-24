package com.gestaohabitos.controller;

import com.gestaohabitos.model.Habit;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    
    private final List<Habit> habits = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    // Listar todos os hábitos
    @GetMapping
    public List<Habit> getAllHabits() {
        return habits;
    }

    // Criar novo hábito
    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        habit.setId(counter.getAndIncrement());
        habits.add(habit);
        return habit;
    }

    // Buscar hábito por ID
    @GetMapping("/{id}")
    public Habit getHabitById(@PathVariable Long id) {
        return habits.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/{id}/registrar")
    public Habit registrarDia(@PathVariable Long id, 
                             @RequestParam LocalDate data,
                             @RequestParam boolean realizado) {
        Habit habit = getHabitById(id);
        if (habit != null) {
            habit.registrarDia(data, realizado);
        }
        return habit;
    }
}
