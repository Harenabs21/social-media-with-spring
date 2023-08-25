package Controller;

import Model.Post;
import Service.PostService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@RestController
@RequestMapping(path = "/post")
public class PostController {
    private PostService postService;
    @GetMapping("/all posts")
    public List<Post> listOfAllPost() throws SQLException {
        return postService.displayAllPosts();
    }
    @GetMapping("/{id}")
    public Optional<Post> PostById(@PathVariable int id) throws SQLException {
        return postService.displayPostOfUser(id);
    }
    @PostMapping
    public void NewPost(@RequestBody Post post) throws SQLException {
        postService.addNewPost(post);
    }
    @PutMapping("/{id}")
    public void updatePost(@PathVariable int id) throws SQLException {
        postService.updatePost(id);
    }
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) throws SQLException {
        postService.deletePost(id);
    }
}
