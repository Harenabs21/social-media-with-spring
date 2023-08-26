package Service;

import Model.Post;
import Repository.PostRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
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
        if(postRepository.findById(insert.getId_account()).isEmpty()){
            System.out.println("user provided doesn't exist");
        }
        postRepository.insert(insert);
    }
    public void updatePost(int id,Post post) throws SQLException {
        Optional<Post> existingPostOptional = postRepository.findById(id);
        if(existingPostOptional.isEmpty()){
            System.out.println("post provided doesn't exist");
        }
        else{
            Post existingPost = existingPostOptional.get();
            if(post.getPostPhoto() != null){
                existingPost.setPostPhoto(post.getPostPhoto());
            }
            if(post.getPostContent() != null){
                existingPost.setPostContent(post.getPostContent());
            }
            postRepository.updateById(id,existingPost);
        }
    }
    public void deletePost(int id) throws SQLException {
        if(postRepository.findById(id).isEmpty()){
            System.out.println("post provided doesn't exist");
        }
        postRepository.deleteById(id);
    }
}
