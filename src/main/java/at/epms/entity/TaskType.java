package at.epms.entity;

public enum TaskType {
    SERVICE_TASK("serviceTask"),
    USER_TASK("userTask"),
    BUSINESS_RULE_TASK("businessRuleTask"),
    RECEIVE_TASK("receiveTask"),
    SCRIPT_TASK("scriptTask"),
    SEND_TASK("sendTask"),
    MANUAL_TASK("manualTask");

    private final String elementTypeName;

    TaskType(String elementTypeName) {
        this.elementTypeName = elementTypeName;
    }

    public String getElementTypeName(){
        return elementTypeName;
    }

    public static TaskType fromString(String text) {
        for (TaskType type : TaskType.values()) {
            if (type.elementTypeName.equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
