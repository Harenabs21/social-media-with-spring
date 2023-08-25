package Service;

import Model.ReactPost;
import Repository.ReactPostRepository;
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
    List<String> reactions = Arrays.asList("like","love","haha","wow","who cares?");
    public void addNewReaction(ReactPost insert) throws SQLException {
        if(!reactions.contains(insert.getReactionType())){
            System.out.println("invalid reactions");
        }
        reactPostRepository.insertNewContent(insert);
    }
   public Optional<ReactPost> displayReactionsOfPost(int id) throws SQLException{
       return reactPostRepository.getAllReactions(id);
   }
   public void updateReactionOfPost(ReactPost react) throws SQLException {
        reactPostRepository.updateByID(react);
   }
   public void deleteReactionOfPost(ReactPost react) throws SQLException{
        reactPostRepository.deleteByID(react);
   }
}
