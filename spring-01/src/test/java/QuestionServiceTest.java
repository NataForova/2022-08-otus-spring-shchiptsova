import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.spring.service.QuestionService;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class Person")
public class QuestionServiceTest {

    @Test
    public void testWhenNoResourceFile() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-test-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        try {
            questionService.printQuestions();
            fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetQuestions() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        assertEquals(5, questionService.getStringQuestions().size());
    }

}
