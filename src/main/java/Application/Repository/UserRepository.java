package Application.Repository;

import Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ionel Carmen on 22.05.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    public User findByName(String name);
    public User findByUsername(String username);
    public List<User> findAll();
}