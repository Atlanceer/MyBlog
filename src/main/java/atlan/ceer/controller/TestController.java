package atlan.ceer.controller;

import atlan.ceer.exception.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    @RequestMapping("/testError")
    public void testError(){
        int i=9/0;
    }

    @RequestMapping("/testNotFound")
    public void testNotFound(){
        //int i=9/0;
        String blog=null;
        if (blog==null) {
            throw new NotFoundException("没有找到页面");
        }
    }

    @RequestMapping("/{id}/{name}")
    public String testAop(@PathVariable Integer id, @PathVariable String name){
        System.out.println("--------index--------"+id+name);
        return "index";
    }

}
