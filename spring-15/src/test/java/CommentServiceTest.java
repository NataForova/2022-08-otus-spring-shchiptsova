import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.spring.dao.CommentRepository;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.service.BookService;
import ru.otus.spring.service.CommentService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = ApplicationConfigTest.class)
public class CommentServiceTest {

    private static final String NEW_COMMENT_ID_BOOK = "1";
    private static final String NEW_COMMENT_TEXT = "The most important book";

    @Autowired
    CommentService commentService;

    @Mock
    CommentRepository commentRepository;

    @Test
    void insertCommentTestWhenBookIdIsNull() {
        assertThatThrownBy(() -> commentService.insert(NEW_COMMENT_TEXT, null))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Book id cannot be null");
    }

    @Test
    void insertCommentTestWhenTextIsEmptyIdIsNull() {
        assertThatThrownBy(() -> commentService.insert("", NEW_COMMENT_ID_BOOK))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("Comment text cannot be null");
    }

    @Test
    void insertCommentTestWhenAllRight() {
        Comment comment = commentService.insert(NEW_COMMENT_TEXT, NEW_COMMENT_ID_BOOK);

        assertThat(comment.getCommentText()).isEqualTo(NEW_COMMENT_TEXT);
        assertThat(comment.getBookId()).isEqualTo(NEW_COMMENT_ID_BOOK);
    }

    @Test
    void updateCommentTestWhenAllRight() {
        given(commentRepository.findAll())
                .willReturn(Collections.singletonList(new Comment("1", "1", "Good book")));
        List<Comment> comments = commentRepository.findAll();

        Comment comment = comments.get(0);
        String commentId = comment.getId();
        commentService.update(commentId, NEW_COMMENT_TEXT);

        given(commentRepository.findById("1"))
                .willReturn(Optional.of(new Comment("1", "1", "The most important book")));
        Optional<Comment> editedCommentOptional = commentRepository.findById(commentId);

        assertThat(editedCommentOptional.isPresent()).isTrue();
        assertThat(editedCommentOptional.get().getCommentText()).isEqualTo(NEW_COMMENT_TEXT);
        assertThat(comment.getBookId()).isEqualTo(NEW_COMMENT_ID_BOOK);
    }
}
