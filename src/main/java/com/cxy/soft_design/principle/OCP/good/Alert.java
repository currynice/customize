package com.cxy.soft_design.principle.OCP.good;


import com.cxy.soft_design.principle.OCP.good.handlers.AlertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 新的告警处理类  </br>
 * Date: 2021/3/24 11:05
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */

public class Alert {


    private List<AlertHandler> alertHandlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    public void check(ApiStatInfo apiStatInfo) {
           for (AlertHandler handler : alertHandlers) {
               handler.check(apiStatInfo);
           }
    }
}
