package com.recycleBusiness.RecyclePal.controllers;

import com.recycleBusiness.RecyclePal.dto.request.CustomerRegistrationRequest;
import com.recycleBusiness.RecyclePal.dto.request.CustomerSubmitRequest;
import com.recycleBusiness.RecyclePal.dto.request.UpdateCustomerRequest;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerRegistrationResponse;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerSubmitResponse;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerUpdateResponse;
import com.recycleBusiness.RecyclePal.exception.CustomerNotSaveIntoDataBase;
import com.recycleBusiness.RecyclePal.exception.CustomerWithEmailOrUsernameExist;
import com.recycleBusiness.RecyclePal.exception.WasteNotCreated;
import com.recycleBusiness.RecyclePal.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerServiceImpl service;

	@PostMapping("/register")
    public ResponseEntity<CustomerRegistrationResponse> registrationResponse(@RequestBody CustomerRegistrationRequest registrationRequest){
        try {
            var response = service.customerRegistration(registrationRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CustomerWithEmailOrUsernameExist | CustomerNotSaveIntoDataBase e) {
            var response = new CustomerRegistrationResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

   	@PatchMapping("/completeRegistration/{username}")
    public ResponseEntity<CustomerUpdateResponse> completeRegistration(
            @PathVariable("username") String username,
            @RequestBody UpdateCustomerRequest request){
        try {
            var response = service.updateProfile(username,request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (CustomerWithEmailOrUsernameExist e) {
            var response = new CustomerUpdateResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @RequestMapping("/createWasteResponse")
    public ResponseEntity<?> createWasteRequest(CustomerSubmitRequest request){
        try {
            var response = service.submitRequest(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (WasteNotCreated e) {
            var response = new CustomerSubmitResponse();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
