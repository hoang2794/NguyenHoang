package demo.controller;

import demo.model.Congviec;
import demo.repository.CongviecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 18-Jun-15.
 */
@RestController
@Table (name="/congviec")
public class CongviecController {

    private CongviecRepository congviecRepository;

    @Autowired
    public CongviecController(CongviecRepository congviecRepository){
        this.congviecRepository= congviecRepository;
    }

    @RequestMapping(value="/update",method = RequestMethod.GET)
    private Congviec AddCV(@RequestParam("macty")String macty,
                           @RequestParam("MaNV")String MaNV){
        Congviec congviec = new Congviec();
        congviec.setMacty(macty);
        congviec.setMaNV(MaNV);
        congviecRepository.save(congviec);
        return congviec;
    }

    @RequestMapping(value="/edit",method = RequestMethod.GET)
    private Congviec EditCV(@RequestParam("macty")String macty,
                           @RequestParam("MaNVcu")String Macu,
                            @RequestParam("MaNVmoi")String Mamoi){
        Congviec congviec = congviecRepository.findOne(macty);
        if(congviec.getMaNV()==Macu){
            congviec.setMaNV(Mamoi);
            congviecRepository.save(congviec);
        }
            return congviec;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private void DelCV(@RequestParam("MaNV")String MaNV,
                       @RequestParam("macty")String macty) {
        Congviec congviec = congviecRepository.findOne(MaNV);
        if (congviec.getMacty() == macty) {
            congviecRepository.delete(congviec);
        }
    }

}
