package com.codegym.endmodule4.service;

import com.codegym.endmodule4.model.GiaoDich;
import com.codegym.endmodule4.repository.GiaoDichRepository;
import com.codegym.endmodule4.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiaoDichService {
    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<GiaoDich> getAllGiaoDich() {
        return giaoDichRepository.findAll();
    }

    public GiaoDich getGiaoDichById(String maGiaoDich) {
        return giaoDichRepository.findById(maGiaoDich).orElse(null);
    }

    public GiaoDich saveGiaoDich(GiaoDich giaoDich) {
        return giaoDichRepository.save(giaoDich);
    }

    public void deleteGiaoDich(String maGiaoDich) {
        giaoDichRepository.deleteById(maGiaoDich);
    }
}
