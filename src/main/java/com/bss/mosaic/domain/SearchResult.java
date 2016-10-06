package com.bss.mosaic.domain;

import com.bss.mosaic.stars.domain.CaseInfo;
import com.bss.mosaic.stars.domain.CaseNote;

import java.util.List;

/**
 * Created by db44417 on 10/08/2016.
 */
public class SearchResult {

    private ClientAccount clientAccount;

    private Client client;

    private ClientParent clientParent;

    private List<InquiryInfo> inquiryInfoList;

    private List<PaymentFile> paymentFileList;

    private List<PaymentTransaction> paymentTransactionList;

    private List<Rate> rateList;

    private List<InquiryTrail> inquiryTrailList;

    private CaseInfo caseInfo;

    private List<CaseInfo> caseInfoList;

    private CaseNote caseNote;

    private ClientRisk clientRisk;



    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientParent getClientParent() {
        return clientParent;
    }

    public void setClientParent(ClientParent clientParent) {
        this.clientParent = clientParent;
    }

    public List<InquiryInfo> getInquiryInfoList() {
        return inquiryInfoList;
    }

    public void setInquiryInfoList(List<InquiryInfo> inquiryInfoList) {
        this.inquiryInfoList = inquiryInfoList;
    }

    public List<PaymentFile> getPaymentFileList() {
        return paymentFileList;
    }

    public void setPaymentFileList(List<PaymentFile> paymentFileList) {
        this.paymentFileList = paymentFileList;
    }

    public List<PaymentTransaction> getPaymentTransactionList() {
        return paymentTransactionList;
    }

    public void setPaymentTransactionList(List<PaymentTransaction> paymentTransactionList) {
        this.paymentTransactionList = paymentTransactionList;
    }

    public List<Rate> getRateList() {
        return rateList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    public List<InquiryTrail> getInquiryTrailList() {
        return inquiryTrailList;
    }

    public void setInquiryTrailList(List<InquiryTrail> inquiryTrailList) {
        this.inquiryTrailList = inquiryTrailList;
    }

    public List<CaseInfo> getCaseInfoList() {
        return caseInfoList;
    }

    public void setCaseInfoList(List<CaseInfo> caseInfoList) {
        this.caseInfoList = caseInfoList;
    }

    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    public CaseNote getCaseNote() {
        return caseNote;
    }

    public void setCaseNote(CaseNote caseNote) {
        this.caseNote = caseNote;
    }

    public ClientRisk getClientRisk() {
        return clientRisk;
    }

    public void setClientRisk(ClientRisk clientRisk) {
        this.clientRisk = clientRisk;
    }
}
