package com.codegym.endmodule4.repository;

import com.codegym.endmodule4.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    List<GiaoDich> findByKhachHang_MaKhachHang(String maKhachHang);
}
