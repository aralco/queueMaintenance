package com.spigit.citi.service;

import com.spigit.citi.dao.TransactionQueueDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QueueMaintenanceService extends Service {

    private static final Logger logger = LoggerFactory.getLogger(QueueMaintenanceService.class);
    private int daysBefore;
    @Autowired
    private TransactionQueueDao transactionQueueDao;

    public void setDaysBefore(int daysBefore) {
        this.daysBefore = daysBefore;
    }

    @Override
    public void execute() {
        logger.info("*************************QueueMaintenance*************************");
        cleanQueueMaintenance();

    }

    private void cleanQueueMaintenance() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, daysBefore * (-1));
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Date timeBefore = calendar.getTime();
        logger.info("Deleting SUCCESS transactions older than {} days, transmitTime before {}.", daysBefore, formatter.format(timeBefore));
        int affectedRows = transactionQueueDao.deleteTransactionQueues(timeBefore);
        logger.info("{} rows have been deleted from TransactionQueue table.",affectedRows);
    }
}
