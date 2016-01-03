package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import api.LoginData;
import api.RegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    private ArrayList<Integer> allRegIDs = new ArrayList<Integer>();

    @Autowired
    private Connection connection;

    /**
     * 1 - user already exists
     * 0 - ok
     * -1 - error
     * @param registrationData
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/registerUser")
    @ResponseBody
    public Integer registerUser(@RequestBody RegistrationData registrationData) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.error("\n------------" + registrationData.toString());

        String sql = "SELECT email from Users WHERE email='" + registrationData.getEmail() + "';";
        ResultSet result = null;
        try{
            result = statement.executeQuery(sql);

            if(result.next()){
                return 1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        sql = "INSERT INTO Users (nume, prenume, email, password, telefon, adresa) VALUES ('"
                + registrationData.getFirstName()
                + "', '" + registrationData.getLastName()
                + "', '" + registrationData.getEmail()
                + "', '" + registrationData.getPassword()
                + "', '" + registrationData.getTelephone()
                + "', '" + registrationData.getAddress()
                +"');";

        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 0 - ok
     * -1 - incorrect
     * @param loginData
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public Integer login(@RequestBody LoginData loginData) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.error("\n------------" + loginData.toString());

        String sql = "SELECT email from Users WHERE email='"
                + loginData.getEmail() + "' and password='"
                + loginData.getPassword() + "';";
        ResultSet result = null;
        try{
            result = statement.executeQuery(sql);

            if(!result.next()){
                return -1;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
