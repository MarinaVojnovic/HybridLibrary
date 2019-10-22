package rs.hybridit.service;

import rs.hybridit.model.User;

import java.util.List;

public interface UserService {
    public User getOne(long id);
    public List<User> getAll();
    public User create(User user);
    public User save(User user);
    public void delete(User user);
    public User findByUsername(String username);
}
