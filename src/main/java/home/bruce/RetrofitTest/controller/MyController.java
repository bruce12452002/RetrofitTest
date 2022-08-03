package home.bruce.RetrofitTest.controller;

import home.bruce.RetrofitTest.entity.Animal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MyController {
    // ======================================= GET =======================================
    @GetMapping("/t1Get/{name}")
    public String test1(@PathVariable("name") String str) {
        return "7788".equals(str) ? "78" : "87";
    }

    @GetMapping("/t2Get/{name}")
    public String test2(@PathVariable("name") String str) {
        return "7788".equals(str) ? "贏了" : "輸了";
    }

    @GetMapping("/t3Get")
    public String test3(@RequestParam("name") String str) {
        System.out.println("name=" + str);
        return "SUCCESS";
    }

    @GetMapping("/t4Get")
    public String test4(@RequestParam("key1") String k1, @RequestParam("key2") String k2) {
        System.out.println("key1=" + k1);
        System.out.println("key2=" + k2);
        return "SUCCESS";
    }

    @GetMapping("/getUri")
    public String test5(@RequestParam("key1") String k1, @RequestParam("key2") String k2) {
        System.out.println("key1=" + k1);
        System.out.println("key2=" + k2);
        return "SUCCESS";
    }

    @GetMapping("/t6Get/{name}")
    public String test6(@PathVariable("name") String str) {
        return "7788".equals(str) ? "78" : "87";
    }

    // ======================================= POST =======================================
    @PostMapping("/t11Post")
    public String test11(HttpServletRequest request) {
        System.out.println("name=" + request.getParameter("name"));
        return "SUCCESS";
    }

    @PostMapping("/t12Post/{address}")
    public String test12(@PathVariable("address") String str) {
        System.out.println("name=" + str);
        return "SUCCESS";
    }

    @PostMapping("/t13Post")
    public String test13(@RequestParam("key1") String k1, @RequestParam("key2") String k2) {
        System.out.println("key1=" + k1);
        System.out.println("key2=" + k2);
        return "SUCCESS";
    }

    @PostMapping("/t14Post")
    public String test14(@RequestBody Animal animal) {
        System.out.println("id=" + animal.getId());
        System.out.println("name=" + animal.getName());
        return "SUCCESS";
    }

    @PostMapping("/t15Post")
    public String test15(@RequestBody List<Animal> animals) {
        animals.forEach(c -> {
            System.out.println(c.getId());
            System.out.println(c.getName());
            System.out.println("===========================");
        });
        return "SUCCESS";
    }

    @PostMapping("/postUri")
    public String test16(@RequestBody Animal animal) {
        System.out.println("id=" + animal.getId());
        System.out.println("name=" + animal.getName());
        return "SUCCESS";
    }

    @PostMapping("/t17Post")
    public String test17(HttpServletRequest request) {
        System.out.println("name=" + request.getParameter("name"));
        return "SUCCESS";
    }
}
