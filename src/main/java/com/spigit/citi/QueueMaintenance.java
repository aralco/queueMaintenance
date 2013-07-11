package com.spigit.citi;

import com.spigit.citi.service.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueMaintenance {
    public static void main(String args[])  {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Service service = (Service) context.getBean("queueMaintenanceService");
        service.execute();
    }
}
