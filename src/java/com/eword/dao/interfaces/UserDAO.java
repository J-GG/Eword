package com.eword.dao.interfaces;

import com.eword.beans.User;

public interface UserDAO {

    void create(User user);

    boolean exist(String username);

    User find(String username, String password);
}
