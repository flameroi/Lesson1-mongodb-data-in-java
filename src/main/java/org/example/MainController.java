package org.example;

import org.example.docs.Users;
import org.example.repository.UserRepos;
import org.example.repository.UserReposCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    private static final String[] NAMES = {"Valchalla", "Decart", "Stone"};

    @Autowired
    private UserReposCustom userReposCustom;
@Autowired
    private UserRepos userRepos;

@RequestMapping("/")
    @ResponseBody
    public String home(){
    String html = "";
    html += "<ul>";
    html += " <li><a href='/testInsert'>Test Insert</a></li>";
    html += " <li><a href='/showAllEmployee'>Show All Employee</a></li>";
    // html += " <li><a href='/showFullNameLikeTom'>Show All 'Tom'</a></li>";
    html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
    html += "</ul>";
    return html;
}

@RequestMapping("/testInsert")
    @ResponseBody
    public String testInsert(){
    Users user = new Users();

    long id = this.userReposCustom.getMaxEmpNo()+1;
    int idRandomName = (int)(id%NAMES.length);
    String fullName = NAMES[idRandomName];

    user.setNewUser(id, "E"+id, fullName);

    this.userRepos.insert(user);

    return "Inserted" + user
            + "<br> <a href='/'>Go Back</a>";
}

    @RequestMapping("/showAllEmployee")
    @ResponseBody
    public String showAllEmployee(){
        Users user = new Users();
        List<Users> users = this.userRepos.findAll();

        String html = "";
        for(Users emp: users){
            html+= emp + "<br>";
        }
        if(html!= null)
        {
            return "Хранящиеся данные: </br>"+ html
                    + "<br> <a href='/'>Go Back</a>";
        }

            return "Try again" +
                    "<br> <a href='/'>Go Back</a>";
    }

    @RequestMapping("/deleteAllEmployee")
    @ResponseBody
    public String deleteAllEmployee(){
        userRepos.deleteAll();
        return "Cleared <br> " +
                "<a href='/'>Go Back</a>";// + user;
    }
}
