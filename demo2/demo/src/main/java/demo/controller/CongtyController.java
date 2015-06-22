package demo.controller;

import demo.model.Nhanvien;
import demo.model.Congty;
import demo.repository.CongtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
@RestController
@Table(name="/congty")
public class CongtyController {
    private final CongtyRepository congtyRepository;

    @Autowired
    public CongtyController(CongtyRepository congtyRepository){
        this.congtyRepository=congtyRepository;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    private Congty ADD(@RequestParam("id") int boss,
                       @RequestParam("macty")String macty,
                       @RequestParam("name")String name){
        Congty congty = new Congty();

        congty.setBoss(boss);
        congty.setMacty(macty);
        congty.setName(name);
        congtyRepository.save(congty);
        return congty;
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    private Congty Edit(@RequestParam("macty")String macty,
                        @RequestParam("id")int boss,
                        @RequestParam("name")String name){
        Congty congty = congtyRepository.findOne(macty);

        congty.setBoss(boss);
        congty.setName(name);
        congtyRepository.save(congty);
        return congty;
    }

    @RequestMapping(value= "/delete",method = RequestMethod.GET)
    private void Del(@RequestParam("macty")String macty){
        Congty congty = congtyRepository.findOne(macty);
        congtyRepository.delete(congty);
    }


}
