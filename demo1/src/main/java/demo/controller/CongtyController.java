package demo.controller;

import demo.model.Congty;
import demo.repository.CongtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nguyen Hoang on 17-Jun-15.
 */
@RestController
@RequestMapping("/congty")
public class CongtyController {
    private CongtyRepository congtyRepository;
    @Autowired
    public CongtyController(CongtyRepository congtyRepository)
    {
        this.congtyRepository=congtyRepository;
    }

    @RequestMapping(value="/update",method = RequestMethod.GET)
    public Congty putCongty(
            @RequestParam("macty") String macty,
            @RequestParam("name") String name,
            @RequestParam("boss") Integer boss) {
        Congty congty = new Congty();

        congty.setMacty(macty);
        congty.setName(name);
        congty.setBoss(boss);
        congtyRepository.save(congty);
        return congty;
    }


    @RequestMapping(value="/edit",method = RequestMethod.GET)
    public Congty editCongty(
            @RequestParam("macty") String macty,
            @RequestParam("name") String name,
            @RequestParam("boss") Integer boss) {
        Congty congty = congtyRepository.findOne(macty);
        congty.setName(name);
        congty.setBoss(boss);
        congtyRepository.save(congty);
        return congty;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private void DelCT(@RequestParam("macty")String macty){
        Congty congty = congtyRepository.findOne(macty);
        congtyRepository.delete(congty);
    }
}
