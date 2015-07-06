package demo.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by Nguyen Hoang on 06-Jul-15.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RootTime extends Root {
    public Long timecreate;
    public Long timeupdate;

    public Long getTimecreate() {
        return timecreate;
    }

    public void setTimecreate(Long timecreate) {
        this.timecreate = timecreate;
    }

    public Long getTimeupdate() {
        return timeupdate;
    }

    public void setTimeupdate(Long timeupdate) {
        this.timeupdate = timeupdate;
    }
}
