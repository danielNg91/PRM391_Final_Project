package com.ngoctin.intuition.IntuitionStore.repositories.user;

import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User,Long>
        , MyUserRepository {

}
