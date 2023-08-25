package Service;

import Model.Post;
import Repository.PostRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class PostService {
    private PostRepository postRepository;
    public List<Post> displayAllPosts() throws SQLException {
        return postRepository.findAll();
    }
    public Optional<Post> displayPostOfUser(int id) throws SQLException {
        if(postRepository.findById(id).isEmpty()){
            System.out.println("user doesn't exist");
        }
        return postRepository.findById(id);
    }
    public void addNewPost(Post insert) throws SQLException {
        if(postRepository.findById(insert.getId_account().getId()).isEmpty()){
            System.out.println("user provided doesn't exist");
        }
        postRepository.insert(insert);
    }
    public void updatePost(int id) throws SQLException {
        if(postRepository.findById(id).isEmpty()){
            System.out.println("post provided doesn't exist");
        }
        postRepository.updateById(id);
    }
    public void deletePost(int id) throws SQLException {
        if(postRepository.findById(id).isEmpty()){
            System.out.println("post provided doesn't exist");
        }
        postRepository.deleteById(id);
    }
}
