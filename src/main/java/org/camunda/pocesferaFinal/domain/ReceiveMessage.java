package org.camunda.pocesferaFinal.domain;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ReceiveMessage implements MessageListener {

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void onMessage(Message message) {
        Map variables = new HashMap<>();
        String myMessage = new String (message.getBody());

        try {
//
            variables.put("paymentOk", true);
            variables.put("payment", myMessage);

            ProcessInstance instance = runtimeService.startProcessInstanceByKey("pocesferaFinallRabbit", variables);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

