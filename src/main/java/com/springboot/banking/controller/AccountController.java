package com.springboot.banking.controller;

import com.springboot.banking.dto.AccountDto;
import com.springboot.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // Create Account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto),
                HttpStatus.CREATED);
    }
    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                             @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    //WithDraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withDraw(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    //Get All Accounts REST API
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDto = accountService.getAllAccounts();
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
    }
}
