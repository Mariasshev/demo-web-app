package com.example.demo;

import org.springframework.boot.SpringApplication; // <--- Важливо
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller; // <--- Додано для ясності

import java.util.List;
import java.util.Random;

@SpringBootApplication
@Controller // Додаємо анотацію контролера
public class DemoApplication {

    // --- ОСЬ ЦЬОГО МЕТОДУ НЕ ВИСТАЧАЛО ---
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    // --------------------------------------

    @GetMapping("/prediction")
    public String getPrediction(Model model) {
        List<String> predictions = List.of(
                "Сьогодні твій щасливий день для кодингу!",
                "Випий кави і спробуй ще раз.",
                "Твій код запрацює з першої спроби (майже).",
                "Уважно перевір крапку з комою."
        );

        String randomPrediction = predictions.get(new Random().nextInt(predictions.size()));

        model.addAttribute("text", randomPrediction);
        return "prediction";
    }
}