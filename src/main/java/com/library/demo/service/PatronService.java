package com.library.demo.service;

import com.library.demo.entity.Patron;
import com.library.demo.exception.notfound.PatronNotFound;
import com.library.demo.model.requests.patron.CreatePatronRequest;
import com.library.demo.model.requests.patron.UpdatePatronRequest;

import java.util.List;

public interface PatronService {
     Patron getPatron(long id) throws PatronNotFound;
     Patron createPatron(CreatePatronRequest request);
     Patron updatePatron(UpdatePatronRequest request,long pk);
     List<Patron> getAllPatrons();
     void deletePatron(long id);

}
