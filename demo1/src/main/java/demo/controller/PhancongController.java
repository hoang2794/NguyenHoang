package demo.controller;

import demo.model.Phancong;
import demo.repository.PhancongRepository;
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
@Table(name="/phancong")
public class PhancongController {

    private PhancongRepository phancongRepository;

    @Autowired
    public PhancongController(PhancongRepository phancongRepository){
        this.phancongRepository=phancongRepository;
    }

    @RequestMapping(value="/update",method = RequestMethod.GET)
    private Phancong AddPC(@RequestParam("MaDA")String MaDA,
                           @RequestParam("MaNV")String MaNV){
        Phancong phancong= new Phancong();
        phancong.setMaDA(MaDA);
        phancong.setMaNV(MaNV);
        phancongRepository.save(phancong);
        return phancong;
    }


    @RequestMapping(value="/edit",method = RequestMethod.GET)
    private Phancong EditPC(@RequestParam("MaDA")String MaDA,
                            @RequestParam("MaNVcu")String Macu,
                            @RequestParam("MaNVmoi")String Mamoi){
        Phancong phancong = phancongRepository.findOne(MaDA);
        if(phancong.getMaNV()==Macu){
             phancong.setMaNV(Mamoi);
             phancongRepository.save(phancong);
        }
        return phancong;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private void DelPC(@RequestParam("MaDA")String MaDA,
                       @RequestParam("MaNV")String MaNV){
        Phancong phancong = phancongRepository.findOne(MaDA);
        if(phancong.getMaNV()==MaNV){
        phancongRepository.delete(phancong);
        }
    }
}
