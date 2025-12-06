package com.gestaohabitos.controller;

import com.gestaohabitos.model.Habit;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/habits")
public class HabitController {
    
    private final List<Habit> habits = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping
    public List<Habit> getAllHabits() {
        return habits;
    }

    @GetMapping("/{id}")
    public Habit getHabitById(@PathVariable Long id) {
        return habits.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Habit createHabit(@RequestBody Habit habit) {
        habit.setId(counter.getAndIncrement());
        habits.add(habit);
        return habit;
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit habitAtualizado) {
        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            if (habit.getId().equals(id)) {
                habitAtualizado.setId(id);
                habits.set(i, habitAtualizado);
                return habitAtualizado;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteHabit(@PathVariable Long id) {
        boolean removed = habits.removeIf(h -> h.getId().equals(id));
        return removed ? "Hábito excluído com sucesso" : "Hábito não encontrado";
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

    @GetMapping("/{id}/estatisticas")
    public String getEstatisticas(@PathVariable Long id) {
        Habit habit = getHabitById(id);
        if (habit == null) {
            return "Hábito não encontrado";
        }
        
        double taxaSucesso = habit.calcularTaxaSucesso();
        int streak = habit.calcularStreakAtual();
        
        return String.format("Taxa de Sucesso: %.2f%% | Streak Atual: %d dias", taxaSucesso, streak);
    }
}
