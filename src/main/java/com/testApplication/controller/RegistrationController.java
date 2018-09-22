    package com.testApplication.controller;

    import com.testApplication.entity.Role;
    import com.testApplication.entity.User;
    import com.testApplication.repo.UserRepo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;

    import java.util.Collections;
    import java.util.Map;

    @Controller
    public class RegistrationController {
        @Autowired
        private UserRepo userRepo;
        @GetMapping("/registration")
        public String registration()
        {
            return "registration";
        }
        @PostMapping("/registration")
        public String regUser(User user, Map<String,Object> model)
        {
           User dbUser=userRepo.findUserByUserName(user.getUserName());
          if (dbUser !=null)
            {
               model.put("message","we have this user");
                return "registration";
            }
            user.setActive(true);
            user.setRoles(Collections.singleton(Role.USER));
            userRepo.save(user);
            return "redirect:/login";
        }
    }
