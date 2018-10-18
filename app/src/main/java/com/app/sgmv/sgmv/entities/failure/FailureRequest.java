package com.app.sgmv.sgmv.entities.failure;

import java.util.List;

/**
 * Created by gtufinof on 6/14/18.
 */

public class FailureRequest {

    private FailureReport failureReport;
    private List<MainComponent> mainComponents;
    private List<WheelComponent> wheelComponents;

    public FailureReport getFailureReport() {
        return failureReport;
    }

    public void setFailureReport(FailureReport failureReport) {
        this.failureReport = failureReport;
    }

    public List<MainComponent> getMainComponents() {
        return mainComponents;
    }

    public void setMainComponents(List<MainComponent> mainComponents) {
        this.mainComponents = mainComponents;
    }

    public List<WheelComponent> getWheelComponents() {
        return wheelComponents;
    }

    public void setWheelComponents(List<WheelComponent> wheelComponents) {
        this.wheelComponents = wheelComponents;
    }

    @Override
    public String toString() {
        return "FailureRequest{" +
                "failureReport=" + failureReport +
                ", mainComponents=" + mainComponents +
                ", wheelComponents=" + wheelComponents +
                '}';
    }
}
