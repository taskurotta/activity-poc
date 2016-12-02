package ru.thprom.activity.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProcessEngine processEngine;

    @Autowired
    public InitController(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @GetMapping("/start")
    public String start() {
        ProcessInstance asyncProcess = this.processEngine.getRuntimeService().startProcessInstanceByKey("asyncProcess");
        String message = "Start process " + asyncProcess.getId();
        log.info(message);
        return message;
    }

}
