package Service;

import Model.Post;
import Model.ReactPost;
import Model.User;
import Repository.PostRepository;
import Repository.ReactPostRepository;
import Repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class ReactPostService {
    private ReactPostRepository reactPostRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    List<String> reactions = Arrays.asList("like","love","haha","wow","who cares?");
    public void addNewReaction(ReactPost insert) throws SQLException {
        if(!reactions.contains(insert.getReactionType())){
            System.out.println("invalid reactions");
        }
        reactPostRepository.insertNewContent(insert);
    }
   public List<ReactPost> displayReactionsOfPost(int id) throws SQLException{
       return reactPostRepository.getAllReactions(id);
   }
   public void updateReactionOfPost(int userId, int postId, String newReaction) throws SQLException {

        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        if(user.isEmpty() || post.isEmpty()){
            System.out.println("the user or post doesn't exist");
        }
        if(!reactions.contains(newReaction)){
            System.out.println("invalid reactions");
        }
        reactPostRepository.updateReactions(userId,postId,newReaction);
   }
   public void deleteReactionOfPost(int userId, int postId) throws SQLException{
        reactPostRepository.deleteByID(userId,postId);
   }
}
