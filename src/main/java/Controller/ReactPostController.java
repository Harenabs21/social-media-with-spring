package Controller;

import Model.ReactPost;
import Service.ReactPostService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Getter
@RestController
@RequestMapping(path = "/reactions")
public class ReactPostController {
    private ReactPostService reactPostService;
    @GetMapping("/{id}")
    public List<ReactPost> displayAllReactionsOfGivenPost(@PathVariable int id) throws SQLException {
        return reactPostService.displayReactionsOfPost(id);
    }
    @PostMapping("/new")
    public void addNewReactions(@RequestBody ReactPost reactions) throws SQLException {
        reactPostService.addNewReaction(reactions);
    }
    @PutMapping("/update/")
    public void changeReactions(@RequestParam int userId, @RequestParam int postId, @RequestBody String newReaction) throws SQLException {
        reactPostService.updateReactionOfPost(userId,postId,newReaction);
    }
    @DeleteMapping("/")
    public void deleteReaction(int userId, int postId) throws SQLException {
        reactPostService.deleteReactionOfPost(userId,postId);
    }
}
