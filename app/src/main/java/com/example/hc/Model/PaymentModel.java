package com.example.hc.Model;

import java.util.Date;

public class PaymentModel {
    String PaymentId,PateintId,AptId,Type,Status;
    Integer Amount;
    String PaymentDate;

    public PaymentModel(String paymentId, String pateintId, String aptId, String paymentDate, String type, int amount, String status) {
        PaymentId = paymentId;
        PateintId = pateintId;
        AptId = aptId;
        PaymentDate = paymentDate;
        Type = type;
        Amount = amount;
        Status = status;
    }

    public PaymentModel() {
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public String getPateintId() {
        return PateintId;
    }

    public void setPateintId(String pateintId) {
        PateintId = pateintId;
    }

    public String getAptId() {
        return AptId;
    }

    public void setAptId(String aptId) {
        AptId = aptId;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
