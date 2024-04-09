package com.library.demo.service;

import com.library.demo.entity.Patron;
import com.library.demo.exception.notfound.PatronNotFound;
import com.library.demo.model.requests.patron.CreatePatronRequest;
import com.library.demo.model.requests.patron.UpdatePatronRequest;

import java.util.List;

public interface PatronService {
    public Patron getPatron(long id) throws PatronNotFound;
    public Patron createPatron(CreatePatronRequest request);
    public Patron updatePatron(UpdatePatronRequest request,long pk);
    public List<Patron> getAllPatrons();
}
