package com.scm.SCM20.repositories;

import com.scm.SCM20.entities.Contact;
import com.scm.SCM20.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {
    List<Contact> findByUser(User user);


//    //custom finder methods
//    List<Contact> findByUserId(String userId);
//
//    // custom Query methods
////    @Query("SELECT c FROM contacts WHERE c.user.id= :userId" )
////    List<Contact> findByUserId(@Param("userId") String userId);
}
