package demo.controller;

import demo.model.Nhanvien;
import demo.repository.NhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 18-Jun-15.
 */
@RestController
@Table (name="/nhanvien")
public class NhanvienController {
    private NhanvienRepository nhanvienRepository;

    @Autowired
    public NhanvienController(NhanvienRepository nhanvienRepository){
        this.nhanvienRepository=nhanvienRepository;
    }

    @RequestMapping(value="/update",method = RequestMethod.GET)
    public Nhanvien putNV(
            @RequestParam("MaNV") String MaNV,
            @RequestParam("MaDA") String MaDA,
            @RequestParam("ten")String ten,
            @RequestParam("gioitinh")String gioitinh,
            @RequestParam("ngaysinh")String ngaysinh) {
        Nhanvien nhanvien = new Nhanvien();

        nhanvien.setMaNV(MaNV);
        nhanvien.setTen(ten);
        nhanvien.setGioitinh(gioitinh);
        nhanvien.setNgaysinh(ngaysinh);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }



    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public Nhanvien editNV(
            @RequestParam("MaNV") String MaNV,
            @RequestParam("ten")String ten,
            @RequestParam("gioitinh")String gioitinh,
            @RequestParam("ngaysinh")String ngaysinh) {
        Nhanvien nhanvien = nhanvienRepository.findOne(MaNV);

        nhanvien.setMaNV(MaNV);
        nhanvien.setTen(ten);
        nhanvien.setGioitinh(gioitinh);
        nhanvien.setNgaysinh(ngaysinh);
        nhanvienRepository.save(nhanvien);
        return nhanvien;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteNV(@RequestParam("MaNV")String MaNV){
        Nhanvien nhanvien= nhanvienRepository.findOne(MaNV);
        nhanvienRepository.delete(nhanvien);
    }
}
