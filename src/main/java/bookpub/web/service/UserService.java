/**
 * UserService.java
 * 
 * $Author: ialexan $
 * $Date: 2012-09-27 14:29:19 -0700 (Thu, 27 Sep 2012) $
 */
package bookpub.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookpub.model.User;
import bookpub.model.dao.BookDao;
import bookpub.model.dao.UserDao;

@Controller
public class UserService {

    @Autowired
    BookDao bookDao;

    @Autowired
    UserDao userDao;

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( Date.class, new CustomDateEditor(
            new SimpleDateFormat( "MM/dd/yyyy" ),
            true ) );
    }

    @RequestMapping("/service/user/{id}")
    public String user( @PathVariable Long id, ModelMap models )
    {
        models.put( "user", userDao.getUser( id ) );
        return "jsonView";
    }

    @RequestMapping("/service/user/register")
    public String register( @RequestParam String email,
        @RequestParam String password, @RequestParam String lastName,
        @RequestParam String firstName, @RequestParam Date birthday,
        ModelMap models )
    {
        ServiceResponse result = new ServiceResponse();
        User user = userDao.getUser( email );
        if( user != null )
        {
            result.setSuccess( false );
            result.setMessage( "User already exists." );
        }
        else
        {
            user = new User();
            user.setEmail( email );
            user.setPassword( password );
            user.setFirstName( firstName );
            user.setLastName( lastName );
            user.setBirthday( birthday );
            user = userDao.saveUser( user );
        }

        models.put( "result", result );
        return "jsonView";
    }

}
