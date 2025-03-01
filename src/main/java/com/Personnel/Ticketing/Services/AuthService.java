package com.Personnel.Ticketing.Services;


import com.Personnel.Ticketing.Models.Customer;
import com.Personnel.Ticketing.dto.SignupRequest;

public interface AuthService {
    Customer createCustomer(SignupRequest signupRequest);
}
