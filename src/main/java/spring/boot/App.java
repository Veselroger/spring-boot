package spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@EnableWebMvc
@Controller
public class App {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/hello")
    @ResponseBody
    public String getGreeting() {
        try (Statement statement = dataSource.getConnection().createStatement()) {
            String query = "SELECT text FROM messages WHERE id = 1";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            return resultSet.getString("text");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
