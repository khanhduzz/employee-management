package com.fpt.fsa.employee_management.repositories;

import com.fpt.fsa.employee_management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountName (String name);

    Account findByAccountNameAndPassword (String accountName, String password);

}
