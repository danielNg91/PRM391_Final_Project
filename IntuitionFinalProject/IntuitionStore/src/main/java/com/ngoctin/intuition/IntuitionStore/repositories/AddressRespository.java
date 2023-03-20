package com.ngoctin.intuition.IntuitionStore.repositories;

import com.ngoctin.intuition.IntuitionStore.entities.User.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRespository extends JpaRepository<Address,Long> {
}
