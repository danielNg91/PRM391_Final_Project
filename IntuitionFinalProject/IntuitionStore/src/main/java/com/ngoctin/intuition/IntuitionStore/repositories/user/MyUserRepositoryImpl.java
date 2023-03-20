package com.ngoctin.intuition.IntuitionStore.repositories.user;

import com.ngoctin.intuition.IntuitionStore.ApplicationUtils;
import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import com.ngoctin.intuition.IntuitionStore.entities.User.User;
import com.ngoctin.intuition.IntuitionStore.entities.User.UsersAddresses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MyUserRepositoryImpl implements MyUserRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM users " +
                    "WHERE username = ? and is_deleted = ?";
            Query query = entityManager.createNativeQuery(sql, User.class);
            query.setParameter(1, username);
            query.setParameter(2,false);
            User user = (User) query.getResultList().stream().findFirst().get();
            return user;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return null;
        }
    }

    @Override
    @Transactional
    public boolean createUser(User user) {
        try {
            log.info(user.toString());
            String sql = "INSERT INTO users " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            Query query = entityManager.createNativeQuery(sql, User.class);
            query.setParameter(1, user.getUsername());
            query.setParameter(2, user.getPassword());
            query.setParameter(3, user.getFullname());
            query.setParameter(4, user.getPhoneNumber());
            query.setParameter(5, user.getEmail());
            query.setParameter(6, ApplicationUtils.getCurrentTime());
            query.setParameter(7, null);
            query.setParameter(8, null);
            query.setParameter(9, "USER");
            query.setParameter(10, null);
            query.setParameter(11, false);
            if (query.executeUpdate() > 0) {
                return true;
            }
            return false;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return false;
        }
    }


    @Override
    @Transactional
    public boolean updateUser(String username, User user) {
        try {
            String sql = "UPDATE users " +
                    "SET fullname = ?, phone_number = ?, email = ? " +
                    "WHERE username = ?";
            Query query = entityManager.createNativeQuery(sql, User.class);
            query.setParameter(1, user.getFullname());
            query.setParameter(2, user.getPhoneNumber());
            query.setParameter(3, user.getEmail());
            query.setParameter(4, username);
            int check = query.executeUpdate();
            if (check > 0) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Address> getAddressByUserID(int userID) {
        try{
            String sql = "SELECT a.id, a.value " +
                    "FROM users u " +
                    "JOIN users_addresses ua ON u.id = ua.user_id " +
                    "JOIN addresses a ON a.id = ua.address_id " +
                    "WHERE u.id = ? and ua.is_deleted = 'false'";
            Query query = entityManager.createNativeQuery(sql,Address.class);
            query.setParameter(1,userID);
            List<Address> addresses = query.getResultList();
            if(addresses != null){
                return addresses;
            }
            return null;
        }catch (Exception exception){
            log.error("MyUserRepository -  getAddressByUserID - err : " + exception.getMessage());
            return  null;
        }
    }

    @Override
    @Transactional
    public boolean removeAddressByUserID(int userID, int addressID) {
        try {
            String sql = "UPDATE users_addresses " +
                    "SET is_deleted = 'true' " +
                    "WHERE user_id = ? " +
                    "AND address_id = ? ";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,userID);
            query.setParameter(2,addressID);
            if(query.executeUpdate() > 0){
                return true;
            }
            return false;
        }catch (Exception exception){
            return false;
        }
    }

    @Override
    @Transactional
    public Address addNewAddress(String addressValue) {
        String sql = "INSERT INTO addresses values(?)";
        Query query = entityManager.createNativeQuery(sql,Address.class);
        query.setParameter(1,addressValue);
        if(query.executeUpdate()  > 0){
            return (Address)query.getSingleResult();
        }
        return null;
    }

    @Override
    @Transactional
    public boolean addNewUserAddress(int userID, int addressID) {
        String sql = "INSERT INTO users_addresses " +
                "VALUES(?,?,'false')";
        Query query = entityManager.createNativeQuery(sql, UsersAddresses.class);;
        query.setParameter(1,userID);
        query.setParameter(2,addressID);
        if(query.executeUpdate() > 0){
            return true;
        }return false;
    }
}
