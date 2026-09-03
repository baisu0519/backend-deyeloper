package me.baisu;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello yzh!";
    }
    @PostMapping("/test")
    public String posttest() {
        return "Hello post!";
    }

    @PutMapping("/test")
    public String puttest() {
        return "Hello put!";
    }

    @DeleteMapping("/test")
    public String deletetest() {
        return "Hello delete!";
    }


}
