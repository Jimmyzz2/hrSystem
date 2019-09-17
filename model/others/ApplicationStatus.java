package model.others;

public enum ApplicationStatus {

    SUBMITTED("submitted"),
    PENDING("pending"),
    CLOSED("closed");

    private String status;

    ApplicationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
