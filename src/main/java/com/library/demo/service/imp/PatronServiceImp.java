package com.library.demo.service.imp;

import com.library.demo.entity.Patron;
import com.library.demo.exception.CustomException;
import com.library.demo.exception.PatronNotFound;
import com.library.demo.model.requests.patron.CreatePatronRequest;
import com.library.demo.model.requests.patron.UpdatePatronRequest;
import com.library.demo.repository.PatronRepository;
import com.library.demo.service.PatronService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatronServiceImp implements PatronService {
    private final String PATRON_NOT_FOUND = "Patron not found";
    PatronRepository patronRepository;
    ModelMapper modelMapper;

    @Autowired
    public PatronServiceImp(PatronRepository patronRepository, ModelMapper modelMapper) {
        this.patronRepository = patronRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Patron getPatron(long id) {
        Optional<Patron> optionalPatron = this.patronRepository.findById(id);
        Patron patron = optionalPatron.orElseThrow(() -> new PatronNotFound(PATRON_NOT_FOUND));
        return patron;
    }

    @Override
    public Patron createPatron(CreatePatronRequest request) {
        Patron patron = this.modelMapper.map(request, Patron.class);
        this.patronRepository.save(patron);
        return patron;
    }

    @Override
    public Patron updatePatron(UpdatePatronRequest request, long pk) {
        Optional<Patron> optionalPatron = this.patronRepository.findById(pk);
        Patron patron = optionalPatron.orElseThrow(()->new PatronNotFound(PATRON_NOT_FOUND));
        if(this.patronRepository.existsByEmailOrPhoneAndIdNot(request.getEmail(),request.getPhone(),pk)){
            if(!Objects.equals(request.getEmail(), patron.getEmail()))
                throw new CustomException("Email is already used","email");

            if(!Objects.equals(request.getPhone(),patron.getPhone()))
                throw new CustomException("Phone is already used","phone");
        }

        this.modelMapper.map(request,patron);

        this.patronRepository.save(patron);

        return patron;
    }

    @Override
    public List<Patron> getAllPatrons() {
        return this.patronRepository.findAll();
    }
}
