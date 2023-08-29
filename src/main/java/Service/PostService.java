package Service;

import Model.Post;
import Repository.PostRepository;
import Repository.ReactPostRepository;
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
    private ReactPostRepository reactPostRepository;
    public List<Post> displayAllPosts() throws SQLException {
        return postRepository.findAll();
    }
    public Optional<Post> displayPostOfUser(int id) throws SQLException {
        if(postRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+id);
        }
        return postRepository.findById(id);
    }
    public void addNewPost(Post insert) throws SQLException {
        if(postRepository.findById(insert.getId_account()).isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+insert.getId_account());
        }
        postRepository.insert(insert);
    }
    public void updatePost(int id,Post post) throws SQLException {
        Post onePost = new Post();
        Optional<Post> existingPostOptional = postRepository.findById(onePost.getId_account());
        if(existingPostOptional.isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+id);
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
        Post post = new Post();
        if(postRepository.findById(post.getId_account()).isEmpty()){
            throw new ResourceNotFoundException("User not found with id"+post.getId_account());
        }
        reactPostRepository.deleteByIdPost(id);
        postRepository.deleteById(id);
    }
}
