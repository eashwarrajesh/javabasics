package com.tns.mallservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MallService {

    @Autowired
    private MallRepository mallRepository;

    public List<Mall> getAllMalls() {
        return mallRepository.findAll();
    }

    public Optional<Mall> getMallById(Long id) {
        return mallRepository.findById(id).orElse(null);
    }

    public Mall saveMall(Mall mall) {
        return mallRepository.save(mall);
    }

    public boolean deleteMall(Long id) {
        mallRepository.deleteById(id);
		return false;
    }
}
