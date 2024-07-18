package learn.hodgepodge.controller;


import learn.hodgepodge.sheep.SheepValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HodgepodgeController {

    static int sheepCount = 0;
    static ArrayList<String> todos = new ArrayList<>(List.of("first"));


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

    // add one sheep
    @PutMapping("/sheep")
    public void sheepCountAdd()
    {
        ++sheepCount;
    }

    // display amount of sheep
    @GetMapping("/sheep")
    public int howManySheep()
    {
        return sheepCount;
    }

    // add sheep by client define amount
    @PutMapping("/sheep/{amount}")
    public void addSheepAmount(@PathVariable int amount)
    {
        sheepCount += amount;
    }

    // add sheep using json object
    @PostMapping("/sheep")
    public void addSheepObj(@RequestBody SheepValue value)
    {
        sheepCount += value.getAmount();
    }

    // subtract sheep by one
    @DeleteMapping("/sheep")
    public void lostSheep()
    {
        --sheepCount;
    }

    // todo
    @GetMapping("/todo")
    public List<String> getTodo()
    {
        return todos;
    }

    // bulk append
    @PutMapping("/todo")
    public void appendTodo(@RequestBody List<String> items)
    {
        todos.addAll(items);
    }

    // add new todo
    @PutMapping("/todo/{item}")
    public void addOneTodo(@PathVariable String item)
    {
        todos.add(item);
    }

    // remove based on given index
    @DeleteMapping("/todo/{index}")
    public ResponseEntity<?> deleteTodo(@PathVariable int index)
    {
        // out of range
        // return 404
        if(index > todos.size() -1 || index < 0)
        {
            return new ResponseEntity<>("Todo Index Not Found", HttpStatus.NOT_FOUND);
        }

        int before = todos.size();
        todos.remove(index);

        // return 200 on success
        if(before == todos.size() + 1)
        {
            return new ResponseEntity<>("Successfully removed " + index, HttpStatus.OK);
        }
        // fails, return 404
        return new ResponseEntity<>("Todo Index Not Found", HttpStatus.NOT_FOUND);
    }

    // replace current todo list with provided one
    @PostMapping("/todo")
    public void replaceTodo(@RequestBody List<String> items)
    {
        todos = (ArrayList<String>) items;
    }
}
