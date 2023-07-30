package com.recycleBusiness.RecyclePal.controllers;

import com.recycleBusiness.RecyclePal.dto.request.CustomerRegistrationRequest;
import com.recycleBusiness.RecyclePal.dto.request.CustomerSubmitRequest;
import com.recycleBusiness.RecyclePal.dto.request.UpdateCustomerRequest;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerRegistrationResponse;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerSubmitResponse;
import com.recycleBusiness.RecyclePal.dto.responce.CustomerUpdateResponse;
import com.recycleBusiness.RecyclePal.exception.CustomerNotSaveIntoDataBase;
import com.recycleBusiness.RecyclePal.exception.CustomerWithEmailOrUsernameExist;
import com.recycleBusiness.RecyclePal.exception.UsernameNotFoundException;
import com.recycleBusiness.RecyclePal.exception.WasteNotCreated;
import com.recycleBusiness.RecyclePal.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
@Slf4j
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

   	@PostMapping("/completeRegistration/")
    public ResponseEntity<CustomerUpdateResponse> completeRegistration(
            @RequestBody UpdateCustomerRequest request){
        log.info("checking for username passed {}",request.getFirstname());
        try {
            var response = service.updateProfile(request);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (CustomerWithEmailOrUsernameExist | UsernameNotFoundException e) {
            var response = new CustomerUpdateResponse();
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    @PostMapping("/createWasteResponse")
    public ResponseEntity<?> createWasteRequest(@RequestBody CustomerSubmitRequest request){
        try {
            var response = service.submitRequest(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (WasteNotCreated e) {
            var response = new CustomerSubmitResponse();
            return ResponseEntity.badRequest().body(response);
        }
    }

}
