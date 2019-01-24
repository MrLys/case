package com.example.ContractSystem.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Contract {
    private long contractNumber;
    private long costumerNumber;
    private String name;
    private String status = "inactive";

    public Contract(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Contract(long contractNumber, String name) {
        this(contractNumber);
        this.name = name;
        status = "Usendt";
    }

    public long getCostumerNumber() {
        return costumerNumber;
    }

    public void setCostumerNumber(long costumerNumber) {
        this.costumerNumber = costumerNumber;
    }

    public long getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(long contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
