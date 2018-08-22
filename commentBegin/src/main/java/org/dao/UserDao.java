package org.dao;

import org.bean.User;

public interface UserDao {
    User selectByNameAndPass(User user);
}
