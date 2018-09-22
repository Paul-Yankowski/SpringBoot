package com.testApplication.controller;

import com.testApplication.entity.Message;
import com.testApplication.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@Controller
public class FirstController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String first(Map<String,Object> model){
        return "hello";
    }

    @GetMapping("/main")
    public String main(Map<String,Object> model)
    {
        Iterable<Message> messages=messageRepo.findAll();
        model.put("messages",messages);
        return "first";
    }
    @PostMapping("/main")
    public String newInf(@RequestParam String text,@RequestParam String tag,Map<String,Object>model)
    {
        Message message=new Message(text,tag);
        messageRepo.save(message);
        Iterable<Message> messages=messageRepo.findAll();
        model.put("messages",messages);
      return "first";
    }
    @PostMapping("find")
    public String find(@RequestParam String find,Map<String,Object>model){
        Iterable<Message> messages;
        if(find!=null&&!find.isEmpty()) {
            messages = messageRepo.findByText(find);
        }else {
            messages=messageRepo.findAll();
        }
         model.put("messages",messages);
        return "first";
    }

}
