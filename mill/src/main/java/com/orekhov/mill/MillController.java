package com.orekhov.mill;

import com.orekhov.mill.bean.MillState;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/mill")
public class MillController {

    private final MillService millService;

    public MillController(MillService millService) {
        this.millService = millService;
    }

    @GetMapping
    public MillState state(){
        return millService.getState();
    }

    @PostMapping("/water/{capacity}")
    public String addWater(@PathVariable Integer capacity){
        millService.addWater(capacity);
        return "Поток воды получен колесом.";
    }

    @PostMapping("/millet/{capacity}")
    public String addMillet(@PathVariable Integer capacity){
        millService.addMillet(capacity);
        return "Зерно доставлено.";
    }

    @PostMapping("flour/drop")
    public String dropFlour(){
        millService.dropFlour();
        return "Мука отгружена.";
    }

}
