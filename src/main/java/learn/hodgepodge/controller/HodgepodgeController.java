package learn.hodgepodge.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HodgepodgeController {

    static int sheepCount = 0;

    // return my name
    @GetMapping("/name")
    public String returnMyName()
    {
        return "Hongshen Lin";
    }

    // return current time
    @GetMapping("/current/time")
    public LocalDateTime returnDateTime()
    {
        return LocalDateTime.now();
    }

    // return name
    @GetMapping("/greet/{name}")
    public String greeting(@PathVariable String name)
    {
        return "Hello, Dr." + name + "!";
    }

    @PutMapping("/sheep")
    public void sheepCountAdd()
    {
        ++sheepCount;
    }

    @GetMapping("/sheep")
    public int howManySheep()
    {
        return sheepCount;
    }


}
