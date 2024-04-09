package com.library.demo.controller;

import com.library.demo.entity.Patron;
import com.library.demo.model.requests.patron.CreatePatronRequest;
import com.library.demo.model.requests.patron.UpdatePatronRequest;
import com.library.demo.service.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    @Autowired
    PatronService patronService;

    @PostMapping
    ResponseEntity<Patron> createPatron(@Valid @RequestBody CreatePatronRequest request) {
        return new ResponseEntity<>(this.patronService.createPatron(request), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Patron>> getAllPatrons() {
        return new ResponseEntity<>(this.patronService.getAllPatrons(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<Patron> getPatronById(@PathVariable long id) {
        return new ResponseEntity<>(this.patronService.getPatron(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    ResponseEntity<Patron> updatePatron(@PathVariable long id, @Valid @RequestBody UpdatePatronRequest request){
        return new ResponseEntity<>(this.patronService.updatePatron(request,id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    ResponseEntity<String> deletePatron(@PathVariable long id ){
        this.patronService.deletePatron(id);
        return new ResponseEntity<>("patron deleted successfully",HttpStatus.OK);
    }
}

