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
@Getter
@Service
public class ReactPostService {
    private ReactPostRepository reactPostRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    public void addNewReaction(ReactPost insert) throws SQLException {
        List<String> reactions = Arrays.asList("like","love","haha","wow","who cares?");
        if(!reactions.contains(insert.getReactionType())){
            throw new IllegalArgumentException("invalid reactions");
        }
        reactPostRepository.insertNewContent(insert);
    }
   public List<ReactPost> displayReactionsOfPost(int id) throws SQLException{
       return reactPostRepository.getAllReactions(id);
   }
   public void updateReactionOfPost(int userId, int postId, ReactPost newReaction) throws SQLException {
        List<String> reactions = Arrays.asList("like","love","haha","wow","who cares?");
        Optional<User> user = userRepository.findById(userId);
        Optional<Post> post = postRepository.findById(postId);
        if(user.isEmpty() || post.isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+userId+"and post not found with id"+postId);
        }
        if(!reactions.contains(newReaction.getReactionType())){
            throw new IllegalArgumentException("invalid reactions");
        }
        reactPostRepository.updateReactions(userId,postId,newReaction);
   }
   public void deleteReactionOfPost(int userId, int postId) throws SQLException{
        reactPostRepository.deleteByID(userId,postId);
   }
   public void deleteReactionOfUser(int userId) throws SQLException{
       Optional<User> user = userRepository.findById(userId);
       if(user.isEmpty()){
           throw new IllegalArgumentException("User with id"+userId+"does not exist");
       }
       reactPostRepository.deleteByIdUser(userId);
   }
   public void deleteReactionsOfPost(int postId) throws SQLException{
       Optional<Post> post = postRepository.findById(postId);
       if(post.isEmpty()){
           throw new IllegalArgumentException("User with id"+postId+"does not exist");
       }
   }
}
