package com.smallworld;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRecord {
    private int mtn;
    private double amount;
    private String senderFullName;
    private int senderAge;
    private String beneficiaryFullName;
    private int beneficiaryAge;
    private Integer issueId;
    private boolean issueSolved;
    private String issueMessage;

    @JsonCreator
    public TransactionRecord(@JsonProperty("mtn") int mtn,
                             @JsonProperty("amount") double amount,
                             @JsonProperty("senderFullName") String senderFullName,
                             @JsonProperty("senderAge") int senderAge,
                             @JsonProperty("beneficiaryFullName") String beneficiaryFullName,
                             @JsonProperty("beneficiaryAge") int beneficiaryAge,
                             @JsonProperty("issueId") Integer issueId,
                             @JsonProperty("issueSolved") boolean issueSolved,
                             @JsonProperty("issueMessage") String issueMessage) {
        this.mtn = mtn;
        this.amount = amount;
        this.senderFullName = senderFullName;
        this.senderAge = senderAge;
        this.beneficiaryFullName = beneficiaryFullName;
        this.beneficiaryAge = beneficiaryAge;
        this.issueId = issueId;
        this.issueSolved = issueSolved;
        this.issueMessage = issueMessage;
    }

    //getters
    public double getAmount() {
        return amount;
    }
    public String getSenderFullName() {
        return senderFullName;
    }

    public String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }

    public int getBeneficiaryAge() {
        return beneficiaryAge;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public boolean isIssueSolved() {
        return issueSolved;
    }

    public String getIssueMessage() {
        return issueMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MTN: ").append(mtn).append(", Amount: ").append(amount).append(", Sender: ").append(senderFullName).append(", Age: ").append(senderAge).append(", Beneficiary: ").append(beneficiaryFullName).append(", Age: ").append(beneficiaryAge).append(", Issue: ").append(issueId != null ? issueId : "No Issue");

        if (issueId != null) {
            sb.append(", Solved: ").append(issueSolved);
            if (issueMessage != null) {
                sb.append(", Messages: ").append(issueMessage);
            }
        }
        return sb.toString();
    }
}




