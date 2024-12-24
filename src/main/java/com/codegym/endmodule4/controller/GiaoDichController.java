package com.codegym.endmodule4.controller;

import com.codegym.endmodule4.model.GiaoDich;
import com.codegym.endmodule4.model.KhachHang;
import com.codegym.endmodule4.repository.KhachHangRepository;
import com.codegym.endmodule4.service.GiaoDichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {
    @Autowired
    private GiaoDichService giaoDichService;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping
    public String listGiaoDich(Model model) {
        model.addAttribute("giaoDichList", giaoDichService.getAllGiaoDich());
        return "giao-dich/list";
    }
    @GetMapping("/them-moi")
    public String themMoiForm(Model model) {
        model.addAttribute("giaoDich", new GiaoDich());  // Tạo đối tượng GiaoDich
        return "giao-dich/them-moi";  // Trả về form
    }

    @PostMapping("/them-moi")
    public String themMoi(@ModelAttribute GiaoDich giaoDich, RedirectAttributes redirectAttributes) {
        // Lưu khách hàng vào database
        KhachHang khachHang = giaoDich.getKhachHang();
        khachHangRepository.save(khachHang);

        // Lưu giao dịch vào database
        giaoDich.setKhachHang(khachHang);  // Gán khách hàng cho giao dịch
        giaoDichService.saveGiaoDich(giaoDich);

        redirectAttributes.addFlashAttribute("message", "Thêm mới giao dịch và khách hàng thành công!");
        return "redirect:/giao-dich";  // Redirect về danh sách giao dịch
    }


    @GetMapping("/chi-tiet/{id}")
    public String chiTiet(@PathVariable String id, Model model) {
        GiaoDich giaoDich = giaoDichService.getGiaoDichById(id);
        model.addAttribute("giaoDich", giaoDich);
        return "giao-dich/chi-tiet";
    }

    @PostMapping("/xoa/{id}")
    public String xoa(@PathVariable String id, RedirectAttributes redirectAttributes) {
        giaoDichService.deleteGiaoDich(id);
        redirectAttributes.addFlashAttribute("message", "Xóa giao dịch thành công!");
        return "redirect:/giao-dich";
    }
}
