package demo.controller;

import demo.model.Nhanvien;
import demo.model.Task;
import demo.model.Duan;
import demo.model.Task;
import demo.repository.DuanRepository;
import demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Nguyen Hoang on 22-Jun-15.
 */
public class DuanController {
    @Autowired
    DuanRepository duanRepository;

    @Autowired
    TaskRepository taskRepository;
    private void Add(@RequestParam("MaNV")String MaNV,
                    @RequestParam("MaDA")String MaDA) {
        Duan duan = new Duan();
        duan.setMaDA(MaDA);
        duan.setMaNV(MaNV);
        duanRepository.save(duan);
    }

    private void List(@RequestParam("taskname")String name,
                      @RequestParam("childname")String childname) {
        for (int i = 0; i < 10; i++)
        {
            Task task = new Task();
            task.setName(name);
            taskRepository.save(task);
            for (int j = 0; j < 5; j++) {
                Task task_child = new Task();
                task_child.setName(childname);
                task_child.setTaskParent(task);
                taskRepository.save(task_child);
            }
        }

    }

    private void ListNV(@RequestParam("taskname")String name,
                        @RequestParam("nhanvien")String MaNV){
        for (int i=0;i<10;i++){
            Duan duan = new Duan();
            duanRepository.save(duan);
            for (int j=0;j<9;j++){
                Duan nhanvien = new Duan();
                nhanvien.setMaNV(MaNV);
                duanRepository.save(nhanvien);
            }
        }
    }
}

