package model.others;

import java.io.Serializable;
import java.util.*;

public class PostManager implements Serializable, Iterable<Post> {

    private List<Post> posts;
    private Integer postID;

    PostManager() {
        this.posts = new ArrayList<>();
        this.postID= 100;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Integer getPostID() {
        return postID;
    }

    public void addPost(Post post) {
        post.setPostID(++postID);
        posts.add(post);
    }

    @Override
    public Iterator<Post> iterator() {
        return new PostManagerIterator();
    }

    private class PostManagerIterator implements Iterator<Post> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < posts.size();
        }

        @Override
        public Post next() {
            Post res;
            try {
                res = posts.get(count);
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            count ++;
            return res;
        }
    }
}
